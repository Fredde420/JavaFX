package database;

import model.User;

import java.sql.*;

public class UserDAO {

    public int create(User user) throws SQLException {
        String sql = "INSERT INTO users (fName, lName, email, phoneNr, categoryID, adressNr) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getFName());
            stmt.setString(2, user.getLName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNr());
            stmt.setInt(5, user.getCategoryID());
            stmt.setInt(6, user.getAdressNr());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Skapande av användare misslyckades, inga rader påverkades.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Returnerar genererade userID
                } else {
                    throw new SQLException("Skapande av användare misslyckades, inget ID hämtat.");
                }
            }
        }
    }
}