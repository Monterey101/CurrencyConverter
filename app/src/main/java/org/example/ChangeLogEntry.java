package org.example;

public class ChangeLogEntry {

    private String user;
    private String symbol;
    private double value;
    private String date;

    public ChangeLogEntry(String user, String symbol, double value, String date) {
        this.user = user;
        this.symbol = symbol;
        this.value = value;
        this.date = date;
    }

    public String getLogUser() {
        return this.user;
    }

    public String getLogSymbol() {
        return this.symbol;
    }

    public double getLogValue() {
        return this.value;
    }

    public String getLogDate() {
        return this.date;
    }
}