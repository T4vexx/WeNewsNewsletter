package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.interfaces.Postaveis;
import com.otavio.wenews.newsletter.employe.Jornalista;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Noticia
 * Classe que representa uma notícia, um tipo de Postagem.
 * Estende postagem e implementa serializable, para poder ser armazenada em um banco de dados
 * Possui campos mais especializados relacionados a notícias
 *
 * @see com.otavio.wenews.newsletter.employe.Jornalista
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Noticia extends Postagem implements Serializable, Postaveis {
    private String fonte;
    private String categoria;
    private Jornalista proprietario;


    /**
     * Construtor que inicializa a notícia com a data de postagem, título, subtítulo, fonte, categoria e proprietário especificados
     * @param dataPostagem a data de postagem da notícia
     * @param titulo o título da notícia
     * @param subTitulo o subtítulo da notícia
     * @param fonte a fonte da notícia
     * @param categoria a categoria da notícia
     * @param proprietario o jornalista proprietário da notícia
     */
    public Noticia(LocalDateTime dataPostagem, String titulo, String subTitulo, String fonte, String categoria, Jornalista proprietario) {
        super(dataPostagem, titulo, subTitulo);
        this.fonte = fonte;
        this.categoria = categoria;
        this.proprietario = proprietario;
    }

    /**
     * Construtor padrão que inicializa uma notícia
     */
    public Noticia() {}

    /**
     * Método que obtém a fonte da notícia
     * @return a fonte da notícia
     */
    public String getFonte() {
        return fonte;
    }

    /**
     * Método que define a fonte da notícia
     * @param fonte a fonte da notícia
     */
    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    /**
     * Método que obtém a categoria da notícia
     * @return a categoria da notícia
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Método que define a categoria da notícia
     * @param categoria a categoria da notícia
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Método que obtém o jornalista proprietário da notícia
     * @return o jornalista proprietário da notícia
     */
    public Jornalista getProprietario() {
        return proprietario;
    }

    /**
     * Método que define o jornalista proprietário da notícia
     * @param proprietario o jornalista proprietário da notícia
     */
    public void setProprietario(Jornalista proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String getTitulo() {
        return super.getTitulo();
    }
}
