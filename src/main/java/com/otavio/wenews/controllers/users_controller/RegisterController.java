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

public class RegisterController implements Initializable {

    @FXML
    private TextField nomeCompleto;
    @FXML
    private TextField email;
    @FXML
    private TextField cpf;
    @FXML
    private TextField senha;
    @FXML
    private Button register;
    @FXML
    private Button changeToLogin;
    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Sistema sis = Utils.getSistema();
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!nomeCompleto.getText().equals("") && !email.getText().equals("") && !senha.getText().equals("") && !cpf.getText().equals("")) {
                    try {
                        sis.register(nomeCompleto.getText(),email.getText(),cpf.getText(),senha.getText());
                        message.setTextFill(Paint.valueOf("#38A169"));
                        message.setText("Registro Efetuado com sucesso | Agora faça login");

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

        changeToLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.changeScene(event,"login","WeNews | Login page",400, 600);
            }
        });
    }
}
