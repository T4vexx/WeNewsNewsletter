package com.otavio.wenews.newsletter.person;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Subscriber
 * Esta classe representa um assinante de uma newsletter.
 * Ela implementa a interface Serializable para permitir a serialização dos objetos.
 * Ela possui atributos para armazenar a data da primeira assinatura, a data de expiração,
 * e um indicador booleano para verificar se a assinatura está expirada ou não.
 * Ela também possui métodos para obter e alterar esses atributos, bem como para renovar a assinatura.
 * @author Otávio Augusto Teixeira
 * @version 1.0
 */
public class Subscriber implements Serializable {
    private LocalDateTime firstSubscription;
    private LocalDateTime expiresIn;
    private boolean isExpired = false;

    /**
     * Construtor padrão da classe Subscriber.
     * Ele inicializa os atributos firstSubscription e expiresIn com a data e hora atual,
     * e o atributo isExpired com false.
     */
    public Subscriber() {
        this.firstSubscription = LocalDateTime.now();
        this.expiresIn = LocalDateTime.now().plusDays(30);
        isExpired = false;
    }

    /**
     * Método que retorna a data da primeira assinatura do assinante.
     * @return Um objeto LocalDateTime que representa a data da primeira assinatura.
     */
    public LocalDateTime getFirstSubscription() {
        return firstSubscription;
    }

    /**
     * Método que retorna a data de expiração da assinatura do assinante.
     * @return Um objeto LocalDateTime que representa a data de expiração da assinatura.
     */
    public LocalDateTime getExpiresIn() {
        return expiresIn;
    }

    /**
     * Método que verifica se a assinatura do assinante está expirada ou não.
     * Ele compara a data de expiração com a data e hora atual, e atualiza o atributo isExpired de acordo.
     * @return Um valor booleano que indica se a assinatura está expirada ou não.
     */
    public boolean isExpired() {
        if(expiresIn.isAfter(LocalDateTime.now())) {
            this.isExpired = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que renova a assinatura do assinante por mais 30 dias.
     * Ele altera o atributo isExpired para false, e o atributo expiresIn para a data e hora atual mais 30 dias.
     */
    public void renew() {
        this.isExpired = false;
        expiresIn = LocalDateTime.now().plusDays(30);
    }
}