package com.example.javafx;

import database.ReservationDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import model.Reservation;

import java.time.LocalDateTime;

public class ReservationController {

    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, Integer> colReservationId, colCopyId, colUserId, colStatus;
    @FXML private TableColumn<Reservation, LocalDateTime> colReservationDate;

    @FXML private TextField copyIdField, userIdField, statusField;
    private final ReservationDAO dao = new ReservationDAO();

    @FXML
    public void initialize() {
        colReservationId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getReservationId()).asObject());
        colCopyId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCopyId()).asObject());
        colUserId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getUserId()).asObject());
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStatus()).asObject());
        colReservationDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getReservationDate()));

        handleRefresh();
    }

    @FXML
    private void handleAdd() {
        try {
            int copyId = Integer.parseInt(copyIdField.getText());
            int userId = Integer.parseInt(userIdField.getText());
            int status = Integer.parseInt(statusField.getText());
            LocalDateTime now = LocalDateTime.now();

            Reservation reservation = new Reservation(0, userId, copyId, status, now);
            dao.insertReservation(reservation);
            handleRefresh();
            handleClear();
        } catch (Exception e) {
            showAlert("Error", "Invalid input: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        reservationTable.setItems(FXCollections.observableArrayList(dao.getAllReservations()));
    }

    @FXML
    private void handleClear() {
        copyIdField.clear();
        userIdField.clear();
        statusField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}

