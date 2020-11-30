package com.example.microsvc.sb.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Account {
    @Id
    private String id;

    private int customer;

    public enum Type {
        CHECKING,
        SAVING,
    }

    @Enumerated(EnumType.STRING)
    private Type type;

    private String currency;

    @Column(name = "created")
    private LocalDateTime timeCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        return String.format("{ id: %s, customer: %d, type: %s, currency: %s, timeCreated: %s}",
                id, customer, type, currency, timeCreated);
    }
}
