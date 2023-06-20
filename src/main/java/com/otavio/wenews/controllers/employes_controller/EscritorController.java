package com.otavio.wenews.controllers.employes_controller;

import com.otavio.wenews.exceptions.WriteNewPostError;
import com.otavio.wenews.newsletter.FuncionarioPainel;
import com.otavio.wenews.newsletter.employe.Escritor;
import com.otavio.wenews.newsletter.posts.Artigo;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * EscritorController
 * Classe controladora responsável por exibir um criador de artigos para um escritor.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @see com.otavio.wenews.exceptions.WriteNewPostError
 * @see com.otavio.wenews.newsletter.FuncionarioPainel
 * @see com.otavio.wenews.newsletter.employe.Escritor
 * @see com.otavio.wenews.newsletter.posts.Artigo
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class EscritorController implements Initializable {
    private Escritor myEsc;
    private FuncionarioPainel funPainel;
    @FXML
    private TextField titulo;
    @FXML
    private TextField subtitulo;
    @FXML
    private TextField tema;
    @FXML
    private TextField protagonistas;
    @FXML
    private TextArea paragrafo;
    @FXML
    private Button enviar;
    @FXML
    private Label message;

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Artigo noticia = new Artigo();

        enviar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkEmpyt(titulo) && checkEmpyt(subtitulo) && checkEmpyt(tema) && checkEmpyt(protagonistas) && !paragrafo.getText().equals("")) {
                    noticia.setTitulo(titulo.getText());
                    noticia.setSubTitulo(subtitulo.getText());
                    noticia.setTema(tema.getText());
                    noticia.setProtagonistas(protagonistas.getText());
                    noticia.setDataPostagem(LocalDateTime.now());
                    noticia.setProprietario(myEsc);
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
                            setEmpyt(tema);
                            setEmpyt(protagonistas);
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

    /**
     * Método que checa se um campo de texto está vazios
     * @param check o TextField que sera verificado
     * @return se tiver vazio retorna false se tiver com texto retorna true
     */
    private boolean checkEmpyt(TextField check) {
        return !check.getText().equals("");
    }

    /**
     * Método que seta um campo de texto como vazio
     * @param check o TextField que sera setado como vazio
     */
    private void setEmpyt(TextField check) {
        check.setText("");
    }

    /**
     * Método que carrega um funcionário e um painel de funcionário
     * @param myEsc um funcionário do tipo Escritor
     * @param funPainel um painel de funcionário
     */
    public void setMyFun(Escritor myEsc, FuncionarioPainel funPainel) {
        this.myEsc = myEsc;
        this.funPainel = funPainel;
    }
}
