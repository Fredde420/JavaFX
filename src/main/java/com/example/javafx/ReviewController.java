package com.example.javafx;

import database.ReviewDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Review;

import java.time.LocalDate;

public class ReviewController {

    @FXML private TableView<Review> reviewTable;
    @FXML private TableColumn<Review, Integer> columnRating;
    @FXML private TableColumn<Review, String> columnText;
    @FXML private TableColumn<Review, LocalDate> columnDate;

    @FXML private ComboBox<Integer> ratingCombo;
    @FXML private TextArea reviewArea;
    @FXML private Button submitButton;

    private ReviewDAO reviewDAO = new ReviewDAO();
    private ObservableList<Review> reviewList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        columnRating.setCellValueFactory(data -> data.getValue().ratingProperty().asObject());
        columnText.setCellValueFactory(data -> data.getValue().textProperty());
        columnDate.setCellValueFactory(data -> data.getValue().dateProperty());

        ratingCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));

        loadReviews();

        submitButton.setOnAction(e -> handleAddReview());
    }

    private void loadReviews() {
        reviewList.setAll(reviewDAO.getAllReviews());
        reviewTable.setItems(reviewList);
    }

    private void handleAddReview() {
        if (!Session.isLoggedIn()) {
            showAlert("Login Required", "Please log in before submitting a review.");
            return;
        }

        Integer rating = ratingCombo.getValue();
        String text = reviewArea.getText();
        int userId = Session.getLoggedInUserId();
        LocalDate date = LocalDate.now();

        if (rating == null || text.isEmpty()) {
            showAlert("Missing Fields", "Please fill in all fields.");
            return;
        }

        Review review = new Review(userId, rating, text, date);
        reviewDAO.addReview(review);
        loadReviews();

        ratingCombo.setValue(null);
        reviewArea.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
