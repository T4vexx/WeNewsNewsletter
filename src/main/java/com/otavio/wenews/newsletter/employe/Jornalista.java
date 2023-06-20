package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Noticia;
import java.io.Serializable;
import java.util.*;

/**
 * Jornalista
 * Classe que representa um jornalista como um tipo de funcionário
 * Especializado em escrever apenas notícias
 *
 * @see com.otavio.wenews.newsletter.posts.Noticia
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Jornalista extends Funcionario implements Serializable {

    private String especialidade;
    private ArrayList<Noticia> noticiasEscritas = new ArrayList<>();

    /**
     * Construtor que inicializa um jornalista com o nome, email, senha, idade e especialidade especificados
     * @param name o nome do jornalista
     * @param email o email do jornalista
     * @param password a senha do jornalista
     * @param idade a idade do jornalista
     * @param especialidade a especialidade do jornalista
     */
    public Jornalista(String name, String email, String password, int idade, String especialidade) {
        super(name, email, password, idade);
        this.especialidade = especialidade;
    }

    /**
     * Método que obtém a especialidade do jornalista
     * @return a especialidade do jornalista
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * Método que obtém as notícias escritas pelo jornalista
     * @return as notícias escritas pelo jornalista
     */
    public ArrayList<Noticia> getNoticiasEscritas() {
        return noticiasEscritas;
    }

    /**
     * Método que adiciona uma notícia à lista de notícias escritas pelo jornalista
     * @param noticiasEscritas a notícia a ser adicionada
     */
    public void addNoticiasEscritas(Noticia noticiasEscritas) {
        this.noticiasEscritas.add(noticiasEscritas);
    }
}
