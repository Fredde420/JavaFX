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


}
