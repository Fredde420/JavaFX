package Databas;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderActions {

    public void createReminder(int reminderID, LocalDateTime reminderDate, int userID, int loanID) {
        String query = "INSERT INTO reminder (reminderID, reminderDate, userID, loanID) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setTimestamp(3, Timestamp.valueOf(reminderDate));
            stmt.setInt(1, loanID);


            stmt.executeUpdate();
            System.out.println("Reminder created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reminder> getAllReminders() {
        List<Reminder> reminders = new ArrayList<>();
        String query = "SELECT * FROM reminder";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("reminderID");
                Timestamp timestamp = rs.getTimestamp("reminderDate");
                int userId = rs.getInt("userID");
                int loanID = rs.getInt("loanID");


                Reminder reminder = new Reminder(id, timestamp.toLocalDateTime(), userId, loanID);
                reminders.add(reminder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reminders;
    }

    public void deleteReminder(int reminderID) {
        String query = "DELETE FROM reminder WHERE reminderID = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, reminderID);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Reminder with ID " + reminderID + " was deleted.");
            } else {
                System.out.println("No reminder found with ID " + reminderID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
