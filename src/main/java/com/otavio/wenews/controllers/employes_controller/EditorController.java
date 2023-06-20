package com.otavio.wenews.controllers.employes_controller;

import com.otavio.wenews.Main;
import com.otavio.wenews.controllers.posts_controller.PostagemPreviewController;
import com.otavio.wenews.newsletter.FuncionarioPainel;
import com.otavio.wenews.newsletter.Sistema;
import com.otavio.wenews.newsletter.Utils;
import com.otavio.wenews.newsletter.employe.Editor;
import com.otavio.wenews.newsletter.employe.Funcionario;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * EditorController
 * Classe controladora responsável por exibir todas as postagens da newsletter para um editor poder selecionar qual editar.
 * Editor consegue editar e deletar qualquer postagem da newsletter.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @see com.otavio.wenews.Main
 * @see com.otavio.wenews.controllers.posts_controller.PostagemPreviewController
 * @see com.otavio.wenews.newsletter.FuncionarioPainel
 * @see com.otavio.wenews.newsletter.Sistema
 * @see com.otavio.wenews.newsletter.Utils
 * @see com.otavio.wenews.newsletter.employe.Editor
 * @see com.otavio.wenews.newsletter.employe.Funcionario
 * @see com.otavio.wenews.newsletter.posts.Artigo
 * @see com.otavio.wenews.newsletter.posts.Noticia
 * @see com.otavio.wenews.newsletter.posts.Postagem
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class EditorController implements Initializable {

    private FuncionarioPainel fc;
    private Funcionario myFun;
    private ArrayList<Postagem> posts;
    @FXML
    private BorderPane listViews;
    @FXML
    private Label nameUser;
    @FXML
    private Button sair;
    @FXML
    private Button voltar;

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

            voltar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    posts = fc.loadAllPosts();
                    loadList();
                }
            });


            sair.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Sistema sis = Utils.getSistema();
                    myFun = null;
                    fc = null;
                    sis.sair(event);
                }
            });
    }

    /**
     * Método que carrega os dados de um funcionário do tipo editor
     * @param fc  o painel de funcionário
     * @param myFun o editor
     */
    public void setData(FuncionarioPainel fc, Funcionario myFun) {
        this.fc = fc;
        this.myFun = myFun;
        nameUser.setText(myFun.getName());
        posts = fc.loadAllPosts();
        loadList();
    }

    /**
     * Método que constrói todos os previews das postagens que um editor pode alterar
     * Ao clicar na postagem o editor e redirecionado para o painel de edição contido em:
     * @see com.otavio.wenews.controllers.employes_controller.EditorEditarController;
     */
    public void loadList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        ObservableList<Pane> list = FXCollections.observableArrayList();
        ListView list2 = new ListView<String>();
        for (Postagem postagem : posts) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("post-preview.fxml"));
                Pane pane = fxmlLoader.load();
                PostagemPreviewController postagemController = fxmlLoader.getController();
                pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Parent root = null;
                        FXMLLoader loader = null;
                        try{
                            loader = new FXMLLoader(Main.class.getResource("editor-fun-view.fxml"));
                            root = loader.load();
                            EditorEditarController edtECont = loader.getController();
                            edtECont.setData(postagem,(Editor) myFun,fc);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        listViews.setCenter(root);
                    }
                });

                if(postagem instanceof Noticia) {
                    postagemController.setDataPostPreview(postagem.getTitulo(),postagem.getSubTitulo(),postagem.getDataPostagem().format(formatter),((Noticia) postagem).getProprietario().getName());
                } else if(postagem instanceof Artigo) {
                    postagemController.setDataPostPreview(postagem.getTitulo(),postagem.getSubTitulo(),postagem.getDataPostagem().format(formatter),((Artigo) postagem).getProprietario().getName());
                }
                list.add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        list2.setItems(list);
        list2.setPrefWidth(1005);
        list2.setPrefHeight(609);
        list2.getStyleClass().add("list-view-transparent");
        listViews.setCenter(list2);
    }
}
