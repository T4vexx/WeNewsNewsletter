package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Postagem;

import java.io.Serializable;
import java.util.ArrayList;

public class Editor extends Funcionario implements Serializable {
    private String departamento;
    private ArrayList<Postagem> postagensEditadas = new ArrayList<>();

    public Editor(String name, String email, String password, int idade, String departamento) {
        super(name, email, password, idade);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public ArrayList<Postagem> getPostagensEditadas() {
        return postagensEditadas;
    }

    public void addPostagensEditadas(Postagem postagensEditadas) {
        this.postagensEditadas.add(postagensEditadas);
    }
}
