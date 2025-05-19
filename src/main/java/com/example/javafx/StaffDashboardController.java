package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class StaffDashboardController {

    @FXML
    private void handlePurchase(ActionEvent event) throws IOException {
        // Ladda Purchase.fxml (du kan skapa en vy för detta)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafx/Purchase.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Purchase");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
