package database;

import model.Loan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    public void insertLoan(Loan loan) {
        String sql = "INSERT INTO loan (itemCopyId, memberId, loanDate, dueDate, returnDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loan.getItemCopyId());
            stmt.setInt(2, loan.getMemberId());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            stmt.setDate(4, Date.valueOf(loan.getDueDate()));

            if (loan.getReturnDate() != null) {
                stmt.setDate(5, Date.valueOf(loan.getReturnDate()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.executeUpdate();
            System.out.println("Lån registrerat!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loan";

        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Loan loan = new Loan(
                        rs.getInt("loanId"),
                        rs.getInt("itemCopyId"),
                        rs.getInt("memberId"),
                        rs.getDate("loanDate").toLocalDate(),
                        rs.getDate("dueDate").toLocalDate(),
                        rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null
                );
                loans.add(loan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }

    public List<Loan> getActiveLoansByUser(int userId) {
        List<Loan> loans = new ArrayList<>();
        String query =
                "SELECT loan.*, item.title " +
                        "FROM loan " +
                        "JOIN itemcopy ON loan.itemCopyId = itemcopy.copyId " +
                        "JOIN item ON itemcopy.itemId = item.itemId " +
                        "WHERE loan.memberId = ? AND loan.returnDate IS NULL";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Loan loan = new Loan(
                        rs.getInt("loanId"),
                        rs.getInt("itemCopyId"),
                        rs.getInt("memberId"),
                        rs.getDate("loanDate").toLocalDate(),
                        rs.getDate("dueDate").toLocalDate(),
                        null // eftersom returnDate är NULL
                );
                loan.setItemTitle(rs.getString("title")); // Nytt fält i modellen
                loans.add(loan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }

}
