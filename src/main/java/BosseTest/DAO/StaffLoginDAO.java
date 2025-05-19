package BosseTest.DAO;

import BosseTest.Annat.StaffLogin;
import java.sql.*;

public class StaffLoginDAO {
    private Connection conn;

    public StaffLoginDAO(Connection conn) {
        this.conn = conn;
    }

    public void addLogin(StaffLogin login) throws SQLException {
        String sql = "INSERT INTO staff_login (staffID, username, password, last_login, is_active, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, login.getStaffID());
        stmt.setString(2, login.getUsername());
        stmt.setString(3, login.getPassword());
        stmt.setTimestamp(4, login.getLastLogin() != null ? new Timestamp(login.getLastLogin().getTime()) : null);
        stmt.setBoolean(5, login.isActive());
        stmt.setTimestamp(6, login.getUpdatedAt() != null ? new Timestamp(login.getUpdatedAt().getTime()) : null);
        stmt.executeUpdate();
    }

    public StaffLogin getLoginByStaffID(int staffID) throws SQLException {
        String sql = "SELECT * FROM staff_login WHERE staffID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, staffID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            StaffLogin login = new StaffLogin();
            login.setStaffID(rs.getInt("staffID"));
            login.setUsername(rs.getString("username"));
            login.setPassword(rs.getString("password"));
            login.setLastLogin(rs.getTimestamp("last_login"));
            login.setActive(rs.getBoolean("is_active"));
            login.setUpdatedAt(rs.getTimestamp("updated_at"));
            return login;
        }
        return null;
    }
}