package br.com.delarco.androidmobilefirst.data.response;

public class BalanceResponse {

    private String costumer;

    private double balance;

    public BalanceResponse(String costumer, double balance) {
        this.costumer = costumer;
        this.balance = balance;
    }

    public String getCostumer() {
        return costumer;
    }

    public double getBalance() {
        return balance;
    }
}
