package com.otavio.wenews.newsletter.posts;

import com.otavio.wenews.newsletter.employe.Editor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Postagem
 * Classe abstrata que representa uma postagem genérica.
 * Possui todos os atributos em comum entre um artigo e uma notícia
 *
 * @see com.otavio.wenews.newsletter.employe.Editor
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public abstract class Postagem implements Serializable {
    private LocalDateTime dataPostagem;
    private LocalDateTime ultimaEdicao;
    private Editor editadoPor;
    private String titulo;
    private String subTitulo;
    private List<String> paragrafos;
    private ArrayList<Comentario> comentarios;

    /**
     * Construtor que inicializa a postagem com a data de postagem, título e subtítulo especificados.
     * @param dataPostagem a data de postagem da postagem
     * @param titulo o título da postagem
     * @param subTitulo o subtítulo da postagem
     */
    public Postagem(LocalDateTime dataPostagem, String titulo, String subTitulo) {
        this.dataPostagem = dataPostagem;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        paragrafos = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    /**
     * Construtor padrão que inicializa a postagem com listas vazias de parágrafos e comentários.
     */
    public Postagem() {
        paragrafos = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    /**
     * Método que obtém a data da última edição da postagem
     * @return a data da última edição da postagem
     */
    public LocalDateTime getUltimaEdicao() {
        return ultimaEdicao;
    }

    /**
     * Método que obtém a data de postagem da postagem
     * @return a data de postagem da postagem
     */
    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }

    /**
     * Método que obtém o título da postagem
     * @return o título da postagem
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método que obtém o subtítulo da postagem
     * @return o subtítulo da postagem
     */
    public String getSubTitulo() {
        return subTitulo;
    }

    /**
     * Método que obtém a lista de parágrafos da postagem
     * @return a lista de parágrafos da postagem
     */
    public List<String> getParagrafos() {
        return paragrafos;
    }

    /**
     * Método que obtém a lista de comentários da postagem
     * @return a lista de comentários da postagem
     */
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Método que define a data da última edição da postagem
     * @param ultimaEdicao a data da última edição da postagem
     */
    public void setUltimaEdicao(LocalDateTime ultimaEdicao) {
        this.ultimaEdicao = ultimaEdicao;
    }

    /**
     * Método que adiciona um comentário à postagem
     * @param c o comentário a ser adicionado
     */

    public void addcComentarios(Comentario c) {
        comentarios.add(c);
    }

    /**
     * Método que obtém o editor responsável pela edição da postagem
     * @return o editor responsável pela edição da postagem
     */
    public Editor getEditadoPor() {
        return editadoPor;
    }

    /**
     * Método que define o editor responsável pela edição da postagem
     * @param editadoPor o editor responsável pela edição da postagem
     */
    public void setEditadoPor(Editor editadoPor) {
        this.editadoPor = editadoPor;
    }

    /**
     * Método que adiciona um novo parágrafo à postagem
     * @param par o novo parágrafo a ser adicionado
     */
    public void addNewParagraf(String par) {
        if(par != null || !par.equals("")) {
            paragrafos.add(par);
        }
    }

    /**
     * Método que define a data de postagem da postagem
     * @param dataPostagem a data de postagem da postagem
     */
    public void setDataPostagem(LocalDateTime dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    /**
     * Método que define o título da postagem
     * @param titulo o título da postagem
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método que define o sub título da postagem
     * @param subTitulo o sub título da postagem
     */
    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    /**
     * Método que define a lista de comentarios
     * @param comentarios os comentarios de uma postagem
     */
    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}

