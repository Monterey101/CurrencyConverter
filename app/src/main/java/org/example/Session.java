package org.example;

import java.util.*;

public class Session {

    String username;
    String passwordHash;
    double balance;
    HashMap<String, Double> holdings;

    public void calculateBalance() {
        double sum = 0;
        for (String currency : holdings.keySet()) {
            // convert and add to sum
            sum = sum + holdings.get(currency);
        }
        this.balance = sum;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.passwordHash;
    }

    public double getBalance() {
        return this.balance;
    }

    public HashMap<String, Double> getHoldings() {
        return this.holdings;
    }

    public void attemptExchange(String fromCurrency, Double fromAmount, String toCurrency, Double toAmount) {

        if (!holdings.containsKey(fromCurrency)) {
            return;
        }
        if (!holdings.containsKey(toCurrency)) {
            holdings.put(toCurrency, 0.0);
        }

        if (holdings.get(fromCurrency) < fromAmount) {
            return;
        }

        holdings.put(fromCurrency, holdings.get(fromCurrency) - fromAmount);
        holdings.put(toCurrency, holdings.get(toCurrency) + toAmount);
    }

    public static void main(String[] args) {

    }
}
