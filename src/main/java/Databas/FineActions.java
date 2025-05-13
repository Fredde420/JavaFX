package Databas;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

    public class FineActions {

        public List<Fines> getAllFines() {
            List<Fines> fines = new ArrayList<>();
            String query = "SELECT * FROM fine";

            try (Connection conn = Database.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int fineId = rs.getInt("fine_id");
                    Integer loanId = rs.getObject("loan_id") != null ? rs.getInt("loan_id") : null;
                    BigDecimal amount = rs.getBigDecimal("amount");
                    Boolean paid = rs.getObject("paid") != null ? rs.getBoolean("paid") : null;

                    Fines fine = new Fines(fineId, loanId, amount, paid);
                    fines.add(fine);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return fines;
        }

        public void addFine(Integer loanId, BigDecimal amount, Boolean paid) {
            String query = "INSERT INTO fine (loan_id, amount, paid) VALUES (?, ?, ?)";

            try (Connection conn = Database.connect();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                if (loanId != null) stmt.setInt(1, loanId);
                else stmt.setNull(1, Types.INTEGER);

                if (amount != null) stmt.setBigDecimal(2, amount);
                else stmt.setNull(2, Types.DECIMAL);

                if (paid != null) stmt.setBoolean(3, paid);
                else stmt.setNull(3, Types.BOOLEAN);

                stmt.executeUpdate();
                System.out.println("Fine added.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void updateFinePaidStatus(int fineId, boolean paid) {
            String query = "UPDATE fine SET paid = ? WHERE fine_id = ?";

            try (Connection conn = Database.connect();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setBoolean(1, paid);
                stmt.setInt(2, fineId);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Fine with ID " + fineId + " was updated to paid = " + paid + ".");
                } else {
                    System.out.println("No fine found with ID " + fineId + ".");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }



