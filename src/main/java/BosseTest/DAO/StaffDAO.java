package BosseTest.DAO;

import BosseTest.Annat.Staff;

public class StaffDAO {
    public void create(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (fName, email, phoneNr, isManager) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, staff.getFName());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPhoneNr());
            stmt.setBoolean(4, staff.isManager());
            stmt.executeUpdate();
        }
    }
}
