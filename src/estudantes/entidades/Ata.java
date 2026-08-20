package estudantes.entidades;

import java.util.Arrays;
import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa uma ata de aula.
 * <br><br>
 *
 * @author coloque os nomes dos autores aqui
 */
public class Ata extends Documento{
    private int numero;
    private String texto;
    private String[] presentes;

    /**
     * Construtor da classe Ata.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param numero Número da ata
     * @param texto Texto da ata
     * @param presentes Vetor com os nomes dos presentes na aula
     */
    public Ata(String criador, CodigoCurso codigoCurso, int paginas, int numero, String texto, String[] presentes) {
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.texto = texto;
        this.presentes = presentes;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(presentes);
		result = prime * result + Objects.hash(numero, texto);
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
		return numero == other.numero && Objects.equals(texto, other.texto)
				&& Arrays.equals(presentes, other.presentes);
	}
}
