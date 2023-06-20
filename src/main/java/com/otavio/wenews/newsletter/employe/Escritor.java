package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.posts.Artigo;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Escritor
 * Classe que representa um escritor como um tipo de funcionário.
 * Especializado em escrever apenas artigos
 *
 * @see com.otavio.wenews.newsletter.posts.Artigo
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Escritor extends Funcionario implements Serializable {
    private String estiloLiterario;
    private ArrayList<Artigo> artigosEscritos = new ArrayList<>();

    /**
     * Construtor que inicializa um escritor com o nome, email, senha, idade e estilo literário especificados
     * @param name o nome do escritor
     * @param email o email do escritor
     * @param password a senha do escritor
     * @param idade a idade do escritor
     * @param estiloLiterario o estilo literário do escritor
     */
    public Escritor(String name, String email, String password, int idade, String estiloLiterario) {
        super(name, email, password, idade);
        this.estiloLiterario = estiloLiterario;
    }

    /**
     * Método que obtém o estilo literário do escritor
     * @return o estilo literário do escritor
     */
    public String getEstiloLiterario() {
        return estiloLiterario;
    }

    /**
     * Método que obtém a lista de artigos escritos pelo escritor
     * @return a lista de artigos escritos pelo escritor
     */
    public ArrayList<Artigo> getArtigosEscritos() {
        return artigosEscritos;
    }

    /**
     * Método que adiciona um artigo à lista de artigos escritos pelo escritor
     * @param artigosEscritos o artigo a ser adicionado
     */
    public void setArtigosEscritos(Artigo artigosEscritos) {
        this.artigosEscritos.add(artigosEscritos);
    }
}
