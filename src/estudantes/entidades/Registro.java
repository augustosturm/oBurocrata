package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa um registro acadêmico de um estudante.
 * <br><br>
 *
 * @author Augusto, Gabriel e Matheus
 */
public abstract class Registro extends DocumentoAcademico {
    private String estudante;
    private long matricula;

    /**
     * Construtor da classe Registro.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param autenticacao Código de autenticação do documento
     * @param estudante Nome do estudante associado ao registro
     * @param matricula Número de matrícula do estudante
     */
    public Registro(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.estudante = estudante;
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(estudante, matricula);
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
        Registro other = (Registro) obj;
        return Objects.equals(estudante, other.estudante) && matricula == other.matricula;
    }
}
