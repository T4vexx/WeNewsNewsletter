package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.Utils;

import java.io.Serializable;

public abstract class Funcionario implements Serializable {
    private String name;
    private String email;
    private String password;
    private int idade;

    public Funcionario(String name, String email, String password, int idade) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public boolean validarSenha(String senha) {
        return getPassword().equals(Utils.encryptPassword(senha));
    }
}
