package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class TableWindow {

        String comboBoxStyleString = "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; " +
                        "-fx-border-color: rgb(71,71,81); " +
                        "-fx-border-width: 1; " +
                        "-fx-background-radius: 15; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-family: Arial; " +
                        "-fx-prompt-text-fill: white;";

        public Scene getTableScene(Stage stage, SessionHandler SH) {
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

                Text title = new Text("Exchange Table");
                title.setFont(new Font("Arial", 22));
                title.setFill(Color.WHITE);
                title.setStyle("-fx-font-weight: bold;");

                Region rightSpacer2 = new Region();
                HBox.setHgrow(rightSpacer2, Priority.ALWAYS);
                HBox titleBox = new HBox(10, title, rightSpacer2);
                titleBox.setPadding(new Insets(10, 200, 10, 200));

                ///////// EXCHANGE TABLE GOES HERE /////////

                // String syleString = "-fx-font-weight: bold -fx-font-family: Arial;
                // -fx-font-size: 18px; -fx-text-fill: white; -fx-background-color: rgb(21, 21,
                // 29);";
                // String syleString = "-fx-font-weight: bold -fx-font-family: Arial;
                // -fx-font-size: 18px; -fx-text-fill: white; -fx-background-color: rgb(21, 21,
                // 29);";

                GridPane gridPane = new GridPane();
                gridPane.setAlignment(Pos.CENTER); // Center the grid in the scene
                gridPane.setHgap(10); // Horizontal gap between columns
                gridPane.setVgap(10); // Vertical gap between rows

                // Fill the GridPane with Text elements wrapped in StackPane
                for (int row = 0; row < 5; row++) {
                        for (int col = 0; col < 5; col++) {
                                Text text = new Text(SH.converter.getPopularCell(row, col)); // Example text content
                                if (row == 0 || col == 0) {
                                        text.setFill(Color.rgb(71, 71, 81));
                                } else {
                                        text.setFill(Color.WHITE);
                                }
                                title.setFont(new Font("Arial", 24));

                                Text c = SH.getChangeText(row, col);

                                HBox cellBox = new HBox(5, text, c);

                                // Wrap Text in StackPane and set width
                                StackPane stackPane = new StackPane(cellBox);
                                stackPane.setPrefWidth(200); // Set the preferred width to 100 pixels
                                stackPane.setMinWidth(200); // Set the preferred width to 100 pixels
                                stackPane.setMaxWidth(200); // Set the preferred width to 100 pixels
                                stackPane.setAlignment(Pos.CENTER); // Center the text in the StackPane
                                stackPane.setPadding(new Insets(0, 0, 50, 0));

                                // Add the StackPane to the grid
                                gridPane.add(stackPane, col, row);
                        }
                }
                gridPane.setPadding(new Insets(0, 0, 0, 150));

                ///////// END OF EXCHANGE TABLE /////////

                VBox outerVBox = new VBox(20, headerzStack, keyinfoRowBox, titleBox, gridPane);
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
