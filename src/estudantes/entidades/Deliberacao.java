package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

public abstract class Deliberacao extends DocumentoAdministrativo {
     private String texto;

    public Deliberacao(String criador, CodigoCurso codigoCurso, int paginas,String texto) {
        super(criador, codigoCurso, paginas);
        this.texto = texto;
    }
    public String getTexto(){
        return this.texto;
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
        Deliberacao other = (Deliberacao) obj;
		return Objects.equals(texto,other.texto);
	}    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(texto);
		return result;
	}
}
