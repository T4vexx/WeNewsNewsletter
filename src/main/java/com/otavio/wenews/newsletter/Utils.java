package com.otavio.wenews.newsletter;

import com.otavio.wenews.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
            stage.setScene(new Scene(root, x,y));
            stage.show();
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
}
