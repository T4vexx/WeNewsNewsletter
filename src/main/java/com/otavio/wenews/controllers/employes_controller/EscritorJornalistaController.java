package com.otavio.wenews.controllers.employes_controller;

import com.otavio.wenews.App;
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

/**
 * EscritorJornalistaController
 * Classe controladora responsável por exibir o header de um criador de notícias/artigos para um jornalista e para um escritor.
 * Essa classe implementa a interface Initializable do JavaFX.
 *
 * @see App
 * @see com.otavio.wenews.newsletter.FuncionarioPainel
 * @see com.otavio.wenews.newsletter.Sistema
 * @see com.otavio.wenews.newsletter.Utils
 * @see com.otavio.wenews.newsletter.employe.Escritor
 * @see com.otavio.wenews.newsletter.employe.Funcionario
 * @see com.otavio.wenews.newsletter.employe.Jornalista
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class EscritorJornalistaController implements Initializable {

    private Funcionario myFun;
    private FuncionarioPainel funPainel;
    @FXML
    private BorderPane bp2;
    @FXML
    private Label nameUser;
    @FXML
    private Button sair;

    /**
     * Método chamado ao inicializar o controlador
     * @param location  o local do arquivo FXML
     * @param resources os recursos utilizados pela classe
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sistema sis = Utils.getSistema();
                myFun = null;
                funPainel = null;
                sis.sair(event);
            }
        });
    }

    /**
     * Método que inicializa o visual dessa página e seta o nome do funcionário
     * Carrega as páginas no BorderPane
     * @param fun um funcionário que pode ser Jornalista e Escritor
     * @param funPainel um painel de funcionário
     */
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

    /**
     * Método que carrega as duas possíveis páginas no BorderPane dessa pagina
     * A páginas carregadas aqui sao o escritor-fun-view.fxml e jornalista-fun-view.fxml
     * @param page nome do arquivo da página para ser carregada
     * @return retorna um FXMLLoader para poder pegar o controller da página seguinte
     */
    private FXMLLoader loadPage(String page) {
        Parent root = null;
        FXMLLoader loader = null;

        try{
            loader = new FXMLLoader(App.class.getResource(page+"-fun-view.fxml"));
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bp2.setCenter(root);
        return loader;
    }
}
