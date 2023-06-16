package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.employe.Jornalista;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Noticia extends Postagem implements Serializable {
    private String fonte;
    private String categoria;
    private Jornalista proprietario;


    public Noticia(LocalDateTime dataPostagem, String titulo, String subTitulo, List<String> paragrafos, String fonte, String categoria, Jornalista proprietario) {
        super(dataPostagem, titulo, subTitulo, paragrafos);
        this.fonte = fonte;
        this.categoria = categoria;
        this.proprietario = proprietario;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Jornalista getProprietario() {
        return proprietario;
    }

    public void setProprietario(Jornalista proprietario) {
        this.proprietario = proprietario;
    }
}
