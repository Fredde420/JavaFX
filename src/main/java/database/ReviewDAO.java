package database;

import model.Review;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public void addReview(Review review) {
        String sql = "INSERT INTO review (userID, rating, comment, reviewDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getUserId());
            stmt.setInt(2, review.getRating());
            stmt.setString(3, review.getText());
            stmt.setTimestamp(4, Timestamp.valueOf(review.getDate().atStartOfDay()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT userID, rating, comment, reviewDate FROM review";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("userID");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                LocalDate date = rs.getTimestamp("reviewDate").toLocalDateTime().toLocalDate();

                reviews.add(new Review(userId, rating, comment, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }
}
