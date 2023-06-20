package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.employe.Escritor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Artigo
 * Classe que representa um Artigo, um tipo de Postagem.
 * Extende postagem e implementa serializable, para poder ser armazenada em um banco de dados
 * Possui campos mais especializados relacionados a um artigo
 *
 * @see com.otavio.wenews.newsletter.employe.Escritor
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Artigo extends Postagem implements Serializable {
    private String tema;
    private String protagonistas;
    private Escritor proprietario;

    /**
     * Construtor que inicializa o artigo com a data de postagem, o título, o subtítulo, o tema, o escritor proprietário e os protagonistas especificados
     * @param dataPostagem a data de postagem do artigo
     * @param titulo o título do artigo
     * @param subTitulo o subtítulo do artigo
     * @param tema o tema do artigo
     * @param proprietario o escritor proprietário do artigo
     * @param protagonista os protagonistas do artigo
     */
    public Artigo(LocalDateTime dataPostagem, String titulo, String subTitulo, String tema, Escritor proprietario, String protagonista) {
        super(dataPostagem, titulo, subTitulo);
        this.tema = tema;
        this.proprietario = proprietario;
        this.protagonistas = protagonista;
    }

    /**
     * Construtor padrão para um artigo.
     */
    public Artigo() {}

    /**
     * Método que obtém os protagonistas do artigo
     * @return os protagonistas do artigo
     */
    public String getProtagonistas() {
        return protagonistas;
    }

    /**
     * Método que define os protagonistas do artigo
     * @param protagonistas os protagonistas do artigo
     */
    public void setProtagonistas(String protagonistas) {
        this.protagonistas = protagonistas;
    }

    /**
     * Método que obtém o tema do artigo
     * @return o tema do artigo
     */
    public String getTema() {
        return tema;
    }

    /**
     * Método que define o tema do artigo
     * @param tema o tema do artigo
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * Método que obtém o escritor proprietário do artigo
     * @return o escritor proprietário do artigo
     */
    public Escritor getProprietario() {
        return proprietario;
    }

    /**
     * Método que define o escritor proprietário do artigo
     * @param proprietario o escritor proprietário do artigo
     */
    public void setProprietario(Escritor proprietario) {
        this.proprietario = proprietario;
    }
}
