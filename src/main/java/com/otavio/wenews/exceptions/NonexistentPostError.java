package com.otavio.wenews.exceptions;

/**
 * NonexistentPostError
 * Classe que cria um erro ao tentar acessar ou editar uma postagem inexistente
 *
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class NonexistentPostError extends Exception {

    /**
     * Construtor que instancia um novo erro de postagem inexistente
     * @param message mensagem de erro
     */
    public NonexistentPostError(String message) {
        super(message);
    }
}
