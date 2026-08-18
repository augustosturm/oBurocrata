package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

/**
 * Classe que representa um documento genérico.
 * <br><br>
 * <strong>Seu trabalho começa aqui...</strong>
 * 
 * @author coloque os nomes dos autores aqui
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Documento documento = (Documento) o;
        return paginas == documento.paginas &&
               Objects.equals(criador, documento.criador) &&
               Objects.equals(codigoCurso, documento.codigoCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criador, codigoCurso, paginas);
    }
}
