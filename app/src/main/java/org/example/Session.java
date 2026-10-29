package org.example;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Session {

    String username;
    String passwordHash;
    String salt;
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

    public boolean checkPassword(String raw) {
        return this.passwordHash.equals(hash(raw, this.salt));
    }

    public static String[] hashNewPassword(String raw) {
        byte[] saltBytes = new byte[16];
        new SecureRandom().nextBytes(saltBytes);
        String salt = HexFormat.of().formatHex(saltBytes);
        return new String[]{ hash(raw, salt), salt };
    }

    private static String hash(String raw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(HexFormat.of().parseHex(salt));
            return HexFormat.of().formatHex(md.digest(raw.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
