package org.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HistoryWindow {

        String comboBoxStyleString = "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; " +
                        "-fx-border-color: rgb(71,71,81); " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 15; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: Arial; " +
                        "-fx-prompt-text-fill: white;";

        public Scene getHistoryeScene(Stage stage, SessionHandler SH) {
                // HEADER

                Text companyName = new Text("Currency Converter");
                companyName.setFont(new Font("Arial", 24));
                companyName.setFill(Color.WHITE);

                Text usernameText1 = new Text(String.format("%s - Welcome %s",
                                new SimpleDateFormat("d MMM YYYY hh:mm:ss").format(new Date()),
                                SH.getCurrentSession().getUsername()));
                usernameText1.setFont(new Font("Arial", 18));
                usernameText1.setFill(Color.rgb(0, 0, 0, 0));

                Text usernameText2 = new Text(String.format("%s - Welcome %s",
                                new SimpleDateFormat("d MMM YYYY hh:mm:ss").format(new Date()),
                                SH.getCurrentSession().getUsername()));
                usernameText2.setFont(new Font("Arial", 18));
                usernameText2.setFill(Color.valueOf("rgb(71,71,81)"));

                // Spacers to ensure the search bar and button are centered
                Region leftSpacer = new Region();
                Region rightSpacer = new Region();
                HBox.setHgrow(leftSpacer, Priority.ALWAYS);
                HBox.setHgrow(rightSpacer, Priority.ALWAYS);

                HBox header = new HBox(10, usernameText1, leftSpacer, companyName, rightSpacer, usernameText2);
                header.setPadding(new Insets(10));
                header.setAlignment(Pos.CENTER);

                Button logoutButton = new Button("Logout");
                logoutButton.setFont(new Font("Arial", 18));
                logoutButton.setStyle("-fx-text-fill: rgb(71,71,81); -fx-background-color: rgba(71,71,81, 0);");

                logoutButton.setOnMouseEntered(
                                e -> logoutButton.setStyle(
                                                "-fx-text-fill: rgb(71,124,234); -fx-background-color: rgba(71,71,81, 0);"));
                logoutButton.setOnMouseExited(
                                e -> logoutButton.setStyle(
                                                "-fx-text-fill: rgb(71,71,81); -fx-background-color: rgba(71,71,81, 0);"));
                logoutButton.setOnAction(e -> {
                        SH.logout();
                });

                Region HlogoutSpacer = new Region();
                HBox HLogoutBox = new HBox(10, logoutButton, HlogoutSpacer);

                StackPane headerzStack = new StackPane(header, HLogoutBox);

                ///////// END OF HEADER /////////

                // CONTENT

                // Key info row
                Text balanceText = new Text("BALANCE");
                balanceText.setFont(new Font("Arial", 12));
                balanceText.setFill(Color.valueOf("rgb(71,71,81)"));

                Text balanceNumber = new Text(String.format("$%s", SH.getCurrentSession().getBalance()));
                balanceNumber.setFont(new Font("Arial", 20));
                balanceNumber.setFill(Color.WHITE);

                Button reloadButton = new Button("⟳");
                reloadButton.setFont(new Font("Arial", 30));
                reloadButton.setStyle(
                                "-fx-background-color: rgba(71, 124, 234, 0); -fx-text-fill: rgb(71, 124, 234); -fx-padding: 0");

                reloadButton.setOnMouseEntered(e -> reloadButton.setStyle(
                                "-fx-background-color: rgba(71, 124, 234, 0); -fx-text-fill: rgb(101, 148,238); -fx-padding: 0"));
                reloadButton.setOnMouseExited(e -> reloadButton.setStyle(
                                "-fx-background-color: rgba(71, 124, 234, 0); -fx-text-fill: rgb(71, 124, 234); -fx-padding: 0"));

                reloadButton.setOnAction(e -> {
                        SH.reload();
                });

                HBox HbalanceBox = new HBox(10, balanceNumber, reloadButton);
                HbalanceBox.setAlignment(Pos.BASELINE_LEFT);

                VBox balanceBox = new VBox(10, balanceText, HbalanceBox);

                Region middleSpacer = new Region();
                HBox.setHgrow(middleSpacer, Priority.ALWAYS);

                Button backButton = new Button("Back");
                backButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                backButton.setOnMouseEntered(e -> backButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                backButton.setOnMouseExited(e -> backButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                backButton.setOnAction(e -> {
                        SH.navigateToMainMenu();
                });

                HBox keyinfoRowBox = new HBox(10, balanceBox, middleSpacer, backButton);
                keyinfoRowBox.setPadding(new Insets(10, 200, 10, 200));

                Text title = new Text("Exchange History");
                title.setFont(new Font("Arial", 22));
                title.setFill(Color.WHITE);
                title.setStyle("-fx-font-weight: bold;");

                Region rightSpacer2 = new Region();
                HBox.setHgrow(rightSpacer2, Priority.ALWAYS);
                HBox titleBox = new HBox(10, title, rightSpacer2);
                titleBox.setPadding(new Insets(10, 200, 10, 200));

                ///////// HISTORY SELECTION GOES HERE /////////

                String styleString = "-fx-background-color: rgb(21, 21, 29); " +
                                "-fx-text-fill: white; " +
                                "-fx-border-color: rgb(71,71,81); " +
                                "-fx-border-width: 1; " +
                                "-fx-background-radius: 15; " +
                                "-fx-font-size: 18px; " +
                                "-fx-font-family: Arial; " +
                                "-fx-prompt-text-fill: white;";

                // select currency pair

                Region currencySpacer = new Region();
                HBox.setHgrow(currencySpacer, Priority.ALWAYS);

                ObservableList<String> currencyList = FXCollections
                                .observableArrayList(SH.converter.availableCurrencies);

                Text selectCurrenciesText = new Text("SELECT CURRENCY PAIR");
                selectCurrenciesText.setFont(new Font("Arial", 12));
                selectCurrenciesText.setFill(Color.valueOf("rgb(71,71,81)"));

                HBox selectCurrenciesPrompt = new HBox(selectCurrenciesText, currencySpacer);
                selectCurrenciesPrompt.setPadding(new Insets(0, 200, 0, 200));

                ComboBox<String> currencyCombo1 = new ComboBox<>(currencyList);
                ComboBox<String> currencyCombo2 = new ComboBox<>(currencyList);

                currencyCombo1.setValue(SH.converter.popularCurrencies.get(0));
                currencyCombo1.setStyle(styleString);
                currencyCombo1.setOnShowing(e -> {
                        currencyCombo1.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });
                currencyCombo2.setValue(SH.converter.popularCurrencies.get(0));
                currencyCombo2.setStyle(styleString);
                currencyCombo2.setOnShowing(e -> {
                        currencyCombo2.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });

                currencyCombo1.setMaxWidth(200);
                currencyCombo1.setMinWidth(200);
                currencyCombo2.setMaxWidth(200);
                currencyCombo2.setMinWidth(200);

                Text currencyText = new Text("against the");
                currencyText.setFont(new Font("Arial", 18));
                currencyText.setFill(Color.valueOf("rgb(71,71,81)"));

                Region currencySelectorSpacer = new Region();
                HBox.setHgrow(currencySelectorSpacer, Priority.ALWAYS);

                HBox currencySelectors = new HBox(10, currencyCombo1, currencyText,
                                currencyCombo2, currencySelectorSpacer);
                currencySelectors.setPadding(new Insets(0, 200, 0, 200));
                currencySelectors.setAlignment(Pos.CENTER);

                VBox currencyBox = new VBox(10, selectCurrenciesPrompt, currencySelectors);
                currencyBox.setAlignment(Pos.CENTER);

                // select date range

                String datePickerStyleString = "-fx-background-color: rgb(21, 21, 29);"
                                + "-fx-text-fill: white;"
                                + "-fx-border-color: rgb(71,71,81);"
                                + "-fx-border-width: 1;"
                                + "-fx-background-radius: 15;"
                                + "-fx-font-size: 18px;"
                                + "-fx-font-family: Arial;"
                                + "-fx-prompt-text-fill: white;"
                                + "/* Popup styling */"
                                + ".date-picker-popup .month-year-pane, .date-picker-popup .day-cell {"
                                + "    -fx-background-color: rgb(21, 21, 29);"
                                + "    -fx-text-fill: white;"
                                + "    -fx-border-color: rgb(71, 71, 81);"
                                + "}";

                Region dateSpacer = new Region();
                HBox.setHgrow(dateSpacer, Priority.ALWAYS);

                Text selectDateRangeText = new Text("SELECT DATE RANGE");
                selectDateRangeText.setFont(new Font("Arial", 12));
                selectDateRangeText.setFill(Color.valueOf("rgb(71,71,81)"));

                HBox selectDateRangePrompt = new HBox(selectDateRangeText, dateSpacer);
                selectDateRangePrompt.setPadding(new Insets(0, 200, 0, 200));

                DatePicker datePicker1 = new DatePicker();
                DatePicker datePicker2 = new DatePicker();

                datePicker1.setStyle(datePickerStyleString);
                datePicker2.setStyle(datePickerStyleString);

                datePicker1.setPromptText("Select date");
                datePicker2.setPromptText("Select date");

                // datePicker1.setOnShowing(e -> {
                // datePicker1.getScene().getStylesheets()
                // .add(getClass().getResource("/styles/datePickerStyle.css").toExternalForm());
                // });
                // datePicker2.setOnShowing(e -> {
                // datePicker2.getScene().getStylesheets()
                // .add(getClass().getResource("/styles/datePickerStyle.css").toExternalForm());
                // });

                datePicker1.setMaxWidth(200);
                datePicker1.setMinWidth(200);
                datePicker2.setMaxWidth(200);
                datePicker2.setMinWidth(200);

                Text dateText1 = new Text("From");
                dateText1.setFont(new Font("Arial", 18));
                dateText1.setFill(Color.valueOf("rgb(71,71,81)"));

                Text dateText2 = new Text("until");
                dateText2.setFont(new Font("Arial", 18));
                dateText2.setFill(Color.valueOf("rgb(71,71,81)"));

                Region dateSelectorSpacer = new Region();
                HBox.setHgrow(dateSelectorSpacer, Priority.ALWAYS);

                HBox dateSelectors = new HBox(10, dateText1, datePicker1, dateText2, datePicker2, dateSelectorSpacer);
                dateSelectors.setPadding(new Insets(0, 200, 0, 200));
                dateSelectors.setAlignment(Pos.CENTER);

                VBox dateBox = new VBox(10, selectDateRangePrompt, dateSelectors);
                dateBox.setAlignment(Pos.BASELINE_LEFT);

                // generate report button

                Region genSpacer = new Region();
                HBox.setHgrow(genSpacer, Priority.ALWAYS);

                Button generateReportButton = new Button("Generate Report");
                generateReportButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                generateReportButton.setMinWidth(250);
                generateReportButton.setMaxWidth(250);

                generateReportButton.setOnMouseEntered(e -> generateReportButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                generateReportButton.setOnMouseExited(e -> generateReportButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                generateReportButton.setOnAction(e -> {

                        if (currencyCombo1.getValue().equals(currencyCombo2.getValue())) {
                                // Pop up
                                Dialog<Void> dialog = new Dialog<>();

                                DialogPane dialogPane = dialog.getDialogPane();
                                dialogPane.getButtonTypes().add(ButtonType.OK);
                                dialogPane.getStylesheets()
                                                .add(getClass().getResource("/styles/dialogStyles.css")
                                                                .toExternalForm());
                                dialog.setTitle("Input error");
                                Label contentLabel = new Label("Currencies must be different");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                        } else if (datePicker1.getValue() == null || datePicker2.getValue() == null
                                        || !convertLocalDateToDate(datePicker1.getValue())
                                                        .before(convertLocalDateToDate(datePicker2.getValue()))) {
                                // Pop up
                                Dialog<Void> dialog = new Dialog<>();

                                DialogPane dialogPane = dialog.getDialogPane();
                                dialogPane.getButtonTypes().add(ButtonType.OK);
                                dialogPane.getStylesheets()
                                                .add(getClass().getResource("/styles/dialogStyles.css")
                                                                .toExternalForm());
                                dialog.setTitle("Input error");
                                Label contentLabel = new Label("Must select valid date range");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();
                        } else {
                                System.setProperty("prism.lcdtext", "false");

                                // Font meiryoBootFont =
                                // Font.loadFont(getClass().getResourceAsStream("/fonts/meiryo_boot.ttf"),
                                // 100);
                                // Label loadingLabel = new Label("\uE052");
                                // loadingLabel.setFont(meiryoBootFont);

                                // Set up the pop-up
                                Dialog<Void> dialog = new Dialog<>();
                                dialog.setTitle("Generating report");

                                DialogPane dialogPane = dialog.getDialogPane();
                                // dialogPane.getButtonTypes().add(ButtonType.CANCEL);
                                dialogPane.getStylesheets()
                                                .add(getClass().getResource("/styles/dialogStyles.css")
                                                                .toExternalForm());

                                // Load the font
                                Font meiryoBootFont = Font.loadFont(
                                                getClass().getResourceAsStream("/fonts/meiryo_boot.ttf"),
                                                20);

                                // Set up the content label
                                Label contentLabel = new Label();
                                contentLabel.setFont(meiryoBootFont);
                                contentLabel.setStyle("-fx-text-fill: white; -fx-font-size: 95px;");
                                contentLabel.getStyleClass().add("meiryo-boot");
                                contentLabel.setAlignment(Pos.TOP_CENTER);
                                contentLabel.setPadding(new Insets(0, 0, 0, 0));

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);

                                // Show the dialog before starting the animation
                                dialog.show();

                                // Run the incrementing task in a separate thread
                                Thread animationThread = new Thread(
                                                () -> incrementFontCharacters(contentLabel, dialog,
                                                                currencyCombo1.getValue(), currencyCombo2.getValue(),
                                                                convertLocalDateToDate(datePicker1.getValue()),
                                                                convertLocalDateToDate(datePicker2.getValue()), SH));
                                animationThread.start();
                        }

                });

                HBox genBox = new HBox(generateReportButton, genSpacer);
                genBox.setPadding(new Insets(30, 0, 0, 200));

                ///////// END OF HISTORY SELECTION /////////

                VBox outerVBox = new VBox(20, headerzStack, keyinfoRowBox, titleBox, currencyBox, dateBox,
                                genBox);
                outerVBox.setPadding(new Insets(10));

                // Create the StackPane to hold everything
                StackPane zStack = new StackPane(outerVBox);
                zStack.setStyle("-fx-background-color: rgb(21,21,29);"); // Set the background color of the entire scene

                Scene scene = new Scene(zStack, 1400, 900);
                scene.getStylesheets().add(getClass().getResource("/styles/datePickerStyle.css").toExternalForm());
                return scene;
        }

        public static Date convertLocalDateToDate(LocalDate localDate) {
                // Convert LocalDate to ZonedDateTime at start of the day in the default time
                // zone
                ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.of("Australia/Sydney"));

                // Convert ZonedDateTime to Date
                return Date.from(zdt.toInstant());
        }

        public static void incrementFontCharacters(Label contentLabel, Dialog<Void> dialog, String cur1, String cur2,
                        Date startDate, Date endDate, SessionHandler SH) {
                char start = '\uE052'; // Starting character
                char end = '\uE09F'; // Ending character

                // Loop through the characters from start to end
                for (char ch = start; ch <= end; ch++) {
                        final String character = String.valueOf(ch);

                        // Update the content label on the JavaFX Application Thread
                        Platform.runLater(() -> contentLabel.setText(character));

                        try {
                                Thread.sleep(20); // Delay for animation effect
                        } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                        }
                }
                start = '\uE0A0';
                end = '\uE0C6';
                // Loop through the characters from start to end
                for (char ch = start; ch <= end; ch++) {
                        final String character = String.valueOf(ch);

                        // Update the content label on the JavaFX Application Thread
                        Platform.runLater(() -> contentLabel.setText(character));

                        try {
                                Thread.sleep(20); // Delay for animation effect
                        } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                        }
                }

                Platform.runLater(() -> {
                        contentLabel.setFont(new Font("Arial", 20));
                        contentLabel.setText("Success: Generated report");
                        contentLabel.setStyle("-fx-text-fill: green; -fx-font-size: 18;");
                        contentLabel.setAlignment(Pos.TOP_LEFT);
                        dialog.setTitle("Success");
                        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        SH.generateReport(cur1, cur2,
                                        startDate,
                                        endDate);
                        dialog.close();
                });
        }

        public static void updateDialogAfterAnimation(DialogPane dialogPane) {
                // Update the dialog title
                dialogPane.getScene().getWindow().setWidth(400);
                dialogPane.getScene().getWindow().setHeight(200);

                // Set new content label after the animation
                Label contentLabelLast = new Label("Success: Generated report");
                contentLabelLast.setStyle("-fx-text-fill: green;");
                contentLabelLast.setFont(Font.font("System", 18)); // Reset font and size

                VBox dialogContentLast = new VBox(contentLabelLast);
                dialogContentLast.setSpacing(10);
                dialogPane.setContent(dialogContentLast);
        }

        public static void main(String[] args) {

        }
}
