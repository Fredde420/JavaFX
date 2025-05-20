package com.example.javafx;

import database.ReviewDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import model.Review;

import java.time.LocalDate;

public class ReviewController {

    @FXML private TableView<Review> reviewTable;
    @FXML private TableColumn<Review, String> columnReviewer;
    @FXML private TableColumn<Review, Integer> columnRating;
    @FXML private TableColumn<Review, String> columnText;
    @FXML private TableColumn<Review, LocalDate> columnDate;
    @FXML private TableColumn<Review, Integer> colUserId;
    @FXML private TextField userIdField;
    @FXML private TextField nameField;
    @FXML private ComboBox<Integer> ratingCombo;
    @FXML private TextArea reviewArea;
    @FXML private Button submitButton;

    private final ReviewDAO dao = new ReviewDAO();

    @FXML
    public void initialize() {
        colUserId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getUserId()).asObject());
        columnRating.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getRating()).asObject());
        columnText.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getComment()));
        columnDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getReviewDate()));

        ratingCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));

        handleRefresh();

        submitButton.setOnAction(e -> handleAdd());
    }



    @FXML
    private void handleAdd() {
        try {
            String name = nameField.getText();
            int rating = ratingCombo.getValue();
            int userId = Integer.parseInt(userIdField.getText());
            String comment = reviewArea.getText();
            LocalDate date = LocalDate.now(); // or use a DatePicker if you add one

            // Example: hardcoding itemId and memberId for testing
            int itemId = 1;

            Review review = new Review(0, itemId, rating, userId, comment, date);
            dao.insertReview(review);
            handleRefresh();
            handleClear();
        } catch (Exception e) {
            showAlert("Invalid input: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        reviewTable.setItems(FXCollections.observableArrayList(dao.getAllReviews()));
    }

    @FXML
    private void handleClear() {
        nameField.clear();
        ratingCombo.getSelectionModel().clearSelection();
        reviewArea.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.showAndWait();
    }
}
