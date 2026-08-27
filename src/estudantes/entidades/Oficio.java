package estudantes.entidades;


import java.util.Objects;

import professor.entidades.CodigoCurso;

public class Oficio extends Deliberacao {
    private String destinatario;

    public Oficio(String criador, CodigoCurso codigoCurso, int paginas,String texto,String destinatarioString) {
        super(criador, codigoCurso, paginas,texto);
        this.destinatario = destinatarioString;

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
        Oficio other = (Oficio) obj; 
		return Objects.equals(destinatario, other.destinatario);
	}   
     @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hashCode(destinatario);
		return result;
	}
    public String getDestinatario(){
        return destinatario;
    }
}

