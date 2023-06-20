package com.otavio.wenews.controllers.users_controller;

import com.otavio.wenews.newsletter.UserPainel;
import com.otavio.wenews.newsletter.person.Subscriber;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * SubscriberController
 * Classe controladora responsável por gerenciar as interações e funcionalidades do assinante no painel de assinante.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @see com.otavio.wenews.newsletter.UserPainel;
 * @see com.otavio.wenews.newsletter.person.Subscriber;
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class SubscriberController implements Initializable {

    private UserPainel us;
    private Subscriber sub;
    @FXML
    private TextField primeira;
    @FXML
    private TextField expiraEm;
    @FXML
    private Button renew;
    @FXML
    private Label message;


    /**
     * Método que é chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        renew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                us.renewSubscription(sub,event);
                message.setText("Inscrição atualizada com sucesso | Obrigado pro financiar nossa instituição");
                message.setTextFill(Paint.valueOf("#38A169"));
            }
        });
    }

    /**
     * Define os dados do painel de assinante.
     * Faz o bloqueio do botao caso o usuario nao esteja expirado com sua inscricão
     * @param us o painel de usuário
     * @param sub o assinante
     */
    public void setData(UserPainel us, Subscriber sub) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy  HH:mm", new Locale("pt", "BR"));
        primeira.setText(sub.getFirstSubscription().format(formatter));
        blockTextFied(primeira);
        expiraEm.setText(sub.getExpiresIn().format(formatter));
        blockTextFied(expiraEm);
        this.us = us;
        this.sub = sub;
        if(sub.isExpired()) {
            renew.setDisable(true);
        } else {
            renew.setDisable(false);
        }
    }

    /**
     * Bloqueia o campo de texto para edição
     * @param tx o campo de texto a ser bloqueado
     */
    public void blockTextFied(TextField tx) {
        tx.setEditable(false);
        tx.setMouseTransparent(true);
    }
}
