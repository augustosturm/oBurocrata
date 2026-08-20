package estudantes.entidades;

import java.util.Arrays;
import java.util.Objects;

import professor.entidades.CodigoCurso;

/**
 * Classe que representa o historico de registro.
 * <br><br>
 *
 * @author Augusto, Gabriel e Matheus
 */
public class Historico extends Registro {
    private double coeficiente;
    private String[] componentes;

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
     * @param coeficiente Coeficiente
     * @param componentes Componentes
     */
    public Historico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
            long matricula, double coeficiente, String[] componentes) {

        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.coeficiente = coeficiente;
        this.componentes = componentes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(componentes);
        result = prime * result + Objects.hash(coeficiente);
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
        Historico other = (Historico) obj;
        return Double.doubleToLongBits(coeficiente) == Double.doubleToLongBits(other.coeficiente)
                && Arrays.equals(componentes, other.componentes);
    }

    

}
