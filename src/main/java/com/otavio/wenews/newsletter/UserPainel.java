package com.otavio.wenews.newsletter;

import com.otavio.wenews.newsletter.person.User;
import javafx.event.ActionEvent;

public class UserPainel {
    private User myUser;

    public UserPainel(User myUser, ActionEvent event) {
        this.myUser = myUser;
        Utils.changeScene(event,"user","WeNews | Home page",600, 400);
    }
}
