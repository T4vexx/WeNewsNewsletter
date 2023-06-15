package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Postagem;

import java.util.ArrayList;

public class Editor<T extends Postagem> extends Funcionario {
    private String departamento;
    private ArrayList<T> postagensEditadas = new ArrayList<>();

    public Editor(String name, String email, String password, int idade, String departamento) {
        super(name, email, password, idade);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public ArrayList<T> getPostagensEditadas() {
        return postagensEditadas;
    }

    public void addPostagensEditadas(T postagensEditadas) {
        this.postagensEditadas.add(postagensEditadas);
    }
}
