package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

public abstract class Deliberacao extends DocumentoAdministrativo {

    String texto;

    public Deliberacao(String criador, CodigoCurso codigoCurso, int paginas, String texto) {
        super(criador, codigoCurso, paginas);
        this.texto = texto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.texto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Deliberacao other = (Deliberacao) obj;
        return Objects.equals(this.texto, other.texto);
    }

    
}
