package com.otavio.wenews.controllers.posts_controller;

import com.otavio.wenews.newsletter.UserPainel;
import com.otavio.wenews.newsletter.person.User;
import com.otavio.wenews.newsletter.posts.Postagem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class ComentarioController implements Initializable {

    private User myUser;
    private UserPainel us;
    private Postagem post;
    @FXML
    private TextField comentario;
    @FXML
    private Button enviarComentario;
    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        enviarComentario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    us.addNewComentToAPost(myUser,post,comentario.getText(),event);
                    message.setTextFill(Paint.valueOf("#38A169"));
                    message.setText("Comentário registrado com sucesso !");
                } catch (Exception ex) {
                    message.setTextFill(Paint.valueOf("#E53E3E"));
                    message.setText(ex.getMessage());
                }
            }
        });
    }

    public void setData(UserPainel us, User myUser, Postagem post) {
        this.us = us;
        this.myUser = myUser;
        this.post = post;
    }
}
