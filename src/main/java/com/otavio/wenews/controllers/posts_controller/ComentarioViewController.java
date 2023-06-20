package com.otavio.wenews.controllers.posts_controller;

import com.otavio.wenews.newsletter.posts.Comentario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ComentarioViewController
 * Classe controladora responsável por exibir um comentário de um post.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @see com.otavio.wenews.newsletter.posts.Comentario
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class ComentarioViewController implements Initializable {

    @FXML
    private TextArea comentario;
    @FXML
    private Label nome;
    @FXML
    private Label data;

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Método que cria o comentario com os dados corretos
     * @param comment comentário de um post
     */
    public void setData(Comentario comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        this.comentario.setText(comment.getComentario());
        this.comentario.setEditable(false);
        this.comentario.setMouseTransparent(true);
        this.data.setText(comment.getDataPostagem().format(formatter));
        this.nome.setText(comment.getUsuario().getName());
    }
}
