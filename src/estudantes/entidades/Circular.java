package estudantes.entidades;

import java.util.Arrays;
import java.util.Objects;

import professor.entidades.CodigoCurso;

public class Circular extends Deliberacao {
    private String [] destinatarios;

    public Circular(String criador, CodigoCurso codigoCurso, int paginas,String texto,String[] destinatariosStrings) {
        super(criador, codigoCurso, paginas,texto);
        this.destinatarios = destinatariosStrings;

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
     @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(destinatarios);
		return result;
	}
    public String[] getDestinatarios(){
        return destinatarios;
    }
}
