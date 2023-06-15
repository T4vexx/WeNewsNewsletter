package com.otavio.wenews;

import com.otavio.wenews.newsletter.DBFun;
import com.otavio.wenews.newsletter.Sistema;
import com.otavio.wenews.newsletter.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setResizable(false);
        stage.setTitle("WeNews | Log-in page");
        stage.getIcons().add(new Image("file:src/main/resources/images/email.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        new Utils();
        launch();
    }
}