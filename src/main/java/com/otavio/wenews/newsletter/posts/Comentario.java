package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.person.User;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Comentario
 * Classe que representa um comentário em uma postagem.
 *
 * @see com.otavio.wenews.newsletter.person.User
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Comentario implements Serializable {
    private User usuario;
    private String comentario;
    private LocalDateTime dataPostagem;

    /**
     * Construtor que inicializa o comentário com o usuário, o texto do comentário e a data de postagem especificados
     * @param usuario o usuário que fez o comentário
     * @param comentario o texto do comentário
     * @param dataPostagem a data de postagem do comentário
     */
    public Comentario(User usuario, String comentario, LocalDateTime dataPostagem) {
        this.usuario = usuario;
        this.comentario = comentario;
        this.dataPostagem = dataPostagem;
    }

    /**
     * Método que obtém o usuário que fez o comentário
     * @return o usuário que fez o comentário
     */
    public User getUsuario() {
        return usuario;
    }

    /**
     * Método que obtém o texto do comentário
     * @return o texto do comentário
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Método que obtém a data de postagem do comentário
     * @return a data de postagem do comentário
     */
    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }
}
