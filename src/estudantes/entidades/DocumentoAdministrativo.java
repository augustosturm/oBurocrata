package estudantes.entidades;

import professor.entidades.CodigoCurso;

public abstract class DocumentoAdministrativo extends Documento {
     
    
    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas) {
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
        return true;
	}    
    @Override
	public int hashCode() {
		return super.hashCode();
	}
}
