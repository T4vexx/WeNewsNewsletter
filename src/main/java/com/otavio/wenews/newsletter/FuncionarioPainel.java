package com.otavio.wenews.newsletter;

import com.otavio.wenews.newsletter.employe.Escritor;
import com.otavio.wenews.newsletter.employe.Funcionario;
import com.otavio.wenews.newsletter.employe.Jornalista;
import javafx.event.ActionEvent;

public class FuncionarioPainel<T extends Funcionario> {
    private T MyFun;

    public FuncionarioPainel(T MyFun, ActionEvent event) {
        this.MyFun = MyFun;
        if(MyFun instanceof Jornalista || MyFun instanceof Escritor) {
            Utils.changeScene(event,"jornalistaescritor","WeNews | Write Article page",600, 400);
        } else {
            Utils.changeScene(event,"editor","WeNews | Edite Article page",600, 400);
        }

    }
}
