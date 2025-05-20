package com.example.javafx;

import database.ItemCopyDAO;
import database.LoanDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import model.Item;
import model.ItemCopy;
import model.Loan;

import java.io.IOException;
import java.time.LocalDate;

public class BookDetailsController {

    @FXML private Label titleLabel;
    @FXML private Label infoLabel;

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
    }



    @FXML
    public void handleLoan(ActionEvent event) {
        int memberId = Session.getLoggedInUserId(); // ← korrekt inloggad användare

        ItemCopyDAO copyDAO = new ItemCopyDAO();
        System.out.println("Testar kopior för itemId: " + selectedItem.getItemId());
        copyDAO.printAllCopiesByItemId(selectedItem.getItemId());

        if (memberId == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ingen användare är inloggad.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        // Hämta ledig kopia
        //ItemCopyDAO copyDAO = new ItemCopyDAO();
        //copyDAO.printAllCopiesByItemId(selectedItem.getItemId());
        ItemCopy availableCopy = copyDAO.getAvailableCopyByItemId(selectedItem.getItemId());


        if (availableCopy == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ej tillgänglig");
            alert.setHeaderText(null);
            alert.setContentText("Inga tillgängliga exemplar finns just nu.");
            alert.showAndWait();
            return;
        }

        // Skapa låneobjekt
        LocalDate today = LocalDate.now();
        LocalDate due = today.plusDays(14);
        Loan loan = new Loan(0, availableCopy.getCopyId(), memberId, today, due, null, 0); // status 0 = aktivt lån


        // Lägg till lån och markera kopia som upptagen
        LoanDAO loanDAO = new LoanDAO();
        loanDAO.insertLoan(loan);
        copyDAO.markCopyAsUnavailable(availableCopy.getCopyId());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lån lyckades");
        alert.setHeaderText(null);
        alert.setContentText("Du har lånat: " + selectedItem.getTitle() + "\nFörfallodatum: " + due);
        alert.showAndWait();
    }



    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/javafx/ItemSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
