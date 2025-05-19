/*
package BosseTest.DAO;

import model.StaffLogin;
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
        stmt.setTimestamp(4, login.getLastLogin());
        stmt.setBoolean(5, login.getIsActive());
        stmt.setTimestamp(6, login.getUpdatedAt());
        stmt.executeUpdate();
    }

    public StaffLogin getLoginByStaffID(int staffID) throws SQLException {
        String sql = "SELECT * FROM staff_login WHERE staffID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, staffID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new StaffLogin(rs.getInt("staffID"), rs.getString("username"), rs.getString("password"),
                                  rs.getTimestamp("last_login"), rs.getBoolean("is_active"), rs.getTimestamp("updated_at"));
        }
        return null;
    }
}
 */