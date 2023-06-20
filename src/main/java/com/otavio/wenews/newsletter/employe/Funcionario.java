package com.otavio.wenews.newsletter.employe;

import com.otavio.wenews.newsletter.Utils;
import java.io.Serializable;

/**
 * Funcionario
 * Classe abstrata que representa um funcionário.
 * Possui atributos comuns a Jornalista, Escritor e Editor
 *
 * @see com.otavio.wenews.newsletter.Utils
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public abstract class Funcionario implements Serializable {
    private String name;
    private String email;
    private String password;
    private int idade;

    /**
     * Construtor que inicializa um funcionário com o nome, email, senha e idade especificados
     * @param name o nome do funcionário
     * @param email o email do funcionário
     * @param password a senha do funcionário
     * @param idade a idade do funcionário
     */
    public Funcionario(String name, String email, String password, int idade) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.idade = idade;
    }

    /**
     * Método que obtém o nome do funcionário
     * @return o nome do funcionário
     */
    public String getName() {
        return name;
    }

    /**
     * Método que obtém o email do funcionário
     * @return o email do funcionário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que obtém a idade do funcionário
     * @return a idade do funcionário
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Método que obtém a idade do funcionário
     * @return a senha do funcionário
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método que valida a senha fornecida comparando-a com a senha armazenada no funcionário
     * @param senha a senha a ser validada
     * @return true se a senha fornecida for válida, false caso contrário
     */
    public boolean validarSenha(String senha) {
        return getPassword().equals(Utils.encryptPassword(senha));
    }
}
