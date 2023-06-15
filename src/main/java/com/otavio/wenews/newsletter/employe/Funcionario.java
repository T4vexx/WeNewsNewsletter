package com.otavio.wenews.newsletter.employe;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Funcionario {
    private String name;
    private String email;
    private String password;
    private int idade;

    public Funcionario(String name, String email, String password, int idade) {
        this.name = name;
        this.email = email;
        this.password = encryptPassword(password);
        this.idade = idade;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public String getPassword() {
        return password;
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
