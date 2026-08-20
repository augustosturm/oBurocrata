package estudantes.entidades;

import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa o historico de registro.
 * <br><br>
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Certificado extends Registro {
    private String descriacao;

    /**
     * Construtor da classe Registro.
     *
     * @param criador Nome do criador do documento
     * @param codigoCurso Código do curso associado ao documento
     * @param paginas Número de páginas do documento
     * @param autenticacao Código de autenticação do documento
     * @param estudante Nome do estudante associado ao registro
     * @param matricula Número de matrícula do estudante
     * @param descricao Descricao do certificado
     */
    public Certificado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
            long matricula, String descriacao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descriacao = descriacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(descriacao);
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
        Certificado other = (Certificado) obj;
        return Objects.equals(descriacao, other.descriacao);
    }

    
    
}
