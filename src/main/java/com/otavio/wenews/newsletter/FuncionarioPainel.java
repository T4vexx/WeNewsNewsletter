package com.otavio.wenews.newsletter;

import com.otavio.wenews.controllers.EscritorJornalistaController;
import com.otavio.wenews.exceptions.LoginMissException;
import com.otavio.wenews.exceptions.WriteNewPostError;
import com.otavio.wenews.newsletter.employe.Escritor;
import com.otavio.wenews.newsletter.employe.Funcionario;
import com.otavio.wenews.newsletter.employe.Jornalista;
import com.otavio.wenews.newsletter.posts.Artigo;
import com.otavio.wenews.newsletter.posts.Noticia;
import com.otavio.wenews.newsletter.posts.Postagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioPainel<T extends Funcionario> {
    private T MyFun;

    public FuncionarioPainel(T MyFun, ActionEvent event) {
        this.MyFun = MyFun;
        if(MyFun instanceof Jornalista || MyFun instanceof Escritor) {
            FXMLLoader loader = Utils.changeScene(event,"jornalistaescritor","WeNews | Write Post page",1220, 700);
            EscritorJornalistaController escJorCont = loader.getController();
            escJorCont.setMyFun(MyFun,this);
        } else {
            Utils.changeScene(event,"editor","WeNews | Edite Article page",1220, 700);
        }
    }

    public boolean post(Postagem post) throws WriteNewPostError {
        PreparedStatement ps;
        ResultSet rs;
        Sistema sis = Utils.getSistema();

        try {
            ps = DBFun.searchDataPrepare(sis.getCon(),"SELECT * FROM posts WHERE titulo = ?");
            ps.setString(1,post.getTitulo());
            rs = ps.executeQuery();
            if(rs.next()) {
                throw new WriteNewPostError("Erro no registro | Post já existente | Tente um novo título");
            }

            ps = DBFun.searchDataPrepare(sis.getCon(), "INSERT INTO posts (post,tipo,titulo) VALUES(?,?,?)");
            ps.setBytes(1, Utils.converterClienteParaByte(post));
            if(post instanceof Noticia) {
                ps.setString(2, "noticia");
            } else if (post instanceof Artigo) {
                ps.setString(2, "artigo");
            }
            ps.setString(3, post.getTitulo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new WriteNewPostError("Erro no registro | Erro interno | Tente novamente mais tarde !");
        }
    }
}
