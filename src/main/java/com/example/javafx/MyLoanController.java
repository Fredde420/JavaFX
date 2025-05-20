package com.example.javafx;

import javafx.fxml.FXML;
import database.LoanDAO;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Loan;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class MyLoanController {
    public TableView loanTable;
    @FXML
    private TableView<Loan> loanTableView;
    @FXML
    private TableColumn<Loan, String> titleColumn;
    @FXML
    private TableColumn<Loan, LocalDate> loanDateColumn;
    @FXML
    private TableColumn<Loan, LocalDate> dueDateColumn;
    private int loggedInUserId;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("itemTitle"));
        loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        loadUserLoans();
    }

    private void loadUserLoans() {
        LoanDAO loanDAO = new LoanDAO();
        List<Loan> userLoans = loanDAO.getActiveLoansByUser(loggedInUserId);
        loanTableView.getItems().setAll(userLoans);
    }

    // Exempel på setter om du vill skicka in user ID från login
    public void setLoggedInUserId(int userId) {
        this.loggedInUserId = userId;
        loadUserLoans();
    }
}
