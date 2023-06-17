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
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Utils {
    private static Sistema syst = new Sistema();

    public static Sistema getSistema() {
        return syst;
    }

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

    public static void genereteSuperUsuarios() {
        PreparedStatement ps1,ps2,ps3;
        try {
            Connection conn = DBFun.connectToDb("wenews","postgres","1163");
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
            ps3.setBytes(2,Utils.converterClienteParaByte(esc));

            ps1.execute();
            ps2.execute();
            ps3.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
