package com.example.javafx;

import database.LoanDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import model.Loan;

import java.time.LocalDate;

public class LoanController {

    @FXML private TableView<Loan> loanTable;
    @FXML private TableColumn<Loan, Integer> colLoanId, colItemCopyId, colMemberId;
    @FXML private TableColumn<Loan, LocalDate> colLoanDate, colDueDate, colReturnDate;

    @FXML private TextField itemCopyIdField, memberIdField;
    @FXML private DatePicker loanDatePicker, dueDatePicker, returnDatePicker;

    private final LoanDAO dao = new LoanDAO();

    @FXML
    public void initialize() {
        colLoanId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getLoanId()).asObject());
        colItemCopyId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getItemCopyId()).asObject());
        colMemberId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
        colLoanDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getLoanDate()));
        colDueDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDueDate()));
        colReturnDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getReturnDate()));

        handleRefresh();
    }

    @FXML
    private void handleAdd() {
        try {
            int itemCopyId = Integer.parseInt(itemCopyIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
            LocalDate loanDate = loanDatePicker.getValue();
            LocalDate dueDate = dueDatePicker.getValue();
            LocalDate returnDate = returnDatePicker.getValue();

            Loan loan = new Loan(0, itemCopyId, memberId, loanDate, dueDate, returnDate);
            dao.insertLoan(loan);
            handleRefresh();
            handleClear();
        } catch (Exception e) {
            showAlert("Error", "Invalid input: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        loanTable.setItems(FXCollections.observableArrayList(dao.getAllLoans()));
    }

    @FXML
    private void handleClear() {
        itemCopyIdField.clear();
        memberIdField.clear();
        loanDatePicker.setValue(null);
        dueDatePicker.setValue(null);
        returnDatePicker.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}