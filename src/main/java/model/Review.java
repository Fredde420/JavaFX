package model;

import java.time.LocalDate;

public class Review {
    private int reviewId;
    private int userId;
    private int itemId;
    private int rating;
    private String comment;
    private LocalDate reviewDate;

    public Review(int i, int itemId, int rating, String comment, LocalDate date) {}

    public Review(int reviewId, int userId, int itemId, int rating, String comment, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.itemId = itemId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDate getReviewDate() { return reviewDate; }
    public void setReviewDate(LocalDate reviewDate) { this.reviewDate = reviewDate; }
}
