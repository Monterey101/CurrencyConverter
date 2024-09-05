package org.example;

import java.text.SimpleDateFormat;
import java.util.*;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {

        public Scene getMainMenuScene(Stage stage, SessionHandler SH) {

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

                Button manage = new Button("✎");
                manage.setStyle(
                                "-fx-background-color: rgb(211,58,60); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                manage.setOnMouseEntered(e -> manage.setStyle(
                                "-fx-background-color: rgb(241, 88, 90); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                manage.setOnMouseExited(e -> manage.setStyle(
                                "-fx-background-color: rgb(211,58,60); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                manage.setOnAction(e -> {
                        SH.navigateToManagement();
                });

                Button convertButton = new Button("⇄");
                convertButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                convertButton.setOnMouseEntered(e -> convertButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                convertButton.setOnMouseExited(e -> convertButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                convertButton.setOnAction(e -> {
                        SH.navigateToConverter();
                });

                Button viewTableButton = new Button("▦");
                viewTableButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                viewTableButton.setOnMouseEntered(e -> viewTableButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                viewTableButton.setOnMouseExited(e -> viewTableButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                viewTableButton.setOnAction(e -> {
                        SH.navigateToTable();
                });

                Button viewHistoryButton = new Button("⧖");
                viewHistoryButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");
                viewHistoryButton.setOnMouseEntered(e -> viewHistoryButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                viewHistoryButton.setOnMouseExited(e -> viewHistoryButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                viewHistoryButton.setOnAction(e -> {
                        SH.navigateToHistory();
                });

                manage.setMaxWidth(42);
                manage.setMinWidth(42);
                convertButton.setMaxWidth(42);
                convertButton.setMinWidth(42);
                viewTableButton.setMaxWidth(42);
                viewTableButton.setMinWidth(42);

                HBox keyinfoRowBox;

                if (SH.getCurrentSession().getUsername().equals("admin")) {
                        keyinfoRowBox = new HBox(10, balanceBox, middleSpacer, manage, convertButton, viewTableButton,
                                        viewHistoryButton);
                } else {
                        keyinfoRowBox = new HBox(10, balanceBox, middleSpacer, convertButton, viewTableButton,
                                        viewHistoryButton);
                }

                keyinfoRowBox.setPadding(new Insets(10, 200, 10, 200));

                Text title = new Text("Holdings");
                title.setFont(new Font("Arial", 22));
                title.setFill(Color.WHITE);
                title.setStyle("-fx-font-weight: bold;");

                Region rightSpacer2 = new Region();
                HBox.setHgrow(rightSpacer2, Priority.ALWAYS);
                HBox titleBox = new HBox(10, title, rightSpacer2);
                titleBox.setPadding(new Insets(10, 200, 10, 200));

                // HOLDINGS TABLE

                TableView<HashMap.Entry<String, Double>> tableView = new TableView<>();

                // Create columns
                TableColumn<HashMap.Entry<String, Double>, String> currencyColumn = new TableColumn<>("CURRENCY");
                currencyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
                currencyColumn.setPrefWidth(490);
                currencyColumn.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-font-size: 18px;");

                TableColumn<HashMap.Entry<String, Double>, Double> amountColumn = new TableColumn<>("AMOUNT");
                amountColumn
                                .setCellValueFactory(
                                                cellData -> new SimpleDoubleProperty(cellData.getValue().getValue())
                                                                .asObject());
                amountColumn.setPrefWidth(490);
                amountColumn.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-font-size: 18px;");

                // Add columns to table
                tableView.getColumns().add(currencyColumn);
                tableView.getColumns().add(amountColumn);

                ObservableList<Map.Entry<String, Double>> data = FXCollections
                                .observableArrayList(SH.getCurrentSession().getHoldings().entrySet());
                tableView.setItems(data);

                tableView.getStylesheets().add(getClass().getResource("/styles/tableStyle.css").toExternalForm());

                for (TableColumn<?, ?> column : tableView.getColumns()) {
                        column.setStyle(
                                        "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-font-size: 18px;");
                }

                VBox outerVBox = new VBox(20, headerzStack, keyinfoRowBox, titleBox, tableView);
                outerVBox.setPadding(new Insets(10));

                // Create the StackPane to hold everything
                StackPane zStack = new StackPane(outerVBox);
                zStack.setStyle("-fx-background-color: rgb(21,21,29);"); // Set the background color of the entire scene

                Scene scene = new Scene(zStack, 1400, 900);
                return scene;
        }

        public static void main(String[] args) {

        }
}
