package com.otavio.wenews.controllers.users_controller;

import com.otavio.wenews.Main;
import com.otavio.wenews.controllers.posts_controller.PostController;
import com.otavio.wenews.controllers.posts_controller.PostagemPreviewController;
import com.otavio.wenews.newsletter.Sistema;
import com.otavio.wenews.newsletter.UserPainel;
import com.otavio.wenews.newsletter.Utils;
import com.otavio.wenews.newsletter.person.User;
import com.otavio.wenews.newsletter.posts.Artigo;
import com.otavio.wenews.newsletter.posts.Noticia;
import com.otavio.wenews.newsletter.posts.Postagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.util.Locale;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    private UserPainel us;
    private List<Postagem> posts = new ArrayList<>();
    private User myUser;
    private ObservableList<Pane> list;
    @FXML
    private Button nameUser;
    @FXML
    private Button sair;
    @FXML
    private Button noticias;
    @FXML
    private Button artigos;
    @FXML
    private BorderPane bp;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        artigos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                artigos.getStyleClass().add("botaoSelected");
                loadList("artigo");
                noticias.getStyleClass().remove("botaoSelected");
            }
        });

        noticias.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                artigos.getStyleClass().remove("botaoSelected");
                noticias.getStyleClass().add("botaoSelected");
                loadList("noticia");
            }
        });

        nameUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                FXMLLoader loader = null;
                try{
                    loader = new FXMLLoader(Main.class.getResource("user-info-view.fxml"));
                    root = loader.load();
                    UserInfosController userInfos = loader.getController();
                    userInfos.setData(us,myUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                bp.setCenter(root);
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sistema sis = Utils.getSistema();
                myUser = null;
                us = null;
                sis.sair(event);
            }
        });
    }

    public void setUser(User myUser,UserPainel us) {
        this.myUser = myUser;
        this.us = us;
        posts = us.loadAllPosts();
        nameUser.setText(myUser.getName());
        noticias.fire();
    }

    public void loadList(String tipo) {
        list = FXCollections.observableArrayList();
        ListView list2 = new ListView<String>();

        for (Postagem postagem : posts) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
                if(postagem instanceof Noticia && tipo.equals("noticia")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("post-preview.fxml"));
                    Pane pane = fxmlLoader.load();
                    pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Parent root = null;
                            FXMLLoader loader = null;
                            try{
                                loader = new FXMLLoader(Main.class.getResource("post-view.fxml"));
                                root = loader.load();
                                PostController postCont = loader.getController();
                                postCont.setData(us,myUser,postagem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            bp.setCenter(root);
                        }
                    });
                    PostagemPreviewController postagemController = fxmlLoader.getController();
                    postagemController.setDataPostPreview(postagem.getTitulo(),postagem.getSubTitulo(),postagem.getDataPostagem().format(formatter),((Noticia) postagem).getProprietario().getName());
                    list.add(pane);
                } else if (postagem instanceof Artigo && tipo.equals("artigo")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("post-preview.fxml"));
                    Pane pane = fxmlLoader.load();
                    pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Parent root = null;
                            FXMLLoader loader = null;
                            try{
                                loader = new FXMLLoader(Main.class.getResource("post-view.fxml"));
                                root = loader.load();
                                PostController postCont = loader.getController();
                                postCont.setData(us,myUser,postagem);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            bp.setCenter(root);
                        }
                    });
                    PostagemPreviewController postagemController = fxmlLoader.getController();
                    postagemController.setDataPostPreview(postagem.getTitulo(),postagem.getSubTitulo(),postagem.getDataPostagem().format(formatter),((Artigo) postagem).getProprietario().getName());
                    list.add(pane);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        list2.setItems(list);
        list2.setPrefWidth(1097);
        list2.setPrefHeight(609);
        list2.getStyleClass().add("list-view-transparent");
        bp.setCenter(list2);
    }

}
