package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Noticia;

import java.io.Serializable;
import java.util.ArrayList;

public class Escritor extends Funcionario implements Serializable {
    private String estiloLiterario;
    private ArrayList<Noticia> artigosEscritos = new ArrayList<>();

    public Escritor(String name, String email, String password, int idade, String estiloLiterario) {
        super(name, email, password, idade);
        this.estiloLiterario = estiloLiterario;
    }

    public String getEstiloLiterario() {
        return estiloLiterario;
    }

    public ArrayList<Noticia> getArtigosEscritos() {
        return artigosEscritos;
    }

    public void setArtigosEscritos(Noticia artigosEscritos) {
        this.artigosEscritos.add(artigosEscritos);
    }
}
