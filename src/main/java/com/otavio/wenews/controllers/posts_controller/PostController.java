package com.otavio.wenews.controllers.posts_controller;

import com.otavio.wenews.Main;
import com.otavio.wenews.controllers.users_controller.UserController;
import com.otavio.wenews.newsletter.UserPainel;
import com.otavio.wenews.newsletter.Utils;
import com.otavio.wenews.newsletter.person.User;
import com.otavio.wenews.newsletter.posts.Artigo;
import com.otavio.wenews.newsletter.posts.Comentario;
import com.otavio.wenews.newsletter.posts.Noticia;
import com.otavio.wenews.newsletter.posts.Postagem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * PostController
 * Classe controladora responsável por exibir um post.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @see com.otavio.wenews.Main
 * @see com.otavio.wenews.controllers.users_controller.UserController
 * @see com.otavio.wenews.newsletter.UserPainel
 * @see com.otavio.wenews.newsletter.Utils
 * @see com.otavio.wenews.newsletter.person.User
 * @see com.otavio.wenews.newsletter.posts.Artigo
 * @see com.otavio.wenews.newsletter.posts.Comentario
 * @see com.otavio.wenews.newsletter.posts.Noticia;
 * @see com.otavio.wenews.newsletter.posts.Postagem
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class PostController implements Initializable {

    private UserPainel us;
    private User myUser;
    private Postagem post;
    @FXML
    private Label titulo;
    @FXML
    private Label subtitulo;
    @FXML
    private Label nome;
    @FXML
    private Label data;
    @FXML
    private Label adicional1;
    @FXML
    private Label adicional2;
    @FXML
    private BorderPane bpParagrafos;

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Método que define os dados do post a ser exibido
     * Também faz a exibição dos parágrafos e dos comentários
     * @param us o painel de usuários
     * @param myUser o usuário atual
     * @param post o post a ser exibido
     */
    public void setData(UserPainel us, User myUser, Postagem post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        this.us = us;
        this.myUser = myUser;
        this.post = post;
        titulo.setText(post.getTitulo());
        subtitulo.setText(post.getSubTitulo());
        data.setText(post.getDataPostagem().format(formatter));
        if(post instanceof Noticia) {
            nome.setText(((Noticia) post).getProprietario().getName());
            adicional1.setText(((Noticia) post).getCategoria());
            adicional2.setText(((Noticia) post).getFonte());
        } else if(post instanceof Artigo) {
            nome.setText(((Artigo) post).getProprietario().getName());
            adicional1.setText(((Artigo) post).getTema());
            adicional2.setText(((Artigo) post).getProtagonistas());
        }

        if(myUser.isUserSubscriber()){
            ObservableList<Pane> list = FXCollections.observableArrayList();
            ListView list2 = new ListView<String>();

            for (String par : post.getParagrafos()) {
                try {
                    if(!par.isEmpty()) {
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("paragrafo.fxml"));
                        Pane pane = fxmlLoader.load();
                        ParagrafoController parController = fxmlLoader.getController();
                        parController.setParagrafo(par);
                        list.add(pane);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("comentario.fxml"));
                Pane pane = fxmlLoader.load();
                ComentarioController comController = fxmlLoader.getController();
                comController.setData(us,myUser,post);
                list.add(pane);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            for(Comentario comment : post.getComentarios()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("comentario-view.fxml"));
                    Pane pane = fxmlLoader.load();
                    ComentarioViewController comController = fxmlLoader.getController();
                    comController.setData(comment);
                    list.add(pane);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }


            list2.setItems(list);
            list2.setPrefWidth(866);
            list2.setPrefHeight(245);
            list2.getStyleClass().add("list-view-transparent");
            bpParagrafos.setCenter(list2);
        } else {
            Button botao = new Button("Deseja continuar lendo? | Click aqui e ASSINE JA",new ImageView(Main.class.getResource("palmas.png").toExternalForm()));
            Label label = new Label();
            botao.getStyleClass().add("botaoSeInscreva");
            label.getStyleClass().add("labelSeInscreva");
            botao.getStylesheets().add(Main.class.getResource("posts.css").toExternalForm());
            label.getStylesheets().add(Main.class.getResource("posts.css").toExternalForm());
            bpParagrafos.setCenter(botao);
            BorderPane.setAlignment(label, Pos.CENTER);
            bpParagrafos.setBottom(label);
            botao.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    label.setText("Muito obrigado, agora vc faz parte da família news Letter | Você poderá ler as materias inteiras");
                    botao.setDisable(true);
                    us.giveUserSubscription(myUser);
                    CompletableFuture.delayedExecutor(4, TimeUnit.SECONDS).execute(() -> {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                FXMLLoader loader = Utils.changeScene(event,"user","WeNews | Home page",1220,700);
                                UserController userController = loader.getController();
                                userController.setUser(myUser,us);
                            }
                        });
                    });
                }
            });
        }
    }
}
