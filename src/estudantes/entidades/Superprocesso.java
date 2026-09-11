package estudantes.entidades;

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

    // --- Agregados usados pelas regras de despacho ---

    private int totalPaginas;
    private int quantidadeDocumentos;

    /** Regra 1: graduação não pode ser despachada junto de pós-graduação. */
    private boolean temGraduacao;
    private boolean temPosGraduacao;

    /** Regra 2: administrativo não pode ser despachado junto de acadêmico (ata é neutra). */
    private boolean temAdministrativo;
    private boolean temAcademico;

    /** Regra 4: portaria/edital com 100+ páginas e ainda válido exige processo exclusivo. */
    private boolean temSubstancialValido;

    /** Regra 6: diploma só pode ir com diploma, certificado ou ata. */
    private boolean temDiploma;
    private boolean temIncompativelComDiploma;

    /** Regra 7: todos os atestados do processo precisam ser da mesma categoria. */
    private String categoriaAtestado;

    private  String [] destinatario;

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
        this.destinatario = null;
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
        // TODO desenvolver a checagem, combinando o estado atual com o documento:
        //   - limite de páginas: totalPaginas + documento.getPaginas() <= LIMITE_PAGINAS
        //   - regra 1: não misturar graduação e pós-graduação
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

        //   - regra 2: não misturar administrativo e acadêmico (ata é livre)
        if(documento instanceof DocumentoAdministrativo && this.temAcademico)
            return false;
        else if(documento instanceof DocumentoAcademico && this.temAdministrativo)
            return false;

        //   - regra 4: substancial válido só entra em processo vazio, e nada entra depois dele
        if ((documento instanceof Portaria || documento instanceof Edital) && documento.getPaginas() >= 100 && ((Norma) documento).isValido())
            if(!this.estaVazio())
                return false;
        //   - regra 5: circulares/ofícios precisam manter um destinatário em comum
        if (documento instanceof Circular || documento instanceof Oficio)
            if(this.destinatario)
        //   - regra 6: diploma só com diploma, certificado ou ata
        //   - regra 7: atestado precisa ser da mesma categoria dos já presentes
        return false;
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

        // Regra 1: origem do documento (graduação x pós-graduação)
        switch (documento.getCodigoCurso()) {
            case POS_GRADUACAO_COMPUTACAO:
            case POS_GRADUACAO_ENGENHARIA_ELETRICA:
            case POS_GRADUACAO_MICROELETRONICA:
                temPosGraduacao = true;
                break;
            default:
                temGraduacao = true;
        }

        // Regra 2: família do documento (ata não é administrativo nem acadêmico)
        if (documento instanceof DocumentoAdministrativo) {
            temAdministrativo = true;
        } else if (documento instanceof DocumentoAcademico) {
            temAcademico = true;
        }

        // Regra 4: portaria ou edital substancial e ainda válido
        if ((documento instanceof Portaria || documento instanceof Edital)
                && documento.getPaginas() >= 100 && ((Norma) documento).isValido()) {
            temSubstancialValido = true;
        }

        // Regra 6: compatibilidade com diploma
        if (documento instanceof Diploma) {
            temDiploma = true;
        } else if (!(documento instanceof Certificado) && !(documento instanceof Ata)) {
            temIncompativelComDiploma = true;
        }

        // Regra 7: guarda a categoria do primeiro atestado encontrado
        if (documento instanceof Atestado && categoriaAtestado == null) {
            categoriaAtestado = ((Atestado) documento).getCategoria();
        }

        // TODO regra 5: manter a interseção dos destinatários de circulares/ofícios

         if (documento instanceof Circular || documento instanceof Oficio){
            if(destinatario == null){
                destinatario = ((Circular) documento).getDestinatarios().clone();
            }
            for(((Circular)documento).getDestinatarios() )
         }
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
     * @return true se nenhum documento foi adicionado ainda
     */
    public boolean estaVazio() {
        return quantidadeDocumentos == 0;
    }
}
