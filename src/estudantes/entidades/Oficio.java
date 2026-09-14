package estudantes.entidades;


import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Representa um ofício, documento administrativo destinado a um destinatário.
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Oficio extends Deliberacao {
	/** Pessoa ou entidade para quem o ofício é destinado. */
    private String destinatario;

	/**
	 * Cria um ofício.
	 *
	 * @param criador nome do criador do ofício
	 * @param codigoCurso código do curso associado ao ofício
	 * @param paginas quantidade de páginas do ofício
	 * @param texto conteúdo textual do ofício
	 * @param destinatarioString pessoa ou entidade destinatária do ofício
	 */
    public Oficio(String criador, CodigoCurso codigoCurso, int paginas,String texto,String destinatarioString) {
        super(criador, codigoCurso, paginas,texto);
        this.destinatario = destinatarioString;

    }

	/**
	 * Compara este ofício com outro objeto.
	 *
	 * @param obj objeto que será comparado com este ofício
	 * @return {@code true} quando os objetos representam o mesmo ofício
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
        Oficio other = (Oficio) obj; 
		return Objects.equals(destinatario, other.destinatario);
	}   

    /**
	* Calcula o código hash do ofício.
	*
	* @return código hash baseado nos dados do ofício
	*/
     @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hashCode(destinatario);
		return result;
	}

	/**
	 * Retorna o destinatário do ofício.
	 *
	 * @return pessoa ou entidade destinatária
	 */
    public String getDestinatario(){
        return destinatario;
    }
}

