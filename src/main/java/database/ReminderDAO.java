package database;


import model.Reminder;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderDAO {
//test
    public void createReminder(int reminderID, LocalDateTime reminderDate, int userID, int loanID) {
        String query = "INSERT INTO reminder (reminderID, reminderDate, userID, loanID) VALUES (?, ?, ?, ?)";

        try (Connection conn = database.connect();
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

        try (Connection conn = database.connect();
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

        try (Connection conn = database.connect();
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

    public void createRemindersForAllOverdueLoans() {
        String overdueQuery = "SELECT loanID, memberId FROM loan WHERE dueDate < CURDATE() AND returnDate IS NULL";
        String checkReminderQuery = "SELECT 1 FROM reminder WHERE loanID = ?";
        String insertQuery = "INSERT INTO reminder (reminderDate, userID, loanID) VALUES (?, ?, ?)";

        int count = 0;

        try (Connection conn = database.connect();
             PreparedStatement overdueStmt = conn.prepareStatement(overdueQuery);
             ResultSet rs = overdueStmt.executeQuery()) {

            while (rs.next()) {
                int loanID = rs.getInt("loanID");
                int userID = rs.getInt("memberId");

                // Kontrollera om påminnelse redan finns
                try (PreparedStatement checkStmt = conn.prepareStatement(checkReminderQuery)) {
                    checkStmt.setInt(1, loanID);
                    ResultSet checkResult = checkStmt.executeQuery();

                    if (!checkResult.next()) {
                        // Skapa ny påminnelse
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                            insertStmt.setInt(2, userID);
                            insertStmt.setInt(3, loanID);
                            insertStmt.executeUpdate();
                            count++;
                            System.out.println("✔ Skapade påminnelse för loanID: " + loanID);
                        }
                    } else {
                        System.out.println("⚠ Påminnelse finns redan för loanID: " + loanID);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Totalt nya påminnelser skapade: " + count);
    }


}
