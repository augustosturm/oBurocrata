package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa um atestado que é um Registro.
 * <br><br>
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Atestado extends Registro {
    private String descricao;
    private String categoria;

    /**
     * Construtor da classe Registro.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param autenticacao Código de autenticação do documento
     * @param estudante Nome do estudante associado ao registro
     * @param matricula Número de matrícula do estudante
     * @param descricao Descrição do que é o atestado
     * @param categoria Categoria do atestado
     */
    public Atestado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao, String categoria) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(descricao, categoria);
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
        Atestado other = (Atestado) obj;
        return Objects.equals(descricao, other.descricao) && Objects.equals(categoria, other.categoria);
    }
    
    
}
