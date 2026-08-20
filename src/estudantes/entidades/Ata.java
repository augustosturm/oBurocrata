package estudantes.entidades;

import java.util.Arrays;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ata ata = (Ata) o;
        return numero == ata.numero &&
               Objects.equals(texto, ata.texto) &&
               Arrays.equals(presentes, ata.presentes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numero, texto, Arrays.hashCode(presentes));
    }
}
