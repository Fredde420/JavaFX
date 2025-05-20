package com.example.javafx;

import database.LoanDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.StaffLoanView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

    public class StaffLoanController implements Initializable {

        @FXML
        private TableView<StaffLoanView> staffLoanTable;

        @FXML
        private TableColumn<StaffLoanView, Integer> loanIDColumn;

        @FXML
        private TableColumn<StaffLoanView, String> titleColumn;

        @FXML
        private TableColumn<StaffLoanView, String> loanDateColumn;

        @FXML
        private TableColumn<StaffLoanView, String> dueDateColumn;

        @FXML
        private TableColumn<StaffLoanView, String> userColumn;

        @FXML
        private TableColumn<StaffLoanView, Long> daysRemainingColumn;

        @FXML
        private void switchToStaffDashboard(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("StaffDashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Session.clearSession();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            LoanDAO dao = new LoanDAO();
            List<StaffLoanView> loans = dao.getUnreturnedLoansForStaff();

            ObservableList<StaffLoanView> observableLoans = FXCollections.observableArrayList(loans);

            loanIDColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLoanID()));
            titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
            loanDateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLoanDate()));
            dueDateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDueDate()));
            userColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUsername()));
            daysRemainingColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDaysRemaining()));

            staffLoanTable.setItems(observableLoans);


            // Debug: Skriver ut alla lån i konsolen
            System.out.println("🔎 Kontroll av ej returnerade lån:");
            System.out.println("Antal lån: " + loans.size());
            for (StaffLoanView loan : loans) {
                System.out.println(
                        loan.getLoanID() + " | " +
                                loan.getTitle() + " | " +
                                loan.getLoanDate() + " | " +
                                loan.getDueDate() + " | " +
                                loan.getUsername() + " | " +
                                loan.getDaysRemaining()
                );
            }
        }


    }
