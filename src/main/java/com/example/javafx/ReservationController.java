package com.example.javafx;

import database.ReservationDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import model.Reservation;

import java.time.LocalDate;

public class ReservationController {

    @FXML private TableView<Reservation> reservationTable;
    @FXML private TableColumn<Reservation, Integer> colReservationId, colItemId, colMemberId;
    @FXML private TableColumn<Reservation, LocalDate> colReservationDate;

    @FXML private TextField itemIdField, memberIdField;
    @FXML private DatePicker reservationDatePicker;

    private final ReservationDAO dao = new ReservationDAO();

    @FXML
    public void initialize() {
        colReservationId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getReservationId()).asObject());
        colItemId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getItemId()).asObject());
        colMemberId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
        colReservationDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getReservationDate()));

        handleRefresh();
    }

    @FXML
    private void handleAdd() {
        try {
            int itemId = Integer.parseInt(itemIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
            LocalDate date = reservationDatePicker.getValue();

            Reservation reservation = new Reservation(0, itemId, memberId, date);
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
        itemIdField.clear();
        memberIdField.clear();
        reservationDatePicker.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
