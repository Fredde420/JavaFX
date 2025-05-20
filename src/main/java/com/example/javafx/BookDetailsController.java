package com.example.javafx;

import database.ItemCopyDAO;
import database.LoanDAO;
import database.ReservationDAO;
import database.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Item;
import model.ItemCopy;
import model.Loan;
import model.Reservation;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookDetailsController {

    @FXML private Label titleLabel;
    @FXML private Label infoLabel;
    @FXML private Button loanButton;

    private Item selectedItem;

    public void setItem(Item item) {
        this.selectedItem = item;

        titleLabel.setText(item.getTitle());
        infoLabel.setText(
                "Kategori: " + item.getCategory() + "\n" +
                        "Författare/Artist: " + item.getAuthorOrArtist() + "\n" +
                        "Streckkod: " + item.getBarcode() + "\n" +
                        "Plats: " + item.getPhysicalLocation() + "\n" +
                        "Klassificering: " + item.getClassification()
        );

        //loanButton.setDisable(!isItemAvailable(item.getItemId()));
    }

    private boolean isItemAvailable(int itemId) {
        String query = "SELECT available FROM itemcopy WHERE itemId = ? AND available = 1 LIMIT 1";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void handleLoan(ActionEvent event) {
        int memberId = Session.getLoggedInUserId();
        if (memberId == -1) {
            showAlert("Fel", "Ingen användare är inloggad.");
            return;
        }

        ItemCopyDAO copyDAO = new ItemCopyDAO();
        ItemCopy availableCopy = copyDAO.getAvailableCopyByItemId(selectedItem.getItemId());

        if (availableCopy == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ej tillgänglig");
            alert.setHeaderText("Inga exemplar finns just nu");
            alert.setContentText("Vill du reservera boken istället?");
            ButtonType reservera = new ButtonType("Reservera");
            ButtonType avbryt = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(reservera, avbryt);

            alert.showAndWait().ifPresent(response -> {
                if (response == reservera) {
                    handleReserve();
                }
            });
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate due = today.plusDays(14);
        Loan loan = new Loan(0, availableCopy.getCopyId(), memberId, today, due, null, 0);

        LoanDAO loanDAO = new LoanDAO();
        loanDAO.insertLoan(loan);
        copyDAO.markCopyAsUnavailable(availableCopy.getCopyId());

        showAlert("Lån lyckades", "Du har lånat: " + selectedItem.getTitle() + "\nFörfallodatum: " + due);
    }

    private void handleReserve() {
        int memberId = Session.getLoggedInUserId();
        if (memberId == -1) {
            showAlert("Fel", "Ingen användare är inloggad.");
            return;
        }

        ItemCopyDAO copyDAO = new ItemCopyDAO();
        ItemCopy copy = copyDAO.getFirstCopyByItemId(selectedItem.getItemId());
        if (copy == null) {
            showAlert("Fel", "Ingen kopia finns att reservera.");
            return;
        }

        Reservation reservation = new Reservation();
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setStatus(1);
        reservation.setUserId(memberId);
        reservation.setCopyId(copy.getCopyId());

        ReservationDAO dao = new ReservationDAO();
        dao.insertReservation(reservation);

        showAlert("Reserverad", "Din reservation har registrerats.");
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/javafx/ItemSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}


