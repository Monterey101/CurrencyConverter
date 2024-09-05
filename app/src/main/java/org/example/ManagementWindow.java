package org.example;

import java.text.SimpleDateFormat;
import java.util.*;

import com.jogamp.common.util.InterruptSource.Thread;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagementWindow {
        public Scene getManagementWindow(Stage stage, SessionHandler SH) {

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

                Region leftSpacer = new Region();
                Region rightSpacer = new Region();
                HBox.setHgrow(leftSpacer, Priority.ALWAYS);
                HBox.setHgrow(rightSpacer, Priority.ALWAYS);

                HBox header = new HBox(10, usernameText1, leftSpacer, companyName, rightSpacer, usernameText2);
                header.setPadding(new Insets(10));
                header.setAlignment(Pos.CENTER);

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

                Text title = new Text("Manage Converter");
                title.setFont(new Font("Arial", 22));
                title.setFill(Color.WHITE);
                title.setStyle("-fx-font-weight: bold;");

                Region rightSpacer2 = new Region();
                HBox.setHgrow(rightSpacer2, Priority.ALWAYS);
                HBox titleBox = new HBox(10, title, rightSpacer2);
                titleBox.setPadding(new Insets(10, 200, 10, 200));

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

                // spacers
                Region poptxtSpacer = new Region();
                Region popleftSpacer = new Region();
                Region popmidSpacer = new Region();
                Region poprightSpacer = new Region();
                Region changeSpacer = new Region();
                Region addSpacer = new Region();
                Region saveSpacer = new Region();
                HBox.setHgrow(poptxtSpacer, Priority.ALWAYS);
                HBox.setHgrow(popleftSpacer, Priority.ALWAYS);
                HBox.setHgrow(popmidSpacer, Priority.ALWAYS);
                HBox.setHgrow(poprightSpacer, Priority.ALWAYS);
                HBox.setHgrow(changeSpacer, Priority.ALWAYS);
                HBox.setHgrow(addSpacer, Priority.ALWAYS);
                HBox.setHgrow(saveSpacer, Priority.ALWAYS);

                // Styling
                String styleString = "-fx-background-color: rgb(21, 21, 29); " +
                                "-fx-text-fill: white; " +
                                "-fx-border-color: rgb(71,71,81); " +
                                "-fx-border-width: 1; " +
                                "-fx-background-radius: 15; " +
                                "-fx-font-size: 18px; " +
                                "-fx-font-family: Arial; " +
                                "-fx-prompt-text-fill: white;";

                // popular currencies
                Text popularCurrenciesText = new Text("POPULAR CURRENCIES");
                popularCurrenciesText.setFont(new Font("Arial", 12));
                popularCurrenciesText.setFill(Color.valueOf("rgb(71,71,81)"));

                HBox popularCurrenciesPrompt = new HBox(popularCurrenciesText, poptxtSpacer);
                popularCurrenciesPrompt.setPadding(new Insets(0, 200, 0, 200));

                ObservableList<String> currencyList = FXCollections
                                .observableArrayList(SH.converter.availableCurrencies);

                ComboBox<String> pop1 = new ComboBox<>(currencyList);
                ComboBox<String> pop2 = new ComboBox<>(currencyList);
                ComboBox<String> pop3 = new ComboBox<>(currencyList);
                ComboBox<String> pop4 = new ComboBox<>(currencyList);

                // Set a default value (optional)
                pop1.setValue(SH.converter.popularCurrencies.get(0));
                pop2.setValue(SH.converter.popularCurrencies.get(1));
                pop3.setValue(SH.converter.popularCurrencies.get(2));
                pop4.setValue(SH.converter.popularCurrencies.get(3));

                pop1.setStyle(styleString);
                pop2.setStyle(styleString);
                pop3.setStyle(styleString);
                pop4.setStyle(styleString);

                pop1.setOnShowing(e -> {
                        pop1.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });
                pop2.setOnShowing(e -> {
                        pop2.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });
                pop3.setOnShowing(e -> {
                        pop3.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });
                pop4.setOnShowing(e -> {
                        pop4.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });

                pop1.setMaxWidth(200);
                pop1.setMinWidth(200);
                pop2.setMaxWidth(200);
                pop2.setMinWidth(200);
                pop3.setMaxWidth(200);
                pop3.setMinWidth(200);
                pop4.setMaxWidth(200);
                pop4.setMinWidth(200);

                HBox popularSelectors = new HBox(pop1, popleftSpacer, pop2, popmidSpacer, pop3, poprightSpacer, pop4);
                popularSelectors.setPadding(new Insets(0, 200, 0, 200));

                VBox popularBox = new VBox(10, popularCurrenciesPrompt, popularSelectors);

                // change currency
                Text changeCurrenciesText = new Text("CHANGE CURRENCY RATE");
                changeCurrenciesText.setFont(new Font("Arial", 12));
                changeCurrenciesText.setFill(Color.valueOf("rgb(71,71,81)"));

                HBox changeCurrenciesPrompt = new HBox(changeCurrenciesText, changeSpacer);
                changeCurrenciesPrompt.setPadding(new Insets(0, 200, 0, 200));

                ComboBox<String> changeCombo = new ComboBox<>(currencyList);

                changeCombo.setValue(SH.converter.popularCurrencies.get(0));
                changeCombo.setStyle(styleString);
                changeCombo.setOnShowing(e -> {
                        changeCombo.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });

                changeCombo.setMaxWidth(200);
                changeCombo.setMinWidth(200);

                Text changeText = new Text("set rate to USD");
                changeText.setFont(new Font("Arial", 18));
                changeText.setFill(Color.valueOf("rgb(71,71,81)"));

                TextField changeAmount = new TextField();
                changeAmount.setPromptText("1");
                changeAmount.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-border-color: rgb(71,71,81); -fx-border-width: 1; -fx-background-radius: 15; -fx-font-size: 18px; -fx-font-family: Arial; -fx-prompt-text-fill: rgb(71,71,81);");
                changeAmount.setMaxWidth(200);
                changeAmount.setMinWidth(200);

                Region changeSelectorSpacer = new Region();
                HBox.setHgrow(changeSelectorSpacer, Priority.ALWAYS);

                HBox changeSelectors = new HBox(10, changeCombo, changeText, changeAmount, changeSelectorSpacer);
                changeSelectors.setPadding(new Insets(0, 200, 0, 200));
                changeSelectors.setAlignment(Pos.CENTER);

                VBox changeBox = new VBox(10, changeCurrenciesPrompt, changeSelectors);
                changeBox.setAlignment(Pos.CENTER);

                // add currency
                Text newCurrencyText = new Text("ADD CURRENCY RATE");
                newCurrencyText.setFont(new Font("Arial", 12));
                newCurrencyText.setFill(Color.valueOf("rgb(71,71,81)"));

                HBox newCurrenciesPrompt = new HBox(newCurrencyText, addSpacer);
                newCurrenciesPrompt.setPadding(new Insets(0, 200, 0, 200));

                TextField newSymbolName = new TextField();
                newSymbolName.setPromptText("Enter symbol name");
                newSymbolName.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-border-color: rgb(71,71,81); -fx-border-width: 1; -fx-background-radius: 15; -fx-font-size: 18px; -fx-font-family: Arial; -fx-prompt-text-fill: rgb(71,71,81);");
                newSymbolName.setMaxWidth(200);
                newSymbolName.setMinWidth(200);

                Text newText = new Text("set rate to USD");
                newText.setFont(new Font("Arial", 18));
                newText.setFill(Color.valueOf("rgb(71,71,81)"));

                TextField setNewCurrencyRate = new TextField();
                setNewCurrencyRate.setPromptText("1");
                setNewCurrencyRate.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-border-color: rgb(71,71,81); -fx-border-width: 1; -fx-background-radius: 15; -fx-font-size: 18px; -fx-font-family: Arial; -fx-prompt-text-fill: rgb(71,71,81);");
                setNewCurrencyRate.setMaxWidth(200);
                setNewCurrencyRate.setMinWidth(200);

                Region newSelectorSpacer = new Region();
                HBox.setHgrow(newSelectorSpacer, Priority.ALWAYS);

                HBox newSelectors = new HBox(10, newSymbolName, newText, setNewCurrencyRate, newSelectorSpacer);
                newSelectors.setPadding(new Insets(0, 200, 0, 200));
                newSelectors.setAlignment(Pos.CENTER);

                VBox newBox = new VBox(10, newCurrenciesPrompt, newSelectors);
                changeBox.setAlignment(Pos.CENTER);

                // save button;
                Button saveButton = new Button("Save");
                saveButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                saveButton.setMinWidth(100);
                saveButton.setMaxWidth(100);

                saveButton.setOnMouseEntered(e -> saveButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                saveButton.setOnMouseExited(e -> saveButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                saveButton.setOnAction(e -> {

                        // Pop up
                        Dialog<Void> dialog = new Dialog<>();

                        DialogPane dialogPane = dialog.getDialogPane();
                        dialogPane.getButtonTypes().add(ButtonType.OK);
                        dialogPane.getStylesheets()
                                        .add(getClass().getResource("/styles/dialogStyles.css").toExternalForm());

                        SH.converter.setPopularCurrencies(new ArrayList<String>(
                                        Arrays.asList(pop1.getValue(), pop2.getValue(), pop3.getValue(),
                                                        pop4.getValue())));

                        // set new rate
                        if (!changeAmount.getText().equals("") && !changeCombo.getValue().equals("USD")) {
                                try {
                                        SH.converter.setNewRate(changeCombo.getValue(),
                                                        Double.parseDouble(changeAmount.getText()));
                                        System.out.println("hello?");
                                        SessionHandler.writeChangeLog(SH.getCurrentSession().getUsername(),
                                                        changeCombo.getValue(),
                                                        Double.parseDouble(changeAmount.getText()));
                                } catch (Exception x) {

                                }
                        }

                        if (changeCombo.getValue().equals("USD")) {
                                try {
                                        SH.converter.setNewRate(changeCombo.getValue(),
                                                        Double.parseDouble(changeAmount.getText()));
                                        SessionHandler.writeChangeLog(SH.getCurrentSession().getUsername(),
                                                        changeCombo.getValue(),
                                                        Double.parseDouble(changeAmount.getText()));
                                } catch (Exception x) {

                                }
                        }

                        // create new currency
                        if (!newSymbolName.getText().equals("") && newSymbolName.getText().length() == 3
                                        && !newSymbolName.getText().equals("USD")
                                        && !setNewCurrencyRate.getText().equals("")) {
                                try {
                                        SH.converter.setNewRate(newSymbolName.getText(),
                                                        Double.parseDouble(setNewCurrencyRate.getText()));
                                        SH.converter.addAvailableRate(newSymbolName.getText());
                                        SessionHandler.writeChangeLog(SH.getCurrentSession().getUsername(),
                                                        newSymbolName.getText(),
                                                        Double.parseDouble(setNewCurrencyRate.getText()));
                                } catch (Exception x) {

                                }
                        }

                        // Pop up logic - determine what error to show

                        // Try to change USD
                        if (changeCombo.getValue().equals("USD")) {

                                dialog.setTitle("Input error");
                                Label contentLabel = new Label("Modifying USD value is prohibited");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                                // invalid changed rate
                        } else if (!changeAmount.getText().equals("") && !isValidDouble(changeAmount.getText())) {

                                dialog.setTitle("Input error");
                                Label contentLabel = new Label("Invalid exchange rate entered");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                        } else if (!newSymbolName.getText().equals("") && !isValidSymbol(newSymbolName.getText())) {

                                dialog.setTitle("Input error");
                                Label contentLabel = new Label("Symbol must be exactly 3 English letters");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                        } else if (!setNewCurrencyRate.getText().equals("")
                                        && !isValidDouble(setNewCurrencyRate.getText())) {

                                dialog.setTitle("Input error");
                                Label contentLabel = new Label("Invalid exchange rate entered");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                        } else {

                                dialog.setTitle("Success");
                                Label contentLabel = new Label("Changes have been saved");
                                contentLabel.setStyle("-fx-text-fill: green;");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                                SH.reload();
                                SH.navigateToMainMenu();
                        }

                });

                HBox saveBox = new HBox(saveButton, saveSpacer);
                saveBox.setPadding(new Insets(30, 200, 0, 200));

                VBox outerVBox = new VBox(20, headerzStack, keyinfoRowBox, titleBox, popularBox, changeBox, newBox,
                                saveBox);
                outerVBox.setPadding(new Insets(10));

                StackPane zStack = new StackPane(outerVBox);
                zStack.setStyle("-fx-background-color: rgb(21,21,29);");

                Scene scene = new Scene(zStack, 1400, 900);
                return scene;
        }

        public boolean isValidDouble(String s) {
                try {
                        Double d = Double.valueOf(s);
                        return true;
                } catch (NumberFormatException e) {
                        return false;
                }
        }

        public boolean isValidSymbol(String s) {
                if (s.length() != 3) {
                        return false;
                }
                char[] chars = s.toCharArray();
                for (char c : chars) {
                        if (!Character.isLetter(c)) {
                                return false;
                        }
                }
                return true;
        }

        public static void main(String[] args) {

        }
}
