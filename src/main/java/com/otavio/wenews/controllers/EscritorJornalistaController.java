package com.otavio.wenews.controllers;

import com.otavio.wenews.Main;
import com.otavio.wenews.newsletter.FuncionarioPainel;
import com.otavio.wenews.newsletter.Sistema;
import com.otavio.wenews.newsletter.Utils;
import com.otavio.wenews.newsletter.employe.Escritor;
import com.otavio.wenews.newsletter.employe.Funcionario;
import com.otavio.wenews.newsletter.employe.Jornalista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EscritorJornalistaController implements Initializable {

    private Funcionario myFun;
    private FuncionarioPainel funPainel;
    @FXML
    private BorderPane bp;
    @FXML
    private Label nameUser;
    @FXML
    private Button sair;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sistema sis = Utils.getSistema();
                myFun = null;
                sis.sair(event);
            }
        });
    }

    public void setMyFun(Funcionario fun, FuncionarioPainel funPainel) {
        this.myFun = fun;
        this.funPainel = funPainel;
        nameUser.setText(fun.getName());
        if(myFun instanceof Jornalista) {
            FXMLLoader loader = loadPage("jornalista");
            JornalistaController jor = loader.getController();
            jor.setMyFun((Jornalista) myFun,funPainel);
        } else if(myFun instanceof Escritor) {
            FXMLLoader loader = loadPage("escritor");
            EscritorController esc = loader.getController();
            esc.setMyFun((Escritor) myFun,funPainel);
        }
    }

    private FXMLLoader loadPage(String page) {
        Parent root = null;
        FXMLLoader loader = null;

        try{
            loader = new FXMLLoader(Main.class.getResource(page+"-fun-view.fxml"));
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bp.setCenter(root);
        return loader;
    }
}
