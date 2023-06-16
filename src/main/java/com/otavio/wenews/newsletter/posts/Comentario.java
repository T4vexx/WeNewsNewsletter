package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.person.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comentario implements Serializable {
    private User usuario;
    private String comentario;
    private LocalDateTime dataPostagem;

    public Comentario(User usuario, String comentario, LocalDateTime dataPostagem) {
        this.usuario = usuario;
        this.comentario = comentario;
        this.dataPostagem = dataPostagem;
    }

    public User getUsuario() {
        return usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }
}
