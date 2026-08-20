package estudantes.entidades;

import java.util.Arrays;

public class Circular extends Deliberacao {
    String[] destinatarios;

    public Circular(String criador, professor.entidades.CodigoCurso codigoCurso, int paginas, String texto, String[] destinatarios) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatarios = destinatarios;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(destinatarios);
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
        Circular other = (Circular) obj;
        return Arrays.equals(destinatarios, other.destinatarios);
    }
}
