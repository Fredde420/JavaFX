package com.example.javafx;

import database.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class UserRegisterController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField adressRad1Field;
    @FXML private TextField postNrField;
    @FXML private TextField ortField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleRegister(ActionEvent event) {
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String adressRad1 = adressRad1Field.getText();
        String postNr = postNrField.getText();
        String ort = ortField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Connection conn = database.connect()) {

            // 1. Skapa user
            String insertUser = "INSERT INTO user (fName, lName, email, phoneNr, categoryID) VALUES (?, ?, ?, ?, 1)";
            PreparedStatement userStmt = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, fName);
            userStmt.setString(2, lName);
            userStmt.setString(3, email);
            userStmt.setString(4, phone);
            userStmt.executeUpdate();

            ResultSet rs = userStmt.getGeneratedKeys();
            if (rs.next()) {
                int newUserID = rs.getInt(1);

                // 2. Lägg till adress kopplad till användaren
                String insertAdress = "INSERT INTO adress (adressRad1, postNr, ort, userID) VALUES (?, ?, ?, ?)";
                PreparedStatement adressStmt = conn.prepareStatement(insertAdress);
                adressStmt.setString(1, adressRad1);
                adressStmt.setString(2, postNr);
                adressStmt.setString(3, ort);
                adressStmt.setInt(4, newUserID);
                adressStmt.executeUpdate();

                // 3. Lägg till login
                String insertLogin = "INSERT INTO user_login (userID, username, password) VALUES (?, ?, ?)";
                PreparedStatement loginStmt = conn.prepareStatement(insertLogin);
                loginStmt.setInt(1, newUserID);
                loginStmt.setString(2, username);
                loginStmt.setString(3, password); // OBS: hash för produktion!
                loginStmt.executeUpdate();

                messageLabel.setText("Användare registrerad!");
            } else {
                messageLabel.setText("Kunde inte skapa användare.");
            }

        } catch (SQLException e) {
            messageLabel.setText("Fel: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
