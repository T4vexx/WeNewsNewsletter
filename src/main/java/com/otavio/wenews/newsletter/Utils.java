package com.otavio.wenews.newsletter;

import com.otavio.wenews.Main;
import com.otavio.wenews.newsletter.employe.Editor;
import com.otavio.wenews.newsletter.employe.Escritor;
import com.otavio.wenews.newsletter.employe.Jornalista;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Utils
 * Essa classe guarda diversos métodos utils para ser usado no código
 * Possui um atributo estático Sistema syst que guarda um sistema para poder ser chamado nos controllers
 * Possui métodos que, converte o cliente para bytes, encryptar a senha e gerar super usuários
 * @see com.otavio.wenews.newsletter.employe.Editor
 * @see com.otavio.wenews.newsletter.employe.Escritor
 * @see com.otavio.wenews.newsletter.employe.Jornalista
 * @see com.otavio.wenews.Main
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Utils {
    private static Sistema syst = new Sistema();

    /**
     * Método que retorna um sistema
     * @return retorna um sitema
     */
    public static Sistema getSistema() {
        return syst;
    }

    /**
     * Método para mudar de uma cena para uma nova cena e retornar o loader para pegar o controlador
     * @param event evento dando quer mudar a cena
     * @param fxmlFile Nome do arquivo.fxml da nova cena
     * @param title titulo da nova cena
     * @param x tamanho de largura da página
     * @param y tamanho de altura da página
     * @return return um FXMLLoader para poder usar um controller mais para frente
     */
    public static FXMLLoader changeScene(ActionEvent event, String fxmlFile, String title,int x, int y) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile+"-view.fxml"));
            root = loader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root, x,y));
            stage.show();
            stage.centerOnScreen();
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que recebe um objeto do tipo T e transforma ela para um array de bytes para serem usados no banco de dados
     * @param cliente Objeto para ser serizalizado em um array de bytes
     * @return retorna um array de bytes
     * @param <T> Tipo do objeto para ser Serializado
     */
    public static <T> byte[] converterClienteParaByte(T cliente) {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream ous;
            ous = new ObjectOutputStream(bao);
            ous.writeObject(cliente);
            return bao.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Método que recebe um Array de bytes transforma ela para um objeto do tipo T
     * @param clienteAsByte Um array de bytes que é um objeto serializado
     * @return retorna um objeto deserializado
     * @param <T> Tipo do objeto para ser deserializado
     */
    public static <T> T converterByteParaCliente(byte[] clienteAsByte) {
        try {
            ByteArrayInputStream bao = new ByteArrayInputStream(clienteAsByte);
            ObjectInputStream ous;
            ous = new ObjectInputStream(bao);
            return (T) ous.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Método que recebe uma string e encrypta essa string em MD5
     * @param password uma string para ser encryptada
     * @return retorna um string encryptada
     */
    public static String encryptPassword(String password) {
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            return s.toString();
        }  catch (NoSuchAlgorithmException e)  {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que gera os 3 superusuários, um jornalista que pode escrever uma notícia, um Escritor que pode escrever um artigo e um editor que pode editar um artigo e uma notícia
     */
    public static void genereteSuperUsuarios() {
        PreparedStatement ps1,ps2,ps3,ps4;
        ResultSet rs;
        Sistema sis = Utils.getSistema();
        Connection conn = sis.getCon();
        try {
            ps4 = DBFun.searchDataPrepare(conn,"SELECT FROM funcionarios");
            rs = ps4.executeQuery();
            if(!rs.next()) {
                Jornalista jor = new Jornalista("Otavio Jornalista","jornalista@gmail.com",Utils.encryptPassword("123"),22,"Guerras");
                Escritor esc = new Escritor("Otavio Escritor","escritor@gmail.com",Utils.encryptPassword("123"),22,"Ficção");
                Editor edt = new Editor("Otavio Editor","editor@gmail.com",Utils.encryptPassword("123"),22,"DCCE");

                ps1 = DBFun.searchDataPrepare(conn,String.format("INSERT INTO funcionarios (name,email,password,idade,funcionario,cargo) VALUES('Otavio Jornalista','jornalista@gmail.com',?,20,?,'jornalista')"));
                ps2 = DBFun.searchDataPrepare(conn,String.format("INSERT INTO funcionarios (name,email,password,idade,funcionario,cargo) VALUES('Otavio Escritor','escritor@gmail.com',?,20,?,'escritor')"));
                ps3 = DBFun.searchDataPrepare(conn,String.format("INSERT INTO funcionarios (name,email,password,idade,funcionario,cargo) VALUES('Otavio Editor','editor@gmail.com',?,20,?,'editor')"));

                ps1.setString(1,Utils.encryptPassword("123"));
                ps1.setBytes(2,Utils.converterClienteParaByte(jor));
                ps2.setString(1,Utils.encryptPassword("123"));
                ps2.setBytes(2,Utils.converterClienteParaByte(esc));
                ps3.setString(1,Utils.encryptPassword("123"));
                ps3.setBytes(2,Utils.converterClienteParaByte(edt));

                ps1.execute();
                ps2.execute();
                ps3.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
