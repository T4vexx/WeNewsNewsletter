package com.otavio.wenews.newsletter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBFun {

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

    public static void insert_row(Connection con, String query) {
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static PreparedStatement searchDataPrepare(Connection con, String query) {
        try {
            return con.prepareStatement(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
