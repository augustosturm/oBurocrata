package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

/**
 * Classe que representa um documento genérico.
 * <br><br>
 * 
 * @author Augusto, Gabriel e Matheus
 */
public abstract class Documento {
    private String criador;
    private CodigoCurso codigoCurso;
    private int paginas;
    /**
     * Construtor da classe Documento.
     * 
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     */
    
    public Documento(String criador, CodigoCurso codigoCurso, int paginas){
        this.criador = criador;
        this.codigoCurso = codigoCurso;
        this.paginas = paginas;
    }
	@Override
	public int hashCode() {
		return Objects.hash(criador, codigoCurso, Integer.valueOf(paginas));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		return Objects.equals(criador, other.criador) && codigoCurso == other.codigoCurso && paginas == other.paginas;
	}
}
