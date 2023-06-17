package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.employe.Escritor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Artigo extends Postagem implements Serializable {
    private String tema;
    private String protagonistas;
    private Escritor proprietario;

    public Artigo(LocalDateTime dataPostagem, String titulo, String subTitulo, String tema, Escritor proprietario, String protagonista) {
        super(dataPostagem, titulo, subTitulo);
        this.tema = tema;
        this.proprietario = proprietario;
        this.protagonistas = protagonista;
    }

    public Artigo() {}

    public String getProtagonistas() {
        return protagonistas;
    }

    public void setProtagonistas(String protagonistas) {
        this.protagonistas = protagonistas;
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
