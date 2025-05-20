package com.example.javafx;

import database.PurchaseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Purchase;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PurchaseController {

    @FXML private DatePicker purchaseDatePicker;
    @FXML private TextField quantityField;
    @FXML private TextField supplierField;
    @FXML private TextField costPerItemField;
    @FXML private TextField totalCostField;
    @FXML private TextField itemIDField;
    @FXML private TextField staffIDField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            LocalDateTime date = purchaseDatePicker.getValue().atStartOfDay();
            int quantity = Integer.parseInt(quantityField.getText());
            String supplier = supplierField.getText();
            int costPerItem = Integer.parseInt(costPerItemField.getText());
            int totalCost = Integer.parseInt(totalCostField.getText());
            int itemID = Integer.parseInt(itemIDField.getText());
            int staffID = Integer.parseInt(staffIDField.getText());

            PurchaseDAO dao = new PurchaseDAO();
            dao.addPurchase(date, quantity, supplier, costPerItem, totalCost, itemID, staffID);

            System.out.println("Purchase saved to database.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        purchaseDatePicker.setValue(LocalDate.now());
        
        quantityField.textProperty().addListener((obs, oldVal, newVal) -> updateTotalCost());
        costPerItemField.textProperty().addListener((obs, oldVal, newVal) -> updateTotalCost());

        saveButton.setOnAction(this::handleSave);
        cancelButton.setOnAction(e -> handleCancel());
    }

    private void updateTotalCost() {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            int costPerItem = Integer.parseInt(costPerItemField.getText());
            int totalCost = quantity * costPerItem;
            totalCostField.setText(String.valueOf(totalCost));
        } catch (NumberFormatException e) {
            totalCostField.setText("");
        }
    }

    private Purchase getPurchase(LocalDateTime purchaseDate) {
        int quantity = Integer.parseInt(quantityField.getText());
        String supplier = supplierField.getText();
        int costPerItem = Integer.parseInt(costPerItemField.getText());
        int totalCost = Integer.parseInt(totalCostField.getText());
        int itemID = Integer.parseInt(itemIDField.getText());
        int staffID = Integer.parseInt(staffIDField.getText());

        Purchase purchase = new Purchase(
                purchaseDate, quantity, supplier,
                costPerItem, totalCost, itemID, staffID
        );
        return purchase;
    }

    @FXML
    private void handleCancel() {
        clearForm();
    }

    private void clearForm() {
        purchaseDatePicker.setValue(LocalDate.now());
        quantityField.clear();
        supplierField.clear();
        costPerItemField.clear();
        totalCostField.clear();
        itemIDField.clear();
        staffIDField.clear();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please check all fields are filled correctly.");
        alert.showAndWait();
    }

}
