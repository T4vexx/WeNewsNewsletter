package com.otavio.wenews.interfaces;

import com.otavio.wenews.newsletter.employe.Funcionario;

/**
 * Postaveis
 * Interface de marcação que garante que os postáveis sempre terão os métodos de pegar o proprietário
 *
 * @see com.otavio.wenews.newsletter.employe.Funcionario
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public interface Postaveis {

    /**
     * Método que retorna um dono da postagem
     * @return um Funcionario dono da postagem
     */
    public Funcionario getProprietario();
}
