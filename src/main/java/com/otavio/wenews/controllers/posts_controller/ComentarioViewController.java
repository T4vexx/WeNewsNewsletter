package com.otavio.wenews.controllers.posts_controller;

import com.otavio.wenews.newsletter.person.User;
import com.otavio.wenews.newsletter.posts.Comentario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class ComentarioViewController implements Initializable {

    @FXML
    private TextArea comentario;
    @FXML
    private Label nome;
    @FXML
    private Label data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Comentario comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        this.comentario.setText(comment.getComentario());
        this.comentario.setEditable(false);
        this.comentario.setMouseTransparent(true);
        this.data.setText(comment.getDataPostagem().format(formatter));
        this.nome.setText(comment.getUsuario().getName());
    }
}
