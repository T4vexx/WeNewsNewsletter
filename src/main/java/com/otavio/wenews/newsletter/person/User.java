package com.otavio.wenews.newsletter.person;

import com.otavio.wenews.newsletter.Utils;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String cpf;
    private String password;
    private boolean isUserSubscriber;
    private Subscriber inscricao;

    public User(String name, String email, String cpf, String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        isUserSubscriber = false;
    }

    public User(String name, String email, String cpf, String password,Subscriber inscricao) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        isUserSubscriber = true;
        this.inscricao = inscricao;
    }

    public boolean isUserSubscriber() {
        return isUserSubscriber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public Subscriber getInscricao() {
        return inscricao;
    }

    public void setUserSubscriber(boolean userSubscriber) {
        isUserSubscriber = userSubscriber;
    }

    public void setInscricao(Subscriber inscricao) {
        this.inscricao = inscricao;
    }

    public boolean validarSenha(String senha) {
        String senhaEncryptada = Utils.encryptPassword(senha);
        String test = this.getPassword();
        return senhaEncryptada.equals(this.getPassword());
    }
}
