package br.com.delarco.androidmobilefirst.data.model;

import java.io.Serializable;

public class Statement implements Serializable {

    private String description;

    private double value;

    private double balance;

    private char type;

    public Statement(String description, double value, double balance, char type) {
        this.description = description;
        this.value = value;
        this.balance = balance;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public double getBalance() {
        return balance;
    }

    public char getType() {
        return type;
    }
}
