package estudantes.entidades;
import java.util.Objects;
import professor.entidades.CodigoCurso;

public class Norma extends DocumentoAdministrativo {
    private int numero;
    private boolean valido;
    private String texto;
    public Norma(String criador, CodigoCurso codigoCurso, int paginas,int numero,boolean valido,String texto){
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;
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
        Norma other = (Norma) obj;
		return  numero == other.numero && valido == other.valido && Objects.equals(texto, other.texto);
	}   
    public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numero,valido,texto);
		return result;
	}
    public boolean getValido(){
        return this.valido;
    }
    public String getTexto(){
        return this.texto;
    }
    public int getNumero(){
        return this.numero;
    }
}
