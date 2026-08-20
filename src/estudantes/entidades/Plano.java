package estudantes.entidades;

import java.util.Arrays;
import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa um plano de ensino.
 * <br><br>
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Plano extends DocumentoAcademico {
    private String responsavel;
    private String[] planejamento;

    /**
     * Construtor da classe Plano.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param autenticacao Código de autenticação do documento
     * @param responsavel Nome do responsável pelo plano
     * @param planejamento Vetor com o planejamento das aulas
     */
    public Plano(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String responsavel, String[] planejamento) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.responsavel = responsavel;
        this.planejamento = planejamento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(planejamento);
        result = prime * result + Objects.hash(responsavel);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Plano other = (Plano) obj;
        return Objects.equals(responsavel, other.responsavel) && Arrays.equals(planejamento, other.planejamento);
    }

    
}
