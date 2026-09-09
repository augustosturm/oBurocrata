package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

public class Portaria extends Norma {
    private int anoInicio;

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
