package com.otavio.wenews.newsletter.person;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Subscriber implements Serializable {
    private LocalDateTime firstSubscription;
    private LocalDateTime expiresIn;
    private boolean isExpired = false;


    public Subscriber(LocalDateTime firstSubscription, LocalDateTime expiresIn) {
        this.firstSubscription = firstSubscription;
        this.expiresIn = expiresIn;
        isExpired = false;
    }

    public LocalDateTime getFirstSubscription() {
        return firstSubscription;
    }

    public LocalDateTime getExpiresIn() {
        return expiresIn;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
