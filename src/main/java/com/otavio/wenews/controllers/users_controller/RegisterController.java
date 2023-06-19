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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    private Label message1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Sistema sis = Utils.getSistema();
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!nomeCompleto.getText().equals("") && !email.getText().equals("") && !senha.getText().equals("") && !cpf.getText().equals("")) {
                    String passwordRegex = "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})[A-Za-z0-9!~<>,;:_=?*+#.”&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]{8,32}$";
                    String cpfRegex = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
                    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                    if(email.getText().matches(emailRegex)) {
                        if(cpf.getText().matches(cpfRegex)) {
                            if(senha.getText().matches(passwordRegex)) {
                                try {
                                    sis.register(nomeCompleto.getText(),email.getText(),cpf.getText(),senha.getText());
                                    message.setTextFill(Paint.valueOf("#38A169"));
                                    message.setText("Registro Efetuado com sucesso | Agora faça login");
                                    message1.setText("");

                                }catch (LoginMissException ex) {
                                    message.setTextFill(Paint.valueOf("#E53E3E"));
                                    message.setText(ex.getMessage());
                                }
                            } else {
                                message.setTextFill(Paint.valueOf("#E53E3E"));
                                message.setText("Senha deve conter: letras maiúsculas e minúsculas");
                                message1.setTextFill(Paint.valueOf("#E53E3E"));
                                message1.setText("Mais de 4 caracteres e deve conter números e caracteres especiais");
                            }
                        } else {
                            message.setTextFill(Paint.valueOf("#E53E3E"));
                            message.setText("CPF inválido");
                        }
                    } else {
                        message.setTextFill(Paint.valueOf("#E53E3E"));
                        message.setText("Email inválido");
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
