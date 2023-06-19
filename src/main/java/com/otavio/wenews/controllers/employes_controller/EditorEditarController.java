package com.otavio.wenews.controllers.employes_controller;

import com.otavio.wenews.exceptions.NonexistentPostError;
import com.otavio.wenews.newsletter.FuncionarioPainel;
import com.otavio.wenews.newsletter.Utils;
import com.otavio.wenews.newsletter.employe.Editor;
import com.otavio.wenews.newsletter.posts.Artigo;
import com.otavio.wenews.newsletter.posts.Noticia;
import com.otavio.wenews.newsletter.posts.Postagem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.ResourceBundle;

public class EditorEditarController implements Initializable {

    private Postagem post;
    private Editor myFun;
    private FuncionarioPainel fp;
    @FXML
    private TextField titulo;
    @FXML
    private TextField subtitulo;
    @FXML
    private TextField opcional1;
    @FXML
    private TextField opcional2;
    @FXML
    private TextArea paragrafo;
    @FXML
    private Button enviar;
    @FXML
    private Button cancelar;
    @FXML
    private Label opcional1Label;
    @FXML
    private Label opcional2Label;
    @FXML
    private Label paragrafoLabel;
    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        enviar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!checkEmpyt(titulo) && !checkEmpyt(subtitulo) && !checkEmpyt(opcional1) && !checkEmpyt(opcional2) && !paragrafo.getText().equals("")) {
                    if(post instanceof Noticia) {
                            Noticia not = new Noticia(post.getDataPostagem(),titulo.getText(),subtitulo.getText(),opcional1.getText(),opcional2.getText(),((Noticia) post).getProprietario());
                            not.setEditadoPor(myFun);
                            not.setUltimaEdicao(LocalDateTime.now());
                            Iterator<CharSequence> it = paragrafo.getParagraphs().iterator();
                            while (it.hasNext()) {
                                not.addNewParagraf(it.next().toString());
                            }
                            myFun.addPostagensEditadas(not);
                            try {
                                fp.editePost(not,(Noticia) post);
                            } catch (NonexistentPostError ex) {
                                message.setText(ex.getMessage());
                                message.setTextFill(Paint.valueOf("#E53E3E"));
                            }
                    } else {
                        Artigo art = new Artigo(post.getDataPostagem(),titulo.getText(),subtitulo.getText(),opcional1.getText(),((Artigo) post).getProprietario(),opcional2.getText());
                        art.setEditadoPor(myFun);
                        art.setUltimaEdicao(LocalDateTime.now());
                        Iterator<CharSequence> it = paragrafo.getParagraphs().iterator();
                        while (it.hasNext()) {
                            art.addNewParagraf(it.next().toString());
                        }
                        myFun.addPostagensEditadas(art);
                        try {
                            fp.editePost(art,(Artigo) post);
                        } catch (NonexistentPostError ex) {
                            message.setText(ex.getMessage());
                            message.setTextFill(Paint.valueOf("#E53E3E"));
                        }
                    }
                    FXMLLoader fx = Utils.changeScene(event,"editor","WeNews | Edite Article page",1220, 700);
                    EditorController edt = fx.getController();
                    edt.setData(fp,myFun);
                } else {
                    message.setText("Preencha todos os campos a baixo");
                    message.setTextFill(Paint.valueOf("#E53E3E"));
                }
            }
        });

        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(JOptionPane.showConfirmDialog(null,"Você tem certeza que deseja apagar essa postagem?")==0){
                    try {
                        fp.excludePost(post);
                        FXMLLoader fx = Utils.changeScene(event,"editor","WeNews | Edite Article page",1220, 700);
                        EditorController edt = fx.getController();
                        edt.setData(fp,myFun);
                    } catch (NonexistentPostError ex) {
                        message.setText(ex.getMessage());
                        message.setTextFill(Paint.valueOf("#E53E3E"));
                    }
                }
            }
        });

    }

    public void setData(Postagem post, Editor myFun, FuncionarioPainel fp) {
        int count=0;
        this.post = post;
        this.myFun = myFun;
        this.fp = fp;
        titulo.setText(post.getTitulo());
        subtitulo.setText(post.getSubTitulo());
        if(post instanceof Noticia) {
            opcional1Label.setText("Categoria");
            opcional2Label.setText("Fonte");
            paragrafoLabel.setText("Corpo da Notícia");
            opcional1.setText(((Noticia) post).getCategoria());
            opcional2.setText(((Noticia) post).getFonte());
        } else if (post instanceof Artigo) {
            opcional1Label.setText("Tema");
            opcional2Label.setText("Protagonistas");
            paragrafoLabel.setText("Corpo do Artigo");
            opcional1.setText(((Artigo) post).getTema());
            opcional2.setText(((Artigo) post).getProtagonistas());
        }
        for(String par : post.getParagrafos()) {
            if(count == 0) {
                paragrafo.appendText(par);
                count++;
            } else {
                paragrafo.appendText("\n");
                paragrafo.appendText(par);
            }
        }
    }

    public boolean checkEmpyt(TextField tx) {
        return tx.getText().equals("");
    }
}
