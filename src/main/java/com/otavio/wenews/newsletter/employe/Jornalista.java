package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Artigo;

import java.util.*;

public class Jornalista extends Funcionario {

    private String especialidade;
    private ArrayList<Artigo> noticiasEscritas = new ArrayList<>();

    public Jornalista(String name, String email, String password, int idade, String especialidade) {
        super(name, email, password, idade);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public ArrayList<Artigo> getNoticiasEscritas() {
        return noticiasEscritas;
    }

    public void addNoticiasEscritas(Artigo noticiasEscritas) {
        this.noticiasEscritas.add(noticiasEscritas);
    }
}
