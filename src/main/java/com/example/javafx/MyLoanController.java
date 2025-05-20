package com.example.javafx;

import database.LoanDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.LoanWithItemTitle;
import com.example.javafx.Session;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyLoanController implements Initializable {

    @FXML
    private TableView<LoanWithItemTitle> loanTable;

    @FXML
    private TableColumn<LoanWithItemTitle, Integer> loanIDColumn;

    @FXML
    private TableColumn<LoanWithItemTitle, String> loanDateColumn;

    @FXML
    private TableColumn<LoanWithItemTitle, String> dueDateColumn;

    @FXML
    private TableColumn<LoanWithItemTitle, String> statusColumn;

    @FXML
    private TableColumn<LoanWithItemTitle, String> titleColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int userId = Session.getLoggedInUserId();
        LoanDAO loanDAO = new LoanDAO();
        List<LoanWithItemTitle> loans = loanDAO.getLoansByUserId(userId);
        System.out.println("User ID från session: " + userId);

        ObservableList<LoanWithItemTitle> observableLoans = FXCollections.observableArrayList(loans);

        loanIDColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLoanID()));
        loanDateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLoanDate()));
        dueDateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDueDate()));
        statusColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStatus()));
        titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));

        loanTable.setItems(observableLoans);

        System.out.println("Antal lån: " + loans.size());
        for (LoanWithItemTitle loan : loans) {
            System.out.println(loan.getLoanID() + " - " + loan.getTitle());
        }
    }

    @FXML
    public void switchToUserDashboard(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
