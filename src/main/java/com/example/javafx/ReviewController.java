package com.example.javafx;

import database.ReviewDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import model.Review;

import java.time.LocalDate;

public class ReviewController {

    @FXML private TableView<Review> reviewTable;
    @FXML private TableColumn<Review, Integer> colReviewId, colItemId, colMemberId, colRating;
    @FXML private TableColumn<Review, String> colComment;
    @FXML private TableColumn<Review, LocalDate> colReviewDate;

    @FXML private TextField itemIdField, memberIdField, ratingField, commentField;
    @FXML private DatePicker reviewDatePicker;

    private final ReviewDAO dao = new ReviewDAO();

    @FXML
    public void initialize() {
        colReviewId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getReviewId()).asObject());
        colItemId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getItemId()).asObject());
        colMemberId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
        colRating.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getRating()).asObject());
        colComment.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getComment()));
        colReviewDate.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getReviewDate()));

        handleRefresh();
    }

    @FXML
    private void handleAdd() {
        try {
            int itemId = Integer.parseInt(itemIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
            int rating = Integer.parseInt(ratingField.getText());
            String comment = commentField.getText();
            LocalDate date = reviewDatePicker.getValue();

            Review review = new Review(0, itemId, memberId, rating, comment, date);
            dao.insertReview(review);
            handleRefresh();
            handleClear();
        } catch (Exception e) {
            showAlert("Error", "Invalid input: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        reviewTable.setItems(FXCollections.observableArrayList(dao.getAllReviews()));
    }

    @FXML
    private void handleClear() {
        itemIdField.clear();
        memberIdField.clear();
        ratingField.clear();
        commentField.clear();
        reviewDatePicker.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
