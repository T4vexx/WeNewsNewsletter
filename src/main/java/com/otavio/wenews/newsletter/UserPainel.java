package com.otavio.wenews.newsletter;

import com.otavio.wenews.controllers.UserController;
import com.otavio.wenews.newsletter.person.User;
import com.otavio.wenews.newsletter.posts.Artigo;
import com.otavio.wenews.newsletter.posts.Noticia;
import com.otavio.wenews.newsletter.posts.Postagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserPainel {
    private User myUser;

    public UserPainel(User myUser, ActionEvent event) {
        this.myUser = myUser;
        FXMLLoader loader = Utils.changeScene(event,"user","WeNews | Home page",1220,700);
        UserController userController = loader.getController();
        userController.setUser(myUser,this);
    }

    public ArrayList<Postagem> loadAllPosts() {
        PreparedStatement ps;
        ResultSet rs;
        Sistema sis = Utils.getSistema();
        ArrayList<Postagem> posts = new ArrayList<>();
        try {
            ps = DBFun.searchDataPrepare(sis.getCon(),"SELECT * FROM posts");
            rs = ps.executeQuery();
            while(rs.next()) {

                if(rs.getString(3).equals("noticia")) {
                    Noticia not = Utils.converterByteParaCliente(rs.getBytes(2));
                    posts.add(not);
                } else if(rs.getString(3).equals("artigo")) {
                    Artigo art = Utils.converterByteParaCliente(rs.getBytes(2));
                    posts.add(art);
                }
            }
            return posts;
        } catch (SQLException ex) {
            return null;
        }
    }
}
