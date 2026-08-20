package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa um documento acadêmico, autenticado por um código.
 * <br><br>
 *
 * @author coloque os nomes dos autores aqui
 */
public abstract class DocumentoAcademico extends Documento{
    private long autenticacao;

    /**
     * Construtor da classe DocumentoAcademico.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param autenticacao Código de autenticação do documento
     */
    public DocumentoAcademico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao) {
        super(criador, codigoCurso, paginas);
        this.autenticacao = autenticacao;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(autenticacao);
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
		DocumentoAcademico other = (DocumentoAcademico) obj;
		return autenticacao == other.autenticacao;
	}    
}
