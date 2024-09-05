package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.element.Table;
import java.io.FileOutputStream;

import com.itextpdf.layout.element.Table;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SessionHandler {
    private Session currentSession;
    private List<Session> sessionList;
    private String currentWindow = "login";
    public Converter converter;

    private Stage stage;

    public SessionHandler(Stage stage) {
        this.stage = stage;
        this.converter = new Converter();
        loadAccounts();
    }

    private void loadAccounts() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/sessionData.json")) {
            Type accountListType = new TypeToken<List<Session>>() {
            }.getType();
            this.sessionList = gson.fromJson(reader, accountListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderUI() {
        switch (currentWindow) {
            case "login":
                showLoginWindow();
                break;
            case "create":
                // showMainMenu();
                showCreateAccount();
                break;
            case "main":
                showMainMenu();
                break;
            case "converter":
                showConverter();
                break;
            case "table":
                showTable();
                break;
            case "manage":
                showManagementWindow();
                break;
            case "history":
                showHistoryWindow();
                break;
            default:
                showLoginWindow();
        }

        stage.setMinWidth(1400);
        stage.setMinHeight(900);
        stage.setMaxWidth(1400);
        stage.setMaxHeight(900);
        stage.setTitle("Currency Converter");
        stage.show();
    }

    public void showLoginWindow() {
        LoginWindow loginWindow = new LoginWindow();
        Scene loginScene = loginWindow.getLoginScene(stage, this);
        stage.setScene(loginScene);
    }

    public void showCreateAccount() {
        CreateAccountWindow CAWindow = new CreateAccountWindow();
        Scene CAScene = CAWindow.getLoginScene(stage, this);
        stage.setScene(CAScene);
    }

    public void showMainMenu() {
        MainMenu MainMenuWindow = new MainMenu();
        Scene MMScene = MainMenuWindow.getMainMenuScene(stage, this);
        stage.setScene(MMScene);
    }

    public void showConverter() {
        ConvertWindow ConvertWindow = new ConvertWindow();
        Scene ConvertScene = ConvertWindow.getConverterScene(stage, this);
        stage.setScene(ConvertScene);
    }

    public void showTable() {
        TableWindow TableWindow = new TableWindow();
        Scene TableScene = TableWindow.getTableScene(stage, this);
        stage.setScene(TableScene);
    }

    public void showManagementWindow() {
        ManagementWindow ManagementWindow = new ManagementWindow();
        Scene ManagementScene = ManagementWindow.getManagementWindow(stage, this);
        stage.setScene(ManagementScene);
    }

    public void showHistoryWindow() {
        HistoryWindow HistoryWindow = new HistoryWindow();
        Scene HistoryScene = HistoryWindow.getHistoryeScene(stage, this);
        stage.setScene(HistoryScene);
    }

    public void attemptLogin(String username, String password) {
        System.out.println(String.format("checking: %s and %s", username, password));
        if (checkCredentials(username, password)) {
            for (Session session : this.sessionList) {
                if (session.getUsername().equals(username) && session.getPassword().equals(password)) {
                    this.currentSession = session;
                    break;
                }
            }
            this.currentWindow = "main";
            renderUI();
        } else {
            System.out.println("Invalid credentials");
        }
    }

    public void logout() {
        currentSession = null;
        currentWindow = "login";
        renderUI();
    }

    public void attemptAccountCreation(String username, String password) {

        for (Session session : this.sessionList) {
            if (session.getUsername().equals(username)) {
                return;
            }
        }

        // Create a new session for the user
        Session newSession = new Session();
        newSession.username = username;
        newSession.passwordHash = password;
        newSession.balance = 100.00;
        newSession.holdings = new HashMap<>();
        newSession.holdings.put("USD", 100.00);

        // Add the new session to the list
        sessionList.add(newSession);

        saveSessionData(this.currentSession);

    }

    public void saveSessionData(Session session) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src/main/resources/sessionData.json")) {
            gson.toJson(sessionList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToCreateAccount() {
        this.currentWindow = "create";
        renderUI();
    }

    public void navigateToLogin() {
        this.currentWindow = "login";
        renderUI();
    }

    public void navigateToMainMenu() {
        this.currentWindow = "main";
        renderUI();
    }

    public void navigateToConverter() {
        this.currentWindow = "converter";
        renderUI();
    }

    public void navigateToTable() {
        this.currentWindow = "table";
        renderUI();
    }

    public void navigateToManagement() {
        this.currentWindow = "manage";
        renderUI();
    }

    public void navigateToHistory() {
        this.currentWindow = "history";
        renderUI();
    }

    private boolean checkCredentials(String username, String password) {

        for (Session session : this.sessionList) {
            if (session.getUsername().equals(username) &&
                    session.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Session getCurrentSession() {
        return this.currentSession;
    }

    public static List<ChangeLogEntry> readChangeLog() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/changeLog.json")) {
            Type listType = new TypeToken<ArrayList<ChangeLogEntry>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String getExchangeRateChange(String fromSymbol, String toSymbol) {
        Double prevX = -1.0;
        Double prevY = -1.0;
        Double X = -1.0;
        Double Y = -1.0;

        List<ChangeLogEntry> logList = readChangeLog();

        for (ChangeLogEntry log : logList) {
            if (log.getLogSymbol().equals(fromSymbol)) {
                prevX = X;
                X = log.getLogValue();
            }
            if (log.getLogSymbol().equals(toSymbol)) {
                prevY = Y;
                Y = log.getLogValue();
            }
        }

        // no values found for one of the currencies, therefore no change
        if (X == -1.0 || Y == -1.0) {
            return "";
        }

        // both currencies only have 1 value, therefore no change
        if (prevX == -1.0 && prevY == -1.0) {
            return "";
        }

        if (prevX == -1.0) {
            if (prevY < Y) {
                return "↓";
            } else if (prevY > Y) {
                return "↑";
            } else {
                return "";
            }
        }

        if (prevY == -1.0) {
            if (prevX < X) {
                return "↑";
            } else if (prevX > X) {
                return "↓";
            } else {
                return "";
            }
        }

        if (prevY / prevX < Y / X) {
            return "↑";
        } else if (prevY / prevX > Y / X) {
            return "↓";
        }
        return "";
    }

    public javafx.scene.text.Text getChangeText(int row, int col) {
        String textString = "";
        if (row == 0 || col == 0) {
            return new javafx.scene.text.Text(textString);
        }

        String fromSymbol = this.converter.popularCurrencies.get(row - 1);
        String toSymbol = this.converter.popularCurrencies.get(col - 1);

        textString = getExchangeRateChange(fromSymbol, toSymbol);
        javafx.scene.text.Text text = new javafx.scene.text.Text(textString);
        if (textString.equals("↑")) {
            text.setFill(Color.GREEN);
        } else if (textString.equals("↓")) {
            text.setFill(Color.RED);
        }
        return text;
    }

    public static void writeChangeLog(String user, String symbol, double value) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<ChangeLogEntry> changeLog = readChangeLog();
        ChangeLogEntry entry = new ChangeLogEntry(user, symbol.toUpperCase(), value, new Date().toString());
        changeLog.add(entry);
        try (FileWriter writer = new FileWriter("src/main/resources/changeLog.json")) {
            gson.toJson(changeLog, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReport(String currencyA, String currencyB, Date start, Date end) {

        SimpleDateFormat sdfStart = new SimpleDateFormat("d MMM YYYY HH:mm:ss");
        sdfStart.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        String formattedStartDate = sdfStart.format(start);

        SimpleDateFormat sdfEnd = new SimpleDateFormat("d MMM YYYY HH:mm:ss");
        sdfEnd.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        String formattedEndDate = sdfStart.format(end);

        String filePath = "";

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            filePath = System.getProperty("user.home") + "\\Downloads\\ExchangeReport.pdf";
        } else {
            filePath = System.getProperty("user.home") + "/Downloads/ExchangeReport.pdf";
        }

        try {
            // Create a PDF writer
            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // PdfFont titleFont = PdfFontFactory.createFont(PdfFontFactory.);
            Paragraph title = new Paragraph(currencyA + " vs " + currencyB)
                    // .setFont(titleFont)
                    .setFontSize(24)
                    .setFontColor(new DeviceRgb(21, 21, 29))
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Add custom text at the top with styling
            Paragraph header = new Paragraph()
                    .add(new Text("Generated on: ").setBold())
                    .add(new Text(new SimpleDateFormat("d MMM YYYY hh:mm:ss").format(new Date())).setFontSize(12))
                    .add("\n")
                    .add(new Text("Generated by: ").setBold())
                    .add(new Text("Currency Converter Inc").setFontSize(12))
                    .add("\n")
                    .add(new Text("Requested by: ").setBold())
                    .add(new Text(this.getCurrentSession().getUsername()).setFontSize(12))
                    .add("\n")
                    .add("\n")
                    .add(new Text("Summary: ").setBold())
                    .add(new Text("This report provides a detailed history of the exchange rate between " + currencyA
                            + " and " + currencyB + " over the period from "
                            + formattedStartDate + " to "
                            + formattedEndDate + ". ")
                            .setFontSize(12))
                    .add("Throughout this date range, you will find a comprehensive record of the fluctuations in the exchange rate for the selected currency pair, as recorded in our database.")
                    .add("\n")
                    .add("\n");

            document.add(header);

            // Create a table with 3 columns
            Table table = new Table(UnitValue.createPercentArray(new float[] { 2, 1, 1 })).useAllAvailableWidth();
            table.addHeaderCell("Date");
            table.addHeaderCell(currencyA + "/" + currencyB + " Rate");
            table.addHeaderCell(currencyB + "/" + currencyA + " Rate");

            boolean rowFound = false;

            List<ChangeLogEntry> logList = readChangeLog();

            boolean foundA = false;
            double A = 0.0;
            boolean foundB = false;
            double B = 0.0;

            // Populate the table with data
            for (ChangeLogEntry log : logList) {

                Date logDate = convertStringToDate(log.getLogDate());

                if (log.getLogSymbol().equals(currencyA)) {
                    foundA = true;
                    A = log.getLogValue();
                } else if (log.getLogSymbol().equals(currencyB)) {
                    foundB = true;
                    B = log.getLogValue();
                } else {
                    continue;
                }

                if (logDate.before(start)) {
                    continue;
                } else if (logDate.after(end)) {
                    break;
                }

                if (foundA && foundB) {
                    rowFound = true;
                    table.addCell(log.getLogDate().toString());
                    table.addCell(String.valueOf(Converter.round(A / B, 6)));
                    table.addCell(String.valueOf(Converter.round(B / A, 6)));
                }
            }

            if (!rowFound) {
                table.addCell("N/A");
                table.addCell("No Rate Found");
                table.addCell("No Rate Found");
            }

            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();

            // Automatically open the PDF after generating
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start", filePath });
            } else {
                Runtime.getRuntime().exec(new String[] { "open", filePath });
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Date convertStringToDate(String dateString) {
        // Define the date format corresponding to the input string
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        try {
            // Parse the date string into a Date object
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // or handle the exception as per your need
        }
    }

    public void reload() {
        System.out.println("reloading...");
        // API CALL
        converter.saveAvailableCurrencies();
        converter.saveConversionRates();
        currentSession.balance = converter.getBalanceFromHoldings(currentSession.getHoldings());
        saveSessionData(this.currentSession);
        renderUI();
    }

}