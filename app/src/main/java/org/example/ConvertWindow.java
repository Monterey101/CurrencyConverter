package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public class ConvertWindow {

        private boolean typingHave = false;
        private boolean typingWant = false;

        public Scene getConverterScene(Stage stage, SessionHandler SH) {
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

                Text title = new Text("Converter");
                title.setFont(new Font("Arial", 22));
                title.setFill(Color.WHITE);
                title.setStyle("-fx-font-weight: bold;");

                Region rightSpacer2 = new Region();
                HBox.setHgrow(rightSpacer2, Priority.ALWAYS);
                HBox titleBox = new HBox(10, title, rightSpacer2);
                titleBox.setPadding(new Insets(10, 200, 10, 200));

                // CONVERTER

                ObservableList<String> currencyList = FXCollections
                                .observableArrayList(SH.converter.availableCurrencies);

                // Create a ComboBox and populate it with the options
                ComboBox<String> currencyPicker = new ComboBox<>(currencyList);

                // Set a default value (optional)
                currencyPicker.setValue("USD");

                // Handle selection changes
                currencyPicker.setOnAction(e -> {
                        String selectedOption = currencyPicker.getValue();
                        System.out.println("Selected: " + selectedOption);
                });

                currencyPicker.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); " +
                                                "-fx-text-fill: white; " +
                                                "-fx-border-color: rgb(71,71,81); " +
                                                "-fx-border-width: 1; " +
                                                "-fx-background-radius: 15; " +
                                                "-fx-font-size: 18px; " +
                                                "-fx-font-family: Arial; " +
                                                "-fx-prompt-text-fill: white;");

                currencyPicker.setOnShowing(e -> {
                        currencyPicker.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });

                currencyPicker.setMaxWidth(300);
                currencyPicker.setMinWidth(300);

                // Create a ComboBox and populate it with the options
                ComboBox<String> currencyPicker2 = new ComboBox<>(currencyList);

                // Set a default value (optional)
                currencyPicker2.setValue("AUD");

                currencyPicker2.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); " +
                                                "-fx-text-fill: white; " +
                                                "-fx-border-color: rgb(71,71,81); " +
                                                "-fx-border-width: 1; " +
                                                "-fx-background-radius: 15; " +
                                                "-fx-font-size: 18px; " +
                                                "-fx-font-family: Arial; " +
                                                "-fx-prompt-text-fill: white;");
                currencyPicker2.setMaxWidth(300);
                currencyPicker2.setMinWidth(300);

                currencyPicker2.setOnShowing(e -> {
                        currencyPicker2.getScene().getStylesheets()
                                        .add(getClass().getResource("/styles/comboBoxStyle.css").toExternalForm());
                });

                // 1/3
                Text IHave = new Text("I HAVE");
                IHave.setFont(new Font("Arial", 12));
                IHave.setFill(Color.valueOf("rgb(71,71,81)"));
                IHave.setStyle("-fx-font-weight: bold;");
                // dropdown
                Text haveAmount = new Text("AMOUNT");
                haveAmount.setFont(new Font("Arial", 12));
                haveAmount.setFill(Color.valueOf("rgb(71,71,81)"));
                haveAmount.setStyle("-fx-font-weight: bold;");

                TextField haveField = new TextField();
                haveField.setPromptText("0");
                haveField.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-border-color: rgb(71,71,81); -fx-border-width: 1; -fx-background-radius: 15; -fx-font-size: 18px; -fx-font-family: Arial; -fx-prompt-text-fill: rgb(71,71,81);");
                haveField.setMaxWidth(300);
                haveField.setMinWidth(300);
                VBox haveBox = new VBox(10, IHave, currencyPicker, haveAmount, haveField);

                // 2/3
                Text convertSymbol = new Text("→");
                convertSymbol.setFill(Color.valueOf("rgb(71,71,81)"));
                convertSymbol.setFont(new Font("Arial", 150));

                // 3/3
                Text IWant = new Text("I WANT");
                IWant.setFont(new Font("Arial", 12));
                IWant.setFill(Color.valueOf("rgb(71,71,81)"));
                IWant.setStyle("-fx-font-weight: bold;");
                // dropdown
                Text wantAmount = new Text("AMOUNT");
                wantAmount.setFont(new Font("Arial", 12));
                wantAmount.setFill(Color.valueOf("rgb(71,71,81)"));
                wantAmount.setStyle("-fx-font-weight: bold;");

                TextField wantField = new TextField();
                wantField.setPromptText("0");
                wantField.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-border-color: rgb(71,71,81); -fx-border-width: 1; -fx-background-radius: 15; -fx-font-size: 18px; -fx-font-family: Arial; -fx-prompt-text-fill: rgb(71,71,81);");
                wantField.setMaxWidth(300);
                wantField.setMinWidth(300);

                // preview conversion
                haveField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!typingWant && isValidDouble(newValue)) { // Only update wantField if typingWant is not
                                                                      // active
                                typingHave = true; // Indicate that typingHave is active
                                try {
                                        double value = Double.parseDouble(newValue);
                                        wantField.setText(String.format("%.4f", SH.converter.getExchange(
                                                        currencyPicker.getValue(), currencyPicker2.getValue(), value))); // Example
                                                                                                                         // conversion
                                        // rate
                                } catch (NumberFormatException e) {
                                        wantField.setText("Invalid amount");
                                }
                                typingHave = false; // Reset the flag after operation
                        }
                });

                wantField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!typingHave && isValidDouble(newValue)) { // Only update haveField if typingHave is not
                                                                      // active
                                typingWant = true; // Indicate that typingWant is active
                                try {
                                        double value = Double.parseDouble(newValue);
                                        haveField.setText(String.format("%.4f", SH.converter.getExchange(
                                                        currencyPicker2.getValue(), currencyPicker.getValue(), value))); // Example
                                                                                                                         // conversion
                                        // rate
                                } catch (NumberFormatException e) {
                                        haveField.setText("Invalid amount");
                                }
                                typingWant = false; // Reset the flag after operation
                        }
                });

                currencyPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                        if (!typingWant) { // Only update wantField if typingWant is not active
                                typingHave = true; // Indicate that typingHave is active
                                try {
                                        double value = Double.parseDouble(haveField.getText());
                                        wantField.setText(String.format("%.4f", SH.converter.getExchange(
                                                        currencyPicker.getValue(), currencyPicker2.getValue(), value))); // Example
                                                                                                                         // conversion
                                        // rate
                                } catch (NumberFormatException e) {
                                        wantField.setText("Invalid amount");
                                }
                                typingHave = false; // Reset the flag after operation
                        }
                });

                // update on currency selection

                currencyPicker2.valueProperty().addListener((observable, oldValue, newValue) -> {
                        if (!typingHave) { // Only update haveField if typingHave is not active
                                typingWant = true; // Indicate that typingWant is active
                                try {
                                        double value = Double.parseDouble(wantField.getText());
                                        haveField.setText(String.format("%.4f", SH.converter.getExchange(
                                                        currencyPicker2.getValue(), currencyPicker.getValue(), value))); // Example
                                                                                                                         // conversion
                                        // rate
                                } catch (NumberFormatException e) {
                                        haveField.setText("Invalid amount");
                                }
                                typingWant = false; // Reset the flag after operation
                        }
                });

                VBox wantBox = new VBox(10, IWant, currencyPicker2, wantAmount, wantField);

                Region Spacerleft = new Region();
                Region Spacerright = new Region();
                HBox.setHgrow(Spacerleft, Priority.ALWAYS);
                HBox.setHgrow(Spacerright, Priority.ALWAYS);

                HBox converterBox = new HBox(10, haveBox, Spacerleft, convertSymbol, Spacerright, wantBox);
                converterBox.setPadding(new Insets(10, 200, 10, 200));

                Text commision = new Text("Commision: $2.00 USD");
                commision.setFont(new Font("Arial", 12));
                commision.setFill(Color.valueOf("rgb(71,71,81)"));
                commision.setStyle("-fx-font-weight: bold;");

                Button convertButton = new Button("Convert");
                convertButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                convertButton.setMinWidth(300);
                convertButton.setMaxWidth(300);

                convertButton.setOnMouseEntered(e -> convertButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                convertButton.setOnMouseExited(e -> convertButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                convertButton.setOnAction(e -> {

                        try {
                                SH.getCurrentSession().attemptExchange(currencyPicker.getValue(),
                                                Double.parseDouble(haveField.getText()), currencyPicker2.getValue(),
                                                Double.parseDouble(wantField.getText()));

                        } catch (NumberFormatException ex) {

                        }

                        // Pop up
                        Dialog<Void> dialog = new Dialog<>();

                        DialogPane dialogPane = dialog.getDialogPane();
                        dialogPane.getButtonTypes().add(ButtonType.OK);
                        dialogPane.getStylesheets()
                                        .add(getClass().getResource("/styles/dialogStyles.css").toExternalForm());

                        System.out.println("hello?");

                        if (!isValidDouble(haveField.getText()) || !isValidDouble(wantField.getText())
                                        || haveField.getText().equals("") || haveField.getText().equals("0")
                                        || wantField.getText().equals("")
                                        || wantField.getText().equals("0")) {

                                dialog.setTitle("Input error");
                                Label contentLabel = new Label("Invalid amount entered");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                        } else if (Double.parseDouble(haveField.getText()) > SH.getCurrentSession().getHoldings()
                                        .get(currencyPicker.getValue())) {

                                dialog.setTitle("Balance error");
                                Label contentLabel = new Label("Insufficent funds");
                                contentLabel.setStyle("-fx-text-fill: rgb(211,58,60);");

                                VBox dialogContent = new VBox(contentLabel);
                                dialogContent.setSpacing(10);
                                dialogPane.setContent(dialogContent);
                                dialog.setWidth(400);
                                dialog.setHeight(200);
                                dialog.showAndWait();

                        } else {

                                dialog.setTitle("Success");
                                Label contentLabel = new Label("Exchange successfull: Bought " + wantField.getText()
                                                + " " + currencyPicker2.getValue() + " for " + haveField.getText() + " "
                                                + currencyPicker.getValue());
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
                VBox VactionItems = new VBox(10, commision, convertButton);
                VactionItems.setAlignment(Pos.CENTER);
                HBox HactionItems = new HBox(VactionItems);
                HactionItems.setAlignment(Pos.CENTER);
                HactionItems.setPadding(new Insets(30, 0, 0, 0));

                VBox outerVBox = new VBox(20, headerzStack, keyinfoRowBox, titleBox, converterBox, HactionItems);
                outerVBox.setPadding(new Insets(10));

                // Create the StackPane to hold everything
                StackPane zStack = new StackPane(outerVBox);
                zStack.setStyle("-fx-background-color: rgb(21,21,29);"); // Set the background color of the entire scene

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

        public static void main(String[] args) {

        }
}
