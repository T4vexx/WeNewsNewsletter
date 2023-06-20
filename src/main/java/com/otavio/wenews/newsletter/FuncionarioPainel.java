package com.otavio.wenews.newsletter;

import com.otavio.wenews.controllers.employes_controller.EditorController;
import com.otavio.wenews.controllers.employes_controller.EscritorJornalistaController;
import com.otavio.wenews.exceptions.NonexistentPostError;
import com.otavio.wenews.exceptions.WriteNewPostError;
import com.otavio.wenews.newsletter.employe.Editor;
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
import java.util.ArrayList;

/**
 * FuncionarioPainel
 * A classe FuncionarioPainel representa o painel do funcionário, onde são realizadas
 * operações relacionadas aos posts da newsletter. Ela é parametrizada pelo tipo T, que
 * deve ser uma subclasse de Funcionario.
 *
 * @see com.otavio.wenews.controllers.employes_controller.EditorController;
 * @see com.otavio.wenews.controllers.employes_controller.EscritorJornalistaController;
 * @see com.otavio.wenews.exceptions.NonexistentPostError;
 * @see com.otavio.wenews.exceptions.WriteNewPostError;
 * @see com.otavio.wenews.newsletter.employe.Editor;
 * @see com.otavio.wenews.newsletter.employe.Escritor;
 * @see com.otavio.wenews.newsletter.employe.Funcionario;
 * @see com.otavio.wenews.newsletter.employe.Jornalista;
 * @see com.otavio.wenews.newsletter.posts.Artigo;
 * @see com.otavio.wenews.newsletter.posts.Noticia;
 * @see com.otavio.wenews.newsletter.posts.Postagem;
 * @param <T> o tipo do funcionário
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class FuncionarioPainel<T extends Funcionario> {
    private T myFun;

    /**
     * Construtor da classe FuncionarioPainel
     * @param MyFun  o funcionário
     * @param event  o evento de ação
     */
    public FuncionarioPainel(T MyFun, ActionEvent event) {
        this.myFun = MyFun;
        if(MyFun instanceof Jornalista || MyFun instanceof Escritor) {
            FXMLLoader loader = Utils.changeScene(event,"jornalistaescritor","WeNews | Write Post page",1220, 700);
            EscritorJornalistaController escJorCont = loader.getController();
            escJorCont.setMyFun(MyFun,this);
        } else if (myFun instanceof Editor) {
            FXMLLoader loader = Utils.changeScene(event,"editor","WeNews | Edite Article page",1220, 700);
            EditorController edit = loader.getController();
            edit.setData(this,myFun);
        }
    }

    /**
     * Método que realiza a publicação de um post no banco de dados
     * @param post o post a ser publicado
     * @return true se a publicação for bem-sucedida, false caso contrário
     * @throws WriteNewPostError exceção lançada quando ocorre um erro na publicação do post
     */
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
                ((Jornalista) myFun).addNoticiasEscritas((Noticia) post);
            } else if (post instanceof Artigo) {
                ps.setString(2, "artigo");
                ((Escritor) myFun).setArtigosEscritos((Artigo) post);
            }
            ps.setString(3, post.getTitulo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new WriteNewPostError("Erro no registro | Erro interno | Tente novamente mais tarde !");
        }
    }

    /**
     * Método que carrega todos os posts da newsletter e retorna como um array
     * @return uma lista de postagens da newsletter
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

    /**
     * Método que exclui um post da newsletter no banco de dados
     * @param post o post a ser excluído
     * @throws NonexistentPostError exceção lançada quando o post não existe
     */
    public void excludePost(Postagem post) throws NonexistentPostError {
        PreparedStatement ps;
        ResultSet rs;
        Sistema sis = Utils.getSistema();
        try {
            ps = DBFun.searchDataPrepare(sis.getCon(),"SELECT * FROM posts WHERE titulo = ?");
            ps.setString(1,post.getTitulo());
            rs = ps.executeQuery();
            if(!rs.next()) {
                throw new NonexistentPostError("Post nao encontrado | Post já deletado ou inexistente");
            }
            ps = DBFun.searchDataPrepare(sis.getCon(),"DELETE FROM posts WHERE titulo = ?");
            ps.setString(1,post.getTitulo());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método chamado por um editor para modificar uma postagem da newsletter
     * @param newPost o novo post
     * @param oldPost o post antigo
     * @throws NonexistentPostError exceção lançada quando o post antigo não existe
     */
    public void editePost(Postagem newPost, Postagem oldPost) throws NonexistentPostError {
        PreparedStatement ps;
        ResultSet rs;
        Sistema sis = Utils.getSistema();
        int numero;
        try {
            ps = DBFun.searchDataPrepare(sis.getCon(),"SELECT * FROM posts WHERE titulo = ?");
            ps.setString(1,oldPost.getTitulo());
            rs = ps.executeQuery();
            if(!rs.next()) {
                throw new NonexistentPostError("Post nao encontrado | Post já deletado ou inexistente");
            }
            numero = rs.getInt(1);
            ps = DBFun.searchDataPrepare(sis.getCon(),"DELETE FROM posts WHERE titulo = ?");
            ps.setString(1,oldPost.getTitulo());
            ps.execute();

            ps = DBFun.searchDataPrepare(sis.getCon(),"INSERT INTO posts (id,post,tipo,titulo) VALUES(?,?,?,?)");
            ps.setInt(1,numero);
            ps.setBytes(2,Utils.converterClienteParaByte(newPost));
            if(newPost instanceof Noticia) {
                ps.setString(3,"noticia");
            } else if(newPost instanceof Artigo) {
                ps.setString(3,"artigo");
            }
            ps.setString(4,newPost.getTitulo());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
