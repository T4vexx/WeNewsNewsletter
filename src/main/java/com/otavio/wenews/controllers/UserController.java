package com.otavio.wenews.controllers;

import com.otavio.wenews.Main;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private Label nameUser;
    @FXML
    private Button sair;
    @FXML
    private Button noticias;
    @FXML
    private Button artigos;
    @FXML
    private Button subscriber;
    @FXML
    private BorderPane bp;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        artigos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                artigos.getStyleClass().add("botaoSelected");
                noticias.getStyleClass().remove("botaoSelected");
                loadList("artigo");
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

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sistema sis = Utils.getSistema();
                myUser = null;
                sis.sair(event);
            }
        });
    }

    public void setUser(User myUser,UserPainel us) {
        this.myUser = myUser;
        this.us = us;
        if(myUser.isUserSubscriber()) {
            subscriber.setVisible(false);
        }
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
                    PostagemPreviewController postagemController = fxmlLoader.getController();
                    postagemController.setDataPostPreview(postagem.getTitulo(),postagem.getSubTitulo(),postagem.getDataPostagem().format(formatter),((Noticia) postagem).getProprietario().getName());
                    list.add(pane);
                } else if (postagem instanceof Artigo && tipo.equals("artigo")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("post-preview.fxml"));
                    Pane pane = fxmlLoader.load();
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
