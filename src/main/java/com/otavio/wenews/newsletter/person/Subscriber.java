package com.otavio.wenews.newsletter.person;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Subscriber implements Serializable {
    private LocalDateTime firstSubscription;
    private LocalDateTime expiresIn;
    private boolean isExpired = false;


    public Subscriber() {
        this.firstSubscription = LocalDateTime.now();
        this.expiresIn = LocalDateTime.now().plusDays(30);
        isExpired = false;
    }

    public LocalDateTime getFirstSubscription() {
        return firstSubscription;
    }

    public LocalDateTime getExpiresIn() {
        return expiresIn;
    }

    public boolean isExpired() {
        if(expiresIn.isAfter(LocalDateTime.now())) {
            this.isExpired = true;
            return true;
        } else {
            return false;
        }
    }

    public void renew() {
        this.isExpired = false;
        expiresIn = LocalDateTime.now().plusDays(30);
    }
}
