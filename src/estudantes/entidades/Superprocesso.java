package estudantes.entidades;

import java.util.ArrayList;
import java.util.List;

import professor.entidades.Processo;

/**
 * Espelho de um {@link Processo} aberto na mesa do burocrata.
 * <br><br>
 * Enquanto o {@link Processo} guarda apenas a lista de documentos, o
 * Superprocesso mantém os agregados necessários para decidir, sem varrer a
 * lista inteira a cada ciclo, se um novo documento pode entrar sem gerar
 * estresse no despacho.
 * <br><br>
 * A atualização desses agregados acontece em {@link #adicionar(Documento)} e a
 * decisão de aceitar ou não um documento fica em {@link #podeReceber(Documento)}.
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Superprocesso {

    /** Limite de páginas suportado por um processo antes de romper no transporte. */
    private static final int LIMITE_PAGINAS = 250;

    /** Processo real na mesa que este superprocesso acompanha. */
    private final Processo processo;

    /** Indica se este superprocesso ainda corresponde a um processo aberto. */
    private boolean ativo;

    /** Agregados usados pelas regras de despacho. */

    /** Soma das páginas dos documentos adicionados. */
    private int totalPaginas;

    /** Quantidade de documentos adicionados ao processo. */
    private int quantidadeDocumentos;

    /** Regra 1: graduação não pode ser despachada junto de pós-graduação. */
    private boolean temGraduacao;

    /** Indica se o processo contém documento de pós-graduação. */
    private boolean temPosGraduacao;

    /** Regra 2: administrativo não pode ser despachado junto de acadêmico (ata é neutra). */
    private boolean temAdministrativo;

    /** Indica se o processo contém documento acadêmico. */
    private boolean temAcademico;

    /** Regra 3: Não pode ter apenas atas. */
    private boolean temNaoAta;

    /** Regra 4: portaria/edital com 100+ páginas e ainda válido exige processo exclusivo. */
    private boolean temSubstancialValido;

    /** Regra 6: diploma só pode ir com diploma, certificado ou ata. */
    private boolean temDiploma;

    /** Indica se há documento incompatível com diploma no processo. */
    private boolean temIncompativelComDiploma;

    /** Regra 7: todos os atestados do processo precisam ser da mesma categoria. */
    private String categoriaAtestado;

    /** Destinatários comuns aos documentos de comunicação do processo. */
    private  String [] destinatariosComuns;

    /**
     * Cria o superprocesso vinculado a um processo aberto na mesa.
     *
     * @param processo processo real que será acompanhado
     */
    public Superprocesso(Processo processo) {
        this.processo = processo;
        this.ativo = true;
        this.totalPaginas = 0;
        this.quantidadeDocumentos = 0;
        this.temGraduacao = false;
        this.temPosGraduacao = false;
        this.temAdministrativo = false;
        this.temAcademico = false;
        this.temSubstancialValido = false;
        this.temDiploma = false;
        this.temIncompativelComDiploma = false;
        this.categoriaAtestado = null;
        this.destinatariosComuns = null;
        this.temNaoAta = false;
    }

    /**
     * Indica se o documento pode entrar neste processo sem gerar estresse no
     * despacho.
     * <br><br>
     * A lógica das regras ainda não foi desenvolvida; por enquanto recusa
     * qualquer documento para garantir que nada seja despachado indevidamente.
     *
     * @param documento documento candidato a entrar no processo
     * @return true se o documento pode ser adicionado, false caso contrário
     */
    public boolean podeReceber(Documento documento) {
        /**
         * Desenvolve a checagem combinando o estado atual com o documento:
         * limite de páginas e regras de compatibilidade do processo.
         */
        if(documento.getPaginas() + this.totalPaginas > 250){
            return false;
        }

        /** Regra 3: o processo não pode conter apenas atas. */
        if (documento instanceof Ata) {
            if (!this.temNaoAta && this.quantidadeDocumentos > 0) {
                return false;
            }
        }

        if(temSubstancialValido){
            return false;
        }

        /** Regra 1: não misturar graduação e pós-graduação. */
        switch (documento.getCodigoCurso()) {
            case POS_GRADUACAO_COMPUTACAO:
            case POS_GRADUACAO_ENGENHARIA_ELETRICA:
            case POS_GRADUACAO_MICROELETRONICA:
                if(this.temGraduacao)
                    return false;
                break;
            default:
                if(this.temPosGraduacao)
                    return false;
        }

        /** Regra 2: não misturar documentos administrativos e acadêmicos. */
        if(documento instanceof DocumentoAdministrativo && this.temAcademico)
            return false;
        else if(documento instanceof DocumentoAcademico && this.temAdministrativo)
            return false;

        /** Regra 4: documento substancial válido só entra em processo vazio. */
        if ((documento instanceof Portaria || documento instanceof Edital) && documento.getPaginas() >= 100 && ((Norma) documento).isValido())
            if(!this.estaVazio())
                return false;

        //   - regra 5: circulares/ofícios precisam manter um destinatário em comum
        if (documento instanceof Circular || documento instanceof Oficio) {
            boolean temEmComum = false; 
            if (this.destinatariosComuns != null){
                if (documento instanceof Oficio) { 
                    Oficio oficio = (Oficio) documento; 
                    String destOficio = oficio.getDestinatario(); 
                    if (destOficio != null) { 
                        for (String destProcesso : this.destinatariosComuns) { 
                            if (destOficio.equals(destProcesso)) { 
                                temEmComum = true; 
                                break; 
                            } 
                        } 
                    } 
                } else if (documento instanceof Circular) { 
                    Circular circular = (Circular) documento; 
                    String[] destsCircular = circular.getDestinatarios(); 
                    if (destsCircular != null) {
                        /** Percorre cada destinatário da circular e compara com os do processo. */
                        for (String destCirc : destsCircular) { 
                            if (destCirc != null) { 
                                for (String destProcesso : this.destinatariosComuns) { 
                                    if (destCirc.equals(destProcesso)) { 
                                        temEmComum = true; break; 
                                    } 
                                } 
                            } if (temEmComum) { 
                                break; 
                            } 
                        } 
                    } 
                }
            }
        if (destinatariosComuns != null && !temEmComum)  return false;
        }

        /** Regra 6: diploma só pode ser agrupado com diploma, certificado ou ata. */
        if (documento instanceof Diploma && this.temIncompativelComDiploma)
            return false;
        else if (!(documento instanceof Certificado) && !(documento instanceof Ata) && this.temDiploma)
            return false;
        /** Regra 7: atestado precisa ser da mesma categoria dos já presentes. */
        if (documento instanceof Atestado) {
            String categoria = ((Atestado) documento).getCategoria();
            if (this.categoriaAtestado != null && !this.categoriaAtestado.equals(categoria))
                return false;
        }
        return true;
    }

    /**
     * Coloca o documento no processo real e atualiza os agregados internos.
     * <br><br>
     * Chame {@link #podeReceber(Documento)} antes para não violar regras.
     *
     * @param documento documento que será adicionado ao processo
     */
    public void adicionar(Documento documento) {
        processo.adicionarDocumento(documento);

        totalPaginas += documento.getPaginas();
        quantidadeDocumentos++;

        /** Regra 1: origem do documento, entre graduação e pós-graduação. */
        switch (documento.getCodigoCurso()) {
            case POS_GRADUACAO_COMPUTACAO:
            case POS_GRADUACAO_ENGENHARIA_ELETRICA:
            case POS_GRADUACAO_MICROELETRONICA:
                temPosGraduacao = true;
                break;
            default:
                temGraduacao = true;
        }

        /** Regra 2: família do documento; ata não é administrativa nem acadêmica. */
        if (documento instanceof DocumentoAdministrativo) {
            temAdministrativo = true;
        } else if (documento instanceof DocumentoAcademico) {
            temAcademico = true;
        }

        /** Regra 4: portaria ou edital substancial e ainda válido. */
        if ((documento instanceof Portaria || documento instanceof Edital)
                && documento.getPaginas() >= 100 && ((Norma) documento).isValido()) {
            temSubstancialValido = true;
        }

        /** Regra 6: compatibilidade com diploma. */
        if (documento instanceof Diploma) {
            temDiploma = true;
        } else if (!(documento instanceof Certificado) && !(documento instanceof Ata)) {
            temIncompativelComDiploma = true;
        }

        /** Regra 7: guarda a categoria do primeiro atestado encontrado. */
        if (documento instanceof Atestado && categoriaAtestado == null) {
            categoriaAtestado = ((Atestado) documento).getCategoria();
        }

        /** Regra 5: mantém a interseção dos destinatários de circulares e ofícios. */
        if (documento instanceof Circular || documento instanceof Oficio) {
            String[] destinatariosDocumento;
            if (documento instanceof Circular) {
                destinatariosDocumento = ((Circular) documento).getDestinatarios();
            } else {
                destinatariosDocumento = new String[] { ((Oficio) documento).getDestinatario() };
            }

            if (destinatariosComuns == null) {
                destinatariosComuns = destinatariosDocumento.clone();
            } else {
                List<String> intersecao = new ArrayList<>();
                for (String destinoComum : destinatariosComuns) {
                    for (String destinoDocumento : destinatariosDocumento) {
                        if (destinoComum != null && destinoComum.equals(destinoDocumento)) {
                            intersecao.add(destinoComum);
                            break;
                        }
                    }
                }
                destinatariosComuns = intersecao.toArray(new String[0]);
            }
        }
        /** Regra 3: o processo não pode conter apenas atas. */
        if (!(documento instanceof Ata)) { this.temNaoAta = true; }
    }


    /**
     * Marca este superprocesso como encerrado (usar após despachar o processo).
     */
    public void encerrar() {
        this.ativo = false;
    }

    /**
     * @return processo real acompanhado por este superprocesso
     */
    public Processo getProcesso() {
        return processo;
    }

    /**
     * @return true enquanto o superprocesso corresponde a um processo aberto
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @return soma das páginas dos documentos já adicionados
     */
    public int getTotalPaginas() {
        return totalPaginas;
    }

    /**
     * @return quantidade de documentos já adicionados
     */
    public int getQuantidadeDocumentos() {
        return quantidadeDocumentos;
    }

    /**
     * @return quantas páginas ainda cabem neste processo antes do limite de transporte
     */
    public int getPaginasRestantes() {
        return LIMITE_PAGINAS - totalPaginas;
    }

    /**
     * @return true se tem substancial válido
     */
    public boolean getSubstancialValido() {
        return temSubstancialValido;
    }
    /**
    * Verifica se nenhum documento foi adicionado ainda.
    *
     * @return true se nenhum documento foi adicionado ainda
     */
    public boolean estaVazio() {
        return quantidadeDocumentos == 0;
    }
}
