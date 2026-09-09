package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa uma portaria que é uma Norma.
 * <br><br>
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Portaria extends Norma {
    private int anoInicio;

    /**
     * Construtor da classe Portaria.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param numero Número da norma
     * @param valido Indica se a norma está válida
     * @param texto Texto da norma
     * @param anoInicio Ano de início da vigência da portaria
     */
    public Portaria(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto,
            int anoInicio) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.anoInicio = anoInicio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(anoInicio);
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
        Portaria other = (Portaria) obj;
        return anoInicio == other.anoInicio;
    }
    
}
