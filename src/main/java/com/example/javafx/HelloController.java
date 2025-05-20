package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import java.sql.*;

import database.database;
import model.Item;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private TextField staffUsernameField;
    @FXML
    private PasswordField staffPasswordField;
    @FXML
    private Label staffLoginErrorLabel;

    @FXML
    private TextField userUsernameField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private Label userLoginErrorLabel;

    public void switchToStartSida(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToStaffLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StaffLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToUserLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToReview(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Review.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUserRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserRegister.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/*  Ska nog inte ligga i HelloController
    public void switchToLoanBook(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoanFilm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
*/
    @FXML
    private void handleStaffLogin(ActionEvent event) {
        String username = staffUsernameField.getText();
        String password = staffPasswordField.getText();

        try (Connection conn = database.connect()) {
            String sql = "SELECT * FROM staff_login WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Staff logged in: " + username);

                //Byt scen här!
                Parent root = FXMLLoader.load(getClass().getResource("StaffDashboard.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                staffLoginErrorLabel.setText("Fel användarnamn eller lösenord");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUserLogin(ActionEvent event) {
        String username = userUsernameField.getText();
        String password = userPasswordField.getText();

        try (Connection conn = database.connect()) {
            String sql = "SELECT * FROM user_login WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("User logged in: " + username);

                // Växla till användarens dashboard
                Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                userLoginErrorLabel.setText("Fel användarnamn eller lösenord");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}






