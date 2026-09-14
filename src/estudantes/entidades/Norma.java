package estudantes.entidades;
import java.util.Objects;
import professor.entidades.CodigoCurso;

/**
 * Representa uma norma administrativa emitida por uma entidade.
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Norma extends DocumentoAdministrativo {
    /** Número de identificação da norma. */
    private int numero;

    /** Indica se a norma está válida. */
    private boolean valido;

    /** Texto descritivo do conteúdo da norma. */
    private String texto;

    /**
     * Cria uma norma administrativa.
     *
     * @param criador nome do criador da norma
     * @param codigoCurso código do curso associado à norma
     * @param paginas quantidade de páginas da norma
     * @param numero número de identificação da norma
     * @param valido indica se a norma está válida
     * @param texto conteúdo textual da norma
     */
    public Norma(String criador, CodigoCurso codigoCurso, int paginas,int numero,boolean valido,String texto){
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;
    }

    /**
     * Compara esta norma com outro objeto.
     *
     * @param obj objeto que será comparado com esta norma
     * @return {@code true} quando os objetos representam a mesma norma
     */
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

    /**
     * Calcula o código hash da norma.
     *
     * @return código hash baseado nos dados da norma
     */
    public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numero,valido,texto);
		return result;
	}

    /**
     * Verifica se a norma está válida.
     *
     * @return {@code true} se a norma estiver válida
     */
    public boolean isValido(){
        return this.valido;
    }

    /**
     * Retorna o texto da norma.
     *
     * @return texto da norma
     */
    public String getTexto(){
        return this.texto;
    }

    /**
     * Retorna o número da norma.
     *
     * @return número de identificação da norma
     */
    public int getNumero(){
        return this.numero;
    }
}
