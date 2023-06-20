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


/**
 * ComentarioController
 * Classe controladora responsável por exibir um criador de comentário de um post.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @see com.otavio.wenews.newsletter.UserPainel;
 * @see com.otavio.wenews.newsletter.person.User;
 * @see com.otavio.wenews.newsletter.posts.Postagem;
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
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

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
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

    /**
     * Define os dados de um comentário
     * @param us o painel de usuários
     * @param myUser o usuário atual
     * @param post o post ao qual o comentário está sendo feito
     */
    public void setData(UserPainel us, User myUser, Postagem post) {
        this.us = us;
        this.myUser = myUser;
        this.post = post;
    }
}
