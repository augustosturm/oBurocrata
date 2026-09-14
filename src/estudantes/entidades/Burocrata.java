package estudantes.entidades;

import java.util.Arrays;
import java.util.Comparator;

import professor.entidades.*;

/**
 * Classe que traz a lógica do algoritmo de organização e despacho de processos.
 * <br><br>
 * Você pode incluir novos atributos e métodos nessa classe para criar
 * lógicas mais complexas para o gerenciamento da organização e despacho de 
 * processos, mas eles não serão invocados diretamente pelo simulador e devem
 * respeitar propriedades de encapsulamento e coesão.
 * 
 * @author Augusto, Gabriel e Matheus
 */
public class Burocrata {
    /** Estresse acumulado pelo burocrata durante a execução. */
    private int estresse = 0;

    /** Mesa que contém os processos sob responsabilidade do burocrata. */
    private Mesa mesa;

    /** Universidade que fornece os documentos e recebe os processos despachados. */
    private Universidade universidade;

    /** Superprocessos utilizados para agrupar documentos antes do despacho. */
    public Superprocesso[] superprocessos;
  
    
    /**
     * Construtor de Burocrata.
     * 
     * @param m mesa com os processos
     * @param u universidade com os montes dos cursos e a secretaria
     */
    public Burocrata(Mesa m, Universidade u){
        this.mesa = m;
        this.universidade = u;
        superprocessos = new Superprocesso[5];
    }
    
    /**
     * Executa a lógica de criação e despacho dos processos.
     * <br><br>
     * Esse método é o único método de controle invocado durante a simulação 
     * da universidade.
     * <br><br>
     * Aqui podem ser feitas todas as verificações sobre os documentos nos 
     * montes dos cursos e dos processos abertos na mesa do Burocrata. A partir 
     * dessas informações, você pode colocar documentos nos processos abertos
     * e despachar os processos para a secretaria acadêmica.
     * <br><br>
     * Cuidado com a complexidade do seu algoritmo, porque se ele demorar muito
     * serão criados menos documentos na sua execução e sua produtividade geral
     * vai cair.
     * <br><br>
     * Esse método será chamado a cada 50 milissegundos pelo simulador da
     * universidade.
     * <br><br>
     * <strong>O burocrata não pode manter documentos com ele</strong> depois
     * que o método trabalhar terminar de executar, ou seja, você deve devolver
     * para os montes dos cursos todos os documentos que você removeu dos montes
     * dos cursos.
     * 
     * @see professor.entidades.Universidade#despachar(Processo)
     * @see professor.entidades.Universidade#removerDocumentoDoMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso)
     * @see professor.entidades.Universidade#devolverDocumentoParaMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso) 
     */
    public void trabalhar(){
        /**
         * Sincroniza o vetor de superprocessos com os processos da mesa.
         * Quando um processo é despachado, a mesa coloca um Processo novo no
         * mesmo índice; a comparação por referência detecta essa troca.
         */
        for (int i = 0; i < superprocessos.length; i++) {
            Processo atual = mesa.getProcesso(i);

            if (atual == null) {
                superprocessos[i] = null;
            } else if (superprocessos[i] == null || superprocessos[i].getProcesso() != atual) {
                superprocessos[i] = new Superprocesso(atual);
            }
        }

        /**
         * Exemplo: pegar um documento de um curso e colocá-lo em um processo.
         * Esqueleto do fluxo básico. A escolha de qual documento e de qual
         * processo ainda será desenvolvida; como podeReceber() recusa tudo por
         * enquanto, nenhum documento é movido de fato.
         */
    
        /**
         * Prioriza o curso com mais documentos acumulados no monte a cada ciclo.
         * Isso evita que um curso fique starving: se ele nunca consegue
         * despachar (ex.: pós-graduação sempre barrada pela regra 1 nos
         * superprocessos já tomados por graduação), o monte dele cresce mais
         * que o dos outros e ele passa a ser o primeiro a tentar qualquer
         * superprocesso recém-esvaziado.
         */
        CodigoCurso[] ordemPrioridade = CodigoCurso.values();
        Arrays.sort(ordemPrioridade, Comparator.comparingInt(universidade::contarDocumentosNoMonteDoCurso).reversed());

        for(CodigoCurso curso: ordemPrioridade){

            Documento[] monte = universidade.pegarCopiaDoMonteDoCurso(curso);

            for (Documento documento : monte) {
                /**
                 * Best-fit: em vez de usar o primeiro superprocesso que aceitar o
                 * documento, avalia todos os candidatos e escolhe o que sobrar
                 * menos espaço depois de inserido. Isso mantém mais superprocessos
                 * vazios disponíveis para documentos substanciais (regra 4).
                 */
                Superprocesso melhor = null;
                int menorSobra = Integer.MAX_VALUE;

                for (Superprocesso superprocesso : superprocessos) {
                    if (superprocesso == null || !superprocesso.isAtivo()) {
                        continue;
                    }
                    if (!superprocesso.podeReceber(documento)) {
                        continue;
                    }
                    int sobra = superprocesso.getPaginasRestantes() - documento.getPaginas();
                    if (sobra < menorSobra) {
                        menorSobra = sobra;
                        melhor = superprocesso;
                    }
                }

                if (melhor != null) {
                    /**
                     * Só adiciona ao processo o documento que foi realmente
                     * removido do monte, senão a Universidade acusa duplicata.
                     */
                    if (universidade.removerDocumentoDoMonteDoCurso(documento, curso)) {
                        melhor.adicionar(documento);
                    }
                }
                /**
                 * Se nenhum superprocesso aceitar, o documento nem chega a ser
                 * removido do monte, então não há nada para devolver.
                 */
            }
        }

        for(Superprocesso superprocesso:superprocessos){
            if (superprocesso != null && superprocesso.isAtivo()) {
                if (superprocesso.getTotalPaginas()>200 || superprocesso.getSubstancialValido()) {
                    universidade.despachar(superprocesso.getProcesso());
                    superprocesso.encerrar();
                }

            }

        }
    }


    
    /**
     * Retorna o valor atual de estresse do burocrata.
     * @return estresse atual
     */
    public int getEstresse(){
        return this.estresse;
    }
    
    /**
     * Aumenta o estresse do burocrata em uma unidade.
     * 
     * <strong>VOCÊ NÃO DEVERIA INVOCAR ESSE MÉTODO!!!</strong>
     */
    public void estressar(){
        this.estresse++;
    }
    
    /**
     * Aumenta o estresse do burocrata em 10 unidades.
     * 
     * <strong>VOCÊ NÃO DEVERIA INVOCAR ESSE MÉTODO!!!</strong>
     */
    public void estressarMuito(){
        this.estresse += 10;
    }
}