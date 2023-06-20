package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Postagem;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Editor
 * Classe que representa um editor como um tipo de funcionário.
 * Especializado em editar notícias e artigos, podendo até mesmo deletá-las
 *
 * @see com.otavio.wenews.newsletter.posts.Postagem
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Editor extends Funcionario implements Serializable {
    private String departamento;
    private ArrayList<Postagem> postagensEditadas = new ArrayList<>();

    /**
     * Construtor que inicializa um editor com o nome, email, senha, idade e departamento especificados
     * @param name o nome do editor
     * @param email o email do editor
     * @param password a senha do editor
     * @param idade a idade do editor
     * @param departamento o departamento do editor
     */
    public Editor(String name, String email, String password, int idade, String departamento) {
        super(name, email, password, idade);
        this.departamento = departamento;
    }

    /**
     * Método que obtém o departamento do editor
     * @return o departamento do editor
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Método que obtém a lista de postagens editadas pelo editor
     * @return a lista de postagens editadas pelo editor
     */
    public ArrayList<Postagem> getPostagensEditadas() {
        return postagensEditadas;
    }

    /**
     * Método que adiciona uma postagem à lista de postagens editadas pelo editor
     * @param postagensEditadas a postagem a ser adicionada
     */
    public void addPostagensEditadas(Postagem postagensEditadas) {
        this.postagensEditadas.add(postagensEditadas);
    }
}
