package com.otavio.wenews.newsletter;

import com.otavio.wenews.exceptions.LoginMissException;
import com.otavio.wenews.newsletter.employe.Editor;
import com.otavio.wenews.newsletter.employe.Escritor;
import com.otavio.wenews.newsletter.employe.Jornalista;
import com.otavio.wenews.newsletter.person.Subscriber;
import com.otavio.wenews.newsletter.person.User;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sistema
 * Representa o sistema de gerenciamento de login da newsletter
 * Possui os métodos de login, registro e de saída da newsletter
 * Nessa classe é important colocar o nome da database, o usuario e a senha da mesma
 *
 * @see com.otavio.wenews.exceptions.LoginMissException;
 * @see com.otavio.wenews.newsletter.employe.Editor;
 * @see com.otavio.wenews.newsletter.employe.Escritor;
 * @see com.otavio.wenews.newsletter.employe.Jornalista;
 * @see com.otavio.wenews.newsletter.person.Subscriber;
 * @see com.otavio.wenews.newsletter.person.User;
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Sistema {
    private Connection con;

    /**
     * Construtor da classe Sistema
     * Inicializa a conexão com o banco de dados
     */
    public Sistema() {
        con = DBFun.connectToDb("wenews","postgres","1163");
    }

    /**
     * Método que realiza o processo de login do usuário ou funcionário
     * Verifica se a conta que foi logada e de um usuário ou funcionario e direciona
     * esse login para seu respectivo painel
     * @param emailLogin o email utilizado para o login
     * @param senha a senha utilizada para o login
     * @param event o evento de ação associado ao login
     * @throws LoginMissException exceção lançada quando o login falha
     */
    public void login(String emailLogin, String senha, ActionEvent event) throws LoginMissException {
        String nome,email,cpf,password;
        boolean isUserSubscriber;
        Subscriber sub;
        ResultSet rs,rs2;
        User meuUser;
        try {
            PreparedStatement ps = DBFun.searchDataPrepare(con,"SELECT * FROM usuarios WHERE email = ?");
            ps.setString(1,emailLogin);
            rs = ps.executeQuery();
            if(rs.next()) {
                nome = rs.getString(2);
                email = rs.getString(3);
                cpf = rs.getString(4);
                password = rs.getString(5);
                isUserSubscriber = rs.getBoolean(6);
                if(isUserSubscriber) {
                    sub = Utils.converterByteParaCliente(rs.getBytes(7));
                    meuUser = new User(nome,email,cpf,password,sub);
                } else {
                    meuUser = new User(nome,email,cpf,password);
                }
                if(!meuUser.validarSenha(senha)) {
                    throw new LoginMissException("Erro no login | Email ou senha não coincidentes");
                }
                new UserPainel(meuUser,event);
            } else {
                PreparedStatement ps2 = DBFun.searchDataPrepare(con,"SELECT * FROM funcionarios WHERE email = ?");
                ps2.setString(1,emailLogin);
                rs2 = ps2.executeQuery();
                if(rs2.next()) {
                    if(rs2.getString(7).equalsIgnoreCase("jornalista")) {
                        Jornalista jor = Utils.converterByteParaCliente(rs2.getBytes(6));
                        new FuncionarioPainel<Jornalista>(jor,event);
                    } else if(rs2.getString(7).equalsIgnoreCase("escritor")) {
                        Escritor esc = Utils.converterByteParaCliente(rs2.getBytes(6));
                        new FuncionarioPainel<Escritor>(esc,event);
                    } else if(rs2.getString(7).equalsIgnoreCase("editor")) {
                        Editor edt = Utils.converterByteParaCliente(rs2.getBytes(6));
                        new FuncionarioPainel<Editor>(edt,event);
                    }
                }
                throw new LoginMissException("Erro no login | Usuário não encontrado");
            }
        } catch (SQLException ex) {
            throw new LoginMissException("Erro no login | Erro interno");
        }

    }

    /**
     * Método que cuida do processo de registro de um novo usuário.
     * Possui validações de senha, email e CPF
     * @param nome o nome do novo usuário
     * @param email o email do novo usuário
     * @param cpf o CPF do novo usuário
     * @param senha a senha do novo usuário
     * @throws LoginMissException exceção lançada quando o registro falha
     */
    public void register(String nome, String email, String cpf, String senha) throws LoginMissException {
        PreparedStatement ps,ps2;
        ResultSet rs;
        boolean rs2;
        try {
            ps = DBFun.searchDataPrepare(con,"SELECT * FROM usuarios WHERE email = ?");
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()) {
                throw new LoginMissException("Erro no registro | Usuário já existe");
            }
            ps2 = DBFun.searchDataPrepare(con,"INSERT INTO usuarios (name,email,cpf,password,isusersubscriber,inscricao) VALUES(?,?,?,?,?,?)");
            ps2.setString(1,nome);
            ps2.setString(2,email);
            ps2.setString(3,cpf);
            ps2.setString(4,Utils.encryptPassword(senha));
            ps2.setBoolean(5,false);
            ps2.setBytes(6,null);
            ps2.execute();
        } catch (SQLException ex) {
            throw new LoginMissException("Erro no registro | Erro interno");
        }
    };

    /**
     * Método que realiza o logout do usuário atual e volta para o login
     * @param e o evento de ação associado ao logout
     */
    public void sair(ActionEvent e) {
        Utils.changeScene(e,"login","WeNews | Log-in page",400,600);
    }

    /**
     * Método que retorna a conexão com o banco de dados
     * @return a conexão com o banco de dados
     */
    public Connection getCon() {
        return con;
    }
}
