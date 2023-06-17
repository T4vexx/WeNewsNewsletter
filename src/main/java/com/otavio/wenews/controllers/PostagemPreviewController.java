package com.otavio.wenews.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PostagemPreviewController implements Initializable {
    @FXML
    private Label titulo;
    @FXML
    private Label subtitulo;
    @FXML
    private Label data;
    @FXML
    private Label autor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDataPostPreview(String titulo, String subtitulo, String data, String autor) {
        this.titulo.setText(titulo);
        this.subtitulo.setText(subtitulo);
        this.data.setText(data);
        this.autor.setText(autor);
    }
}
