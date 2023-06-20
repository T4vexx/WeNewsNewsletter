package com.otavio.wenews.controllers.users_controller;

import com.otavio.wenews.exceptions.LoginMissException;
import com.otavio.wenews.newsletter.Sistema;
import com.otavio.wenews.newsletter.Utils;
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
 * LoginController
 * Classe controladora responsável pelo login de usuários.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField senha;
    @FXML
    private Button logar;
    @FXML
    private Button changeToRegister;
    @FXML
    private Label message;

    /**
     * Método que é chamado ao inicializar o controlador
     * @param location o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Sistema sis = Utils.getSistema();

        logar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!email.getText().equals("") && !senha.getText().equals("")) {
                    try {
                        sis.login(email.getText(),senha.getText(),event);
                    }catch (LoginMissException ex) {
                        message.setTextFill(Paint.valueOf("#E53E3E"));
                        message.setText(ex.getMessage());
                    }
                } else {
                    message.setTextFill(Paint.valueOf("#E53E3E"));
                    message.setText("Todas as informações devem ser prenchidas");
                }
            }
        });

        changeToRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.changeScene(event,"register","WeNews | Register page",400, 600);
            }
        });
    }
}
