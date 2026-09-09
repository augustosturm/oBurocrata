package estudantes.entidades;

import java.util.Arrays;
import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa um edital que é uma Norma.
 * <br><br>
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Edital extends Norma {
    private String[] responsaveis;

    /**
     * Construtor da classe Edital.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param numero Número da norma
     * @param valido Indica se a norma está válida
     * @param texto Texto da norma
     * @param responsaveis Nomes dos responsáveis pelo edital
     */
    public Edital(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto,
            String[] responsaveis) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.responsaveis = responsaveis;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(responsaveis);
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
        Edital other = (Edital) obj;
        return Arrays.equals(responsaveis, other.responsaveis);
    }

}
