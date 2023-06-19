package com.otavio.wenews.newsletter;

import com.otavio.wenews.controllers.users_controller.UserController;
import com.otavio.wenews.exceptions.LoginMissException;
import com.otavio.wenews.newsletter.person.Subscriber;
import com.otavio.wenews.newsletter.person.User;
import com.otavio.wenews.newsletter.posts.Artigo;
import com.otavio.wenews.newsletter.posts.Comentario;
import com.otavio.wenews.newsletter.posts.Noticia;
import com.otavio.wenews.newsletter.posts.Postagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * UserPainel
 * Esta classe são os métodos que cuidam de todas as funções de um usuário
 * Possui um atributo myUser que armazena as informações de uma usuário
 * Quando a classe é instaciada ela cria a página Home de usúario
 * @see com.otavio.wenews.controllers.users_controller.UserController;
 * @see com.otavio.wenews.exceptions.LoginMissException;
 * @see com.otavio.wenews.newsletter.person.Subscriber;
 * @see com.otavio.wenews.newsletter.person.User;
 * @see com.otavio.wenews.newsletter.posts.Artigo;
 * @see com.otavio.wenews.newsletter.posts.Comentario;
 * @see com.otavio.wenews.newsletter.posts.Noticia;
 * @see com.otavio.wenews.newsletter.posts.Postagem;
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class UserPainel {
    private User myUser;

    /**
     * Construtor que inicia o usuário e cria a cena da Home de usuário
     * Envia os dados para o controllador
     * @param myUser
     * @param event
     */
    public UserPainel(User myUser, ActionEvent event) {
        this.myUser = myUser;
        FXMLLoader loader = Utils.changeScene(event,"user","WeNews | Home page",1220,700);
        UserController userController = loader.getController();
        userController.setUser(myUser,this);
    }

    /**
     * Método que busca no banco de dados todos os posts e retorna esse posts
     * @return retorna um array list de posts
     */
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

    public void giveUserSubscription(User user) {
        PreparedStatement ps;
        Sistema sis = Utils.getSistema();
        try {
            Subscriber sub = new Subscriber();
            ps = DBFun.searchDataPrepare(sis.getCon(),"UPDATE usuarios SET isusersubscriber = ?, inscricao = ? WHERE email = ?");
            ps.setBoolean(1,true);
            ps.setBytes(2,Utils.converterClienteParaByte(sub));
            ps.setString(3,user.getEmail());
            ps.execute();
            user.setUserSubscriber(true);
            user.setInscricao(sub);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addNewComentToAPost(User myUser, Postagem post, String comentario, ActionEvent event) {
        PreparedStatement ps,ps2;
        ResultSet rs;
        Sistema sis = Utils.getSistema();
        Postagem pos;


        try {
            Comentario coment = new Comentario(myUser,comentario, LocalDateTime.now());
            ps = DBFun.searchDataPrepare(sis.getCon(),"SELECT * FROM posts WHERE titulo = ? ");
            ps.setString(1,post.getTitulo());
            rs = ps.executeQuery();
            rs.next();

            ps2 = DBFun.searchDataPrepare(sis.getCon(),"INSERT INTO posts (id,post,tipo,titulo) VALUES(?,?,?,?)");
            ps2.setInt(1,rs.getInt(1));
            if(rs.getString(3).equals("noticia")) {
                pos = (Noticia) Utils.converterByteParaCliente(rs.getBytes(2));
            } else {
                pos = (Artigo) Utils.converterByteParaCliente(rs.getBytes(2));
            }
            pos.addcComentarios(coment);
            ps2.setBytes(2,Utils.converterClienteParaByte(pos));
            ps2.setString(3,rs.getString(3));
            ps2.setString(4,rs.getString(4));

            ps = DBFun.searchDataPrepare(sis.getCon(),"DELETE FROM posts WHERE id = ? ");
            ps.setInt(1,rs.getInt(1));
            ps.execute();
            ps2.execute();
            FXMLLoader loader = Utils.changeScene(event,"user","WeNews | Home page",1220,700);
            UserController userController = loader.getController();
            userController.setUser(myUser,this);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changePassword(String oldPassword, String newPassword) throws LoginMissException {
        if(myUser.validarSenha(oldPassword)) {
            PreparedStatement ps;
            Sistema sis = Utils.getSistema();
            try {
                ps = DBFun.searchDataPrepare(sis.getCon(),"UPDATE usuarios SET password = ? WHERE email = ?");
                ps.setString(1,Utils.encryptPassword(newPassword));
                ps.setString(2,myUser.getEmail());
                ps.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            throw new LoginMissException("Senha antiga incorreta | Tente novamente!");
        }
    }

    public void renewSubscription(Subscriber sub,ActionEvent event) {
        PreparedStatement ps;
        Sistema sis = Utils.getSistema();
        sub.renew();

        try {
            ps = DBFun.searchDataPrepare(sis.getCon(),"UPDATE usuarios SET inscricao = ? WHERE email = ?");
            ps.setBytes(1,Utils.converterClienteParaByte(sub));
            ps.setString(2,myUser.getEmail());
            ps.execute();
            FXMLLoader loader = Utils.changeScene(event,"user","WeNews | Home page",1220,700);
            UserController userController = loader.getController();
            userController.setUser(myUser,this);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
