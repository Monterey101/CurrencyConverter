package org.example;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;

public class Converter {

    List<String> availableCurrencies;
    List<String> popularCurrencies = new ArrayList<String>(Arrays.asList("AUD", "SGD", "USD", "EUR"));
    HashMap<String, Double> conversionRates;

    public void loadAvailableCurrencies() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/availableCurrencies.json")) {
            Type availableCurrenciesListType = new TypeToken<List<String>>() {
            }.getType();
            this.availableCurrencies = gson.fromJson(reader, availableCurrenciesListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAvailableCurrencies() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(this.availableCurrencies);

        System.out.println("this is what is saving:\n" + json);

        try (FileWriter fileWriter = new FileWriter("src/main/resources/availableCurrencies.json")) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAvailableRate(String s) {
        if (!this.availableCurrencies.contains(s.toUpperCase())) {
            this.availableCurrencies.add(s.toUpperCase());
        }
        saveAvailableCurrencies();
    }

    public void setPopularCurrencies(ArrayList<String> currencyList) {
        this.popularCurrencies = currencyList;
    }

    public List<List<Double>> getPopularRates() {
        List<List<Double>> table = new ArrayList<>();
        for (String fromCurrency : this.popularCurrencies) {
            List<Double> row = new ArrayList<Double>();
            for (String toCurrency : this.popularCurrencies) {
                Double rate = round(this.conversionRates.get(fromCurrency) / this.conversionRates.get(toCurrency), 4);
                row.add(rate);
            }
            table.add(row);
        }
        return table;
    }

    public String getPopularCell(int row, int col) {

        if (row == 0 && col == 0) {
            return "";
        }

        // first row
        if (row == 0) {
            return this.popularCurrencies.get(col - 1);
        }

        // first col
        if (col == 0) {
            return this.popularCurrencies.get(row - 1);
        }

        return this.getPopularRates().get(col - 1).get(row - 1).toString();
    }

    public void loadConversionRates() {
        Gson gson = new Gson();

        try {
            FileReader reader = new FileReader("src/main/resources/rates.json");

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonObject conversionRatesJson = jsonObject.getAsJsonObject("conversion_rates");
            Type type = new TypeToken<HashMap<String, Double>>() {
            }.getType();

            this.conversionRates = gson.fromJson(conversionRatesJson, type);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConversionRates() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileReader reader = new FileReader("src/main/resources/rates.json")) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // Replace the old "conversion_rates" with the updated one
            JsonObject conversionRatesJson = gson.toJsonTree(this.conversionRates).getAsJsonObject();
            jsonObject.add("conversion_rates", conversionRatesJson);

            // Write the updated JSON object back to the file
            try (FileWriter writer = new FileWriter("src/main/resources/rates.json")) {
                gson.toJson(jsonObject, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNewRate(String symbol, double amount) {
        this.conversionRates.put(symbol.toUpperCase(), 1 / amount);
        System.out.println("Just set: ");
        System.out.println(symbol.toUpperCase() + "to" + this.conversionRates.get(symbol.toUpperCase()));
    }

    public Double getExchange(String fromSymbol, String toSymbol, Double amount) {
        return (this.conversionRates.get(toSymbol) / this.conversionRates.get(fromSymbol) * amount);
    }

    public Double getBalanceFromHoldings(HashMap<String, Double> holdings) {
        Double sum = 0.0;
        for (String symbol : holdings.keySet()) {
            // convert to usd
            sum += getExchange(symbol, "USD", holdings.get(symbol));

            System.out.println(getExchange(symbol, "USD", holdings.get(symbol)));

            // Double rate = 1 / this.conversionRates.get(symbol);
            // sum += rate * holdings.get(symbol);
            // System.out.println(String.format("rate: %s", rate.toString()));
        }
        return round(sum, 2);
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public Converter() {
        loadAvailableCurrencies();
        loadConversionRates();
    }
}
