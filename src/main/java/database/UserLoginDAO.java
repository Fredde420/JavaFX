package database;

import model.UserLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserLoginDAO {
    private Connection conn;

    public UserLoginDAO(Connection conn) {
        this.conn = conn;
    }

    public void addLogin(UserLogin login) throws SQLException {
        String sql = "INSERT INTO user_login (userID, username, password, last_login, is_active, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, login.getUserID());
            stmt.setString(2, login.getUsername());
            stmt.setString(3, login.getPassword());
            stmt.setTimestamp(4, login.getLastLogin() != null ? new Timestamp(login.getLastLogin().getTime()) : null);
            stmt.setBoolean(5, login.isActive());
            stmt.setTimestamp(6, login.getCreatedAt() != null ? new Timestamp(login.getCreatedAt().getTime()) : null);
            stmt.setTimestamp(7, login.getUpdatedAt() != null ? new Timestamp(login.getUpdatedAt().getTime()) : null);
            stmt.executeUpdate();
        }
    }

    public UserLogin getLoginByUserID(int userID) throws SQLException {
        String sql = "SELECT * FROM user_login WHERE userID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UserLogin login = new UserLogin();
                login.setUserID(rs.getInt("userID"));
                login.setUsername(rs.getString("username"));
                login.setPassword(rs.getString("password"));
                login.setLastLogin(rs.getTimestamp("last_login"));
                login.setActive(rs.getBoolean("is_active"));
                login.setCreatedAt(rs.getTimestamp("created_at"));
                login.setUpdatedAt(rs.getTimestamp("updated_at"));
                return login;
            }
        }

        return null;
    }
}