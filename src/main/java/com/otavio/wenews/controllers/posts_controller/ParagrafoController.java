package com.otavio.wenews.controllers.posts_controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ParagrafoController implements Initializable {
    @FXML
    private Label paragrafo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setParagrafo(String paragrafo) {
        this.paragrafo.setText(paragrafo);
    }
}
