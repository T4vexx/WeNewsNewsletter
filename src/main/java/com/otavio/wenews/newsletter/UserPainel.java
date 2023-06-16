package com.otavio.wenews.newsletter;

import com.otavio.wenews.controllers.UserController;
import com.otavio.wenews.newsletter.person.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class UserPainel {
    private User myUser;

    public UserPainel(User myUser, ActionEvent event) {
        this.myUser = myUser;
        FXMLLoader loader = Utils.changeScene(event,"user","WeNews | Home page",1220,700);
        UserController userController = loader.getController();
        userController.setUser(myUser);
    }

    
}
