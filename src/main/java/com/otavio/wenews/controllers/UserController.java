package com.otavio.wenews.controllers;

import com.otavio.wenews.newsletter.person.User;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    private User myUser;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUser(User myUser) {
        this.myUser = myUser;
    }
}
