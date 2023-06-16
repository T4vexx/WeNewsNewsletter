package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.employe.Editor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public abstract class Postagem implements Serializable {
    private LocalDateTime dataPostagem;
    private LocalDateTime ultimaEdicao;
    private Editor editadoPor;
    private String titulo;
    private String subTitulo;
    private List<String> paragrafos;
    private ArrayList<Comentario> comentarios = new ArrayList<>();

    public Postagem(LocalDateTime dataPostagem, String titulo, String subTitulo, List<String> paragrafos) {
        this.dataPostagem = dataPostagem;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.paragrafos = paragrafos;
    }

    public LocalDateTime getUltimaEdicao() {
        return ultimaEdicao;
    }

    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public List<String> getParagrafos() {
        return paragrafos;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setUltimaEdicao(LocalDateTime ultimaEdicao) {
        this.ultimaEdicao = ultimaEdicao;
    }

    public void addcComentarios(Comentario c) {
        comentarios.add(c);
    }

    public Editor getEditadoPor() {
        return editadoPor;
    }

    public void setEditadoPor(Editor editadoPor) {
        this.editadoPor = editadoPor;
    }
}

