package com.otavio.wenews;

import com.otavio.wenews.newsletter.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Esta classe é a classe principal da aplicação WeNews, que permite aos usuários se cadastrarem e receberem notícias.
 * <p>
 * A classe Main herda da classe Application do JavaFX e é responsável por carregar a cena inicial da interface gráfica, que é a tela de login.
 *
 * @see com.otavio.wenews.newsletter.DBFun
 * @see com.otavio.wenews.newsletter.Sistema
 * @see com.otavio.wenews.newsletter.Utils
 * @author Otavio Augusto teixiera
 * @version 1.0
 */
public class Main extends Application {
    /**
     * Este método é chamado quando a aplicação é iniciada e carrega a cena da tela de login a partir do arquivo FXML.
     * @param stage o palco principal da aplicação
     * @throws IOException se ocorrer algum erro ao carregar o arquivo FXML
     */
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

    /**
     * Este método é o ponto de entrada da aplicação e cria alguns usuários super para teste.
     * @param args os argumentos da linha de comando
     */
    public static void main(String[] args) {
        new Utils();
        Utils.genereteSuperUsuarios();
        launch();
    }
}
