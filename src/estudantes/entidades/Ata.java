package estudantes.entidades;

import java.util.Arrays;

import professor.entidades.CodigoCurso;

public class Ata extends Documento{
    private int numero;
    private String texto;
    private String[] presentes;

    public Ata(String criador, CodigoCurso codigoCurso, int paginas) {
        super(criador, codigoCurso, paginas);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numero;
        result = prime * result + ((texto == null) ? 0 : texto.hashCode());
        result = prime * result + Arrays.hashCode(presentes);
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
        Ata other = (Ata) obj;
        if (numero != other.numero)
            return false;
        if (texto == null) {
            if (other.texto != null)
                return false;
        } else if (!texto.equals(other.texto))
            return false;
        if (!Arrays.equals(presentes, other.presentes))
            return false;
        return true;
    }
}
