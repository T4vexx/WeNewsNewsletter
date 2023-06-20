package com.otavio.wenews.controllers.posts_controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * PostagemPreviewController
 * Classe controladora responsável por exibir um preview de um post.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class PostagemPreviewController implements Initializable {
    @FXML
    private Label titulo;
    @FXML
    private Label subtitulo;
    @FXML
    private Label data;
    @FXML
    private Label autor;

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    /**
     * Método que define os dados do post preview a ser exibido
     * @param titulo título de um post
     * @param subtitulo sub título de um post
     * @param data data formatada de um post
     * @param autor autor do post
     */
    public void setDataPostPreview(String titulo, String subtitulo, String data, String autor) {
        this.titulo.setText(titulo);
        this.subtitulo.setText(subtitulo);
        this.data.setText(data);
        this.autor.setText(autor);
    }
}
