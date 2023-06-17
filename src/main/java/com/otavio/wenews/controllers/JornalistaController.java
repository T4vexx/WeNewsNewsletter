package com.otavio.wenews.controllers;

import com.otavio.wenews.exceptions.WriteNewPostError;
import com.otavio.wenews.newsletter.FuncionarioPainel;
import com.otavio.wenews.newsletter.employe.Jornalista;
import com.otavio.wenews.newsletter.posts.Noticia;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.*;

public class JornalistaController implements Initializable {

    private FuncionarioPainel funPainel;
    private Jornalista myJor;
    @FXML
    private TextField titulo;
    @FXML
    private TextField subtitulo;
    @FXML
    private TextField categoria;
    @FXML
    private TextField fonte;
    @FXML
    private TextArea paragrafo;
    @FXML
    private Button enviar;
    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Noticia noticia = new Noticia();

        enviar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkEmpyt(titulo) && checkEmpyt(subtitulo) && checkEmpyt(categoria) && checkEmpyt(fonte) && !paragrafo.getText().equals("")) {
                    noticia.setTitulo(titulo.getText());
                    noticia.setSubTitulo(subtitulo.getText());
                    noticia.setCategoria(categoria.getText());
                    noticia.setFonte(fonte.getText());
                    noticia.setDataPostagem(LocalDateTime.now());
                    noticia.setProprietario(myJor);
                    Iterator<CharSequence> it = paragrafo.getParagraphs().iterator();
                    while (it.hasNext()) {
                        noticia.addNewParagraf(it.next().toString());
                    }
                    try {
                        if(funPainel.post(noticia)) {
                            message.setTextFill(Paint.valueOf("#38A169"));
                            message.setText("Registro de uma nova postagem efetuado com sucesso");
                            setEmpyt(titulo);
                            setEmpyt(subtitulo);
                            setEmpyt(categoria);
                            setEmpyt(fonte);
                            paragrafo.setText("");
                            CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.setText("");
                                    }
                                });
                            });
                        } else {
                            message.setTextFill(Paint.valueOf("#E53E3E"));
                            message.setText("Erro ao cadastrar uma nova postagem | Tente novamente mais tarde !");
                        }
                    } catch (WriteNewPostError ex) {
                        message.setTextFill(Paint.valueOf("#E53E3E"));
                        message.setText(ex.getMessage());
                    }

                } else {
                    message.setTextFill(Paint.valueOf("#E53E3E"));
                    message.setText("Todas as informações devem ser prenchidas");
                }
            }
        });
    }

    private boolean checkEmpyt(TextField check) {
        return !check.getText().equals("");
    }

    private void setEmpyt(TextField check) {
        check.setText("");
    }

    public void setMyFun(Jornalista myJor, FuncionarioPainel funPainel) {
        this.myJor = myJor;
        this.funPainel = funPainel;
    }

}
