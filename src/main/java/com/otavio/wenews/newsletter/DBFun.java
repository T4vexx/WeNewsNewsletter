package com.otavio.wenews.newsletter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * DBFun
 * Classe que realiza métodos relacionados ao banco de dados, conexao, inserção e preparação de querys
 */
public class DBFun {

    /**
     * Conexão ao banco de dados especificado usando as credenciais fornecidas
     * @param dbname   o nome do banco de dados
     * @param user     o nome de usuário para a conexão com o banco de dados
     * @param password a senha para a conexão com o banco de dados
     * @return o objeto Connection que representa a conexão com o banco de dados, ou null se ocorrer um erro
     */
    public static Connection connectToDb(String dbname, String user, String password) {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,password);
            if(con != null) {
                System.out.println("Conexao realizada com sucesso");
            } else {
                System.out.println("Erro ao tentar conexao");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    /**
     * Método que insere uma linha no banco de dados usando a conexão e a consulta fornecidas
     * @param con o objeto Connection que representa a conexão com o banco de dados
     * @param query a consulta SQL para inserir a linha
     */
    public static void insert_row(Connection con, String query) {
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que prepara uma consulta SQL para execução usando a conexão e a consulta fornecidas
     * @param con o objeto Connection que representa a conexão com o banco de dados
     * @param query a consulta SQL a ser preparada
     * @return o objeto PreparedStatement que representa a consulta preparada, ou null se ocorrer um erro
     */
    public static PreparedStatement searchDataPrepare(Connection con, String query) {
        try {
            return con.prepareStatement(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
