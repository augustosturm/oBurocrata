package estudantes.entidades;

import professor.entidades.CodigoCurso;

public abstract class DocumentoAdministrativo extends Documento {

    /**
     * @param criador
     * @param codigoCurso
     * @param paginas
     */
    public DocumentoAdministrativo (String criador, CodigoCurso codigoCurso, int paginas) {
        super(criador, codigoCurso, paginas);
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
        DocumentoAdministrativo other = (DocumentoAdministrativo) obj;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        return result;
    }


    
}
