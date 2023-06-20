package com.otavio.wenews.controllers.posts_controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * ParagrafoController
 * Classe controladora responsável por exibir um parágrafo de um post.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class ParagrafoController implements Initializable {
    @FXML
    private Label paragrafo;

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Método que seta o texto do parágrafo
     * @param paragrafo parágrafo de um texto
     */
    public void setParagrafo(String paragrafo) {
        this.paragrafo.setText(paragrafo);
    }
}
