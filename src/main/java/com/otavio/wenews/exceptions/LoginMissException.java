package com.otavio.wenews.exceptions;

/**
 * LoginMissException
 * Classe que cria um erro ao entrar ou registrar um usuário
 *
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class LoginMissException extends Exception{

    /**
     * Construtor que instancia um novo erro de login
     * @param message mensagem de erro
     */
    public LoginMissException(String message) {
        super(message);
    }
}
