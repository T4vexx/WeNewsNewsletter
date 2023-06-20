package com.otavio.wenews.newsletter.person;

import com.otavio.wenews.newsletter.Utils;
import java.io.Serializable;

/**
 * User
 * Esta classe representa um usuário do sistema de newsletter.
 * Um usuário pode ser um assinante ou não, e tem um nome, um email, um cpf e uma senha.
 * Um usuário assinante também tem uma inscrição associada a ele, que contém as informações sobre o plano e o pagamento.
 *
 * @see com.otavio.wenews.newsletter.Utils
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class User implements Serializable {

    private String name;
    private String email;
    private String cpf;
    private String password;
    private boolean isUserSubscriber;
    private Subscriber inscricao;

    /**
     * Construtor para um usuário não assinante.
     * @param name o nome do usuário
     * @param email o email do usuário
     * @param cpf o cpf do usuário
     * @param password a senha do usuário
     */
    public User(String name, String email, String cpf, String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        isUserSubscriber = false;
    }

    /**
     * Construtor para um usuário assinante.
     * @param name o nome do usuário
     * @param email o email do usuário
     * @param cpf o cpf do usuário
     * @param password a senha do usuário
     * @param inscricao a inscrição do usuário
     */
    public User(String name, String email, String cpf, String password,Subscriber inscricao) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        isUserSubscriber = true;
        this.inscricao = inscricao;
    }

    /**
     * Retorna se o usuário é um assinante ou não.
     * @return true se o usuário é um assinante, false caso contrário
     */
    public boolean isUserSubscriber() {
        return isUserSubscriber;
    }

    /**
     * Retorna o nome do usuário.
     * @return o nome do usuário
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o email do usuário.
     * @return o email do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna o cpf do usuário.
     * @return o cpf do usuário
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna a senha do usuário.
     * @return a senha do usuário
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retorna a inscrição do usuário.
     * @return a inscrição do usuário
     */
    public Subscriber getInscricao() {
        return inscricao;
    }

    /**
     * Define se o usuário é um assinante ou não.
     * @param userSubscriber true se o usuário é um assinante, false caso contrário
     */
    public void setUserSubscriber(boolean userSubscriber) {
        isUserSubscriber = userSubscriber;
    }

    /**
     * Define a inscrição do usuário.
     * @param inscricao a inscrição do usuário
     */
    public void setInscricao(Subscriber inscricao) {
        this.inscricao = inscricao;
    }

    /**
     * Valida se uma senha é igual à senha do usuário.
     * A senha é criptografada antes da comparação usando a classe Utils.
     * @param senha a senha a ser validada
     * @return true se a senha é igual à senha do usuário, false caso contrário
     */
    public boolean validarSenha(String senha) {
        String senhaEncryptada = Utils.encryptPassword(senha);
        return senhaEncryptada.equals(this.getPassword());
    }
}
