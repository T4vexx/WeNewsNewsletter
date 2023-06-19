package com.otavio.wenews.controllers.users_controller;

import com.otavio.wenews.Main;
import com.otavio.wenews.exceptions.LoginMissException;
import com.otavio.wenews.newsletter.UserPainel;
import com.otavio.wenews.newsletter.person.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfosController implements Initializable {

    private UserPainel us;
    private User myUser;
    @FXML
    private TextField nome;
    @FXML
    private TextField email;
    @FXML
    private TextField cpf;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    @FXML
    private Button changePassword;
    @FXML
    private Label message;
    @FXML
    private BorderPane bp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        changePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!oldPassword.getText().equals("") && !newPassword.getText().equals("")) {
                    if(!oldPassword.getText().equals(newPassword.getText())) {
                        try {
                            us.changePassword(oldPassword.getText(),newPassword.getText());
                            message.setText("Senha alterada com sucesso");
                            message.setTextFill(Paint.valueOf("#38A169"));
                        } catch (LoginMissException ex) {
                            message.setText(ex.getMessage());
                            message.setTextFill(Paint.valueOf("#E53E3E"));
                        }
                    } else {
                        message.setText("Sua senha antiga nao pode ser igual a atual!");
                        message.setTextFill(Paint.valueOf("#E53E3E"));
                    }
                } else {
                    message.setText("Preencha todos os campos acima!");
                    message.setTextFill(Paint.valueOf("#E53E3E"));
                }
            }
        });

    }

    public void setData(UserPainel us, User myUser) {
        this.us = us;
        this.myUser = myUser;
        nome.setText(myUser.getName());
        blockTextFied(nome);
        email.setText(myUser.getEmail());
        blockTextFied(email);
        cpf.setText(myUser.getCpf());
        blockTextFied(cpf);

        if(myUser.isUserSubscriber()) {
            Parent root = null;
            FXMLLoader loader = null;
            try{
                loader = new FXMLLoader(Main.class.getResource("subscriber-info-view.fxml"));
                root = loader.load();
                SubscriberController subCont = loader.getController();
                subCont.setData(us,myUser.getInscricao());
            } catch (Exception e) {
                e.printStackTrace();
            }
            bp.setCenter(root);
        }
    }

    public void blockTextFied(TextField tx) {
        tx.setEditable(false);
        tx.setMouseTransparent(true);
    }
}
