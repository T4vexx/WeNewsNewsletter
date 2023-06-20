package com.otavio.wenews.exceptions;

/**
 * WriteNewPostError
 * Classe que cria um erro ao criar uma postagem
 *
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class WriteNewPostError extends Exception{

    /**
     * Construtor que instancia um novo erro de escrita de postagem
     * @param message mensagem de erro
     */
    public WriteNewPostError(String message) {
        super(message);
    }
}
