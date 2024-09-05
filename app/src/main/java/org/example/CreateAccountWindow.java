package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateAccountWindow {

        String username = "";
        String password = "";

        public Scene getLoginScene(Stage stage, SessionHandler SH) {

                // Create the title text
                Text title = new Text("Currency Converter");
                title.setFont(new Font("Arial", 24));
                title.setFill(Color.WHITE);

                // Create the rectangle for the login box
                // Create the rectangle with the specified dimensions and corner radius
                Rectangle loginBox = new Rectangle(420, 360);
                loginBox.setArcHeight(20);
                loginBox.setArcWidth(20);
                loginBox.setFill(Color.rgb(21, 21, 29)); // Background color
                loginBox.setStroke(Color.valueOf("rgb(71,71,81)")); // Border color
                loginBox.setStrokeWidth(1); // Border width

                Text subtitle = new Text("Create Account");
                subtitle.setFont(new Font("Arial", 20));
                subtitle.setFill(Color.WHITE);

                // Create the text fields with background color, white border, and rounded
                // corners
                TextField usernameField = new TextField();
                usernameField.setPromptText("Username");
                usernameField.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-border-color: rgb(71,71,81); -fx-border-width: 1; -fx-background-radius: 15; -fx-font-size: 18px; -fx-font-family: Arial; -fx-prompt-text-fill: rgb(71,71,81);");

                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("Password");
                passwordField.setStyle(
                                "-fx-background-color: rgb(21, 21, 29); -fx-text-fill: white; -fx-border-color: rgb(71,71,81); -fx-border-width: 1; -fx-background-radius: 15; -fx-font-size: 18px; -fx-font-family: Arial; -fx-prompt-text-fill: rgb(71,71,81);");

                // Create the button with a custom color and white text
                Button submitButton = new Button("Submit");
                submitButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;");

                submitButton.setOnMouseEntered(e -> submitButton.setStyle(
                                "-fx-background-color: rgb(101, 148,238); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));
                submitButton.setOnMouseExited(e -> submitButton.setStyle(
                                "-fx-background-color: rgb(71, 124, 234); -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size: 18px; -fx-font-family: Arial;"));

                usernameField.setMaxWidth(300);
                passwordField.setMaxWidth(300);
                submitButton.setMaxWidth(300);

                Text question = new Text("Already have an account?");
                question.setFont(new Font("Arial", 14));
                question.setFill(Color.valueOf("rgb(71,71,81)"));

                Button loginLink = new Button("Login");
                loginLink.setStyle(
                                "-fx-background-color: rgba(71, 124, 234, 0); -fx-text-fill: rgb(71,124,234); -fx-background-radius: 8; -fx-font-size: 14px; -fx-font-family: Arial;");

                loginLink.setOnMouseEntered(e -> loginLink.setStyle(
                                "-fx-background-color: rgba(71, 124, 234, 0); -fx-text-fill: rgb(101, 148,238); -fx-background-radius: 8; -fx-font-size: 14px; -fx-font-family: Arial;"));
                loginLink.setOnMouseExited(e -> loginLink.setStyle(
                                "-fx-background-color: rgba(71, 124, 234, 0); -fx-text-fill: rgb(71,124,234); -fx-background-radius: 8; -fx-font-size: 14px; -fx-font-family: Arial;"));

                HBox createPrompt = new HBox(1, question, loginLink);
                createPrompt.setAlignment(Pos.CENTER);

                // Arrange the text fields and button vertically
                VBox loginForm = new VBox(15, subtitle, usernameField, passwordField, submitButton, createPrompt);
                loginForm.setAlignment(Pos.CENTER);

                // Stack the login form on top of the rectangle
                StackPane loginPane = new StackPane(loginBox, loginForm);
                loginPane.setAlignment(Pos.CENTER);

                // Arrange everything in the center of the screen
                VBox outerVBox = new VBox(20, title, loginPane);
                outerVBox.setAlignment(Pos.CENTER);

                // Create the StackPane to hold everything
                StackPane zStack = new StackPane(outerVBox);
                zStack.setStyle("-fx-background-color: rgb(21,21,29);"); // Set the background color of the entire scene

                // Track window width and height
                stage.widthProperty().addListener((observable, oldValue, newValue) -> {
                        double width = newValue.doubleValue();
                        loginBox.setWidth(width * 0.3);
                        usernameField.setMaxWidth(300);
                        passwordField.setMaxWidth(300);
                        submitButton.setMaxWidth(300);
                });

                stage.heightProperty().addListener((observable, oldValue, newValue) -> {
                        double height = newValue.doubleValue();
                        loginBox.setHeight(height * 0.4);
                        usernameField.setMinHeight(40);
                        passwordField.setMinHeight(40);
                        submitButton.setMinHeight(40);
                });

                submitButton.setOnAction(e -> {
                        String username = usernameField.getText();
                        String password = passwordField.getText();
                        SH.attemptAccountCreation(username, password);
                        SH.attemptLogin(username, password);
                });

                loginLink.setOnAction(e -> {
                        System.out.println("hello?");
                        SH.navigateToLogin();
                });

                // Create the scene and set it to the stage
                Scene scene = new Scene(zStack, 1400, 900);

                return scene;
        }

        public static void main(String[] args) {

        }
}
