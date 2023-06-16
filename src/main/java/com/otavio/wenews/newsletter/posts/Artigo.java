package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.employe.Escritor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Artigo extends Postagem implements Serializable {
    private String tema;
    private Escritor proprietario;

    public Artigo(LocalDateTime dataPostagem, String titulo, String subTitulo, List<String> paragrafos, String tema, Escritor proprietario) {
        super(dataPostagem, titulo, subTitulo, paragrafos);
        this.tema = tema;
        this.proprietario = proprietario;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Escritor getProprietario() {
        return proprietario;
    }

    public void setProprietario(Escritor proprietario) {
        this.proprietario = proprietario;
    }
}
