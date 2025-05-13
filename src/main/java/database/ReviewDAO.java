package database;

import model.Review;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public void insertReview(Review review) {
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM review";

        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Review review = new Review(
                        rs.getInt("reviewId"),
                        rs.getInt("itemId"),
                        rs.getInt("memberId"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getDate("reviewDate").toLocalDate()
                );
                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }


}
