package BosseTest.DAO;

import BosseTest.Annat.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public void create(User user) throws SQLException {
        String sql = "INSERT INTO users (fName, lName, email, phoneNr, categoryID, adressNr) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFName());
            stmt.setString(2, user.getLName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNr());
            stmt.setInt(5, user.getCategoryID());
            stmt.setInt(6, user.getAdressNr());

            stmt.executeUpdate();
        }
    }
}