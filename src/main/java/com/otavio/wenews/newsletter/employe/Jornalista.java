package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Artigo;
import com.otavio.wenews.newsletter.posts.Noticia;

import java.io.Serializable;
import java.util.*;

public class Jornalista extends Funcionario implements Serializable {

    private String especialidade;
    private ArrayList<Noticia> noticiasEscritas = new ArrayList<>();

    public Jornalista(String name, String email, String password, int idade, String especialidade) {
        super(name, email, password, idade);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public ArrayList<Noticia> getNoticiasEscritas() {
        return noticiasEscritas;
    }

    public void addNoticiasEscritas(Noticia noticiasEscritas) {
        this.noticiasEscritas.add(noticiasEscritas);
    }
}
