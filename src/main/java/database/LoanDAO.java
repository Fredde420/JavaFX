package database;

import model.Loan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    public void insertLoan(Loan loan) {
        String sql = "INSERT INTO loan (copyID, userID, loanDate, dueDate, returnedDate, status) VALUES (?, ?, ?, ?, ?, ?)";;

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("Försöker lägga till lån...");
            System.out.println("Försöker markera som otillgänglig...");

            stmt.setInt(1, loan.getItemCopyId());
            stmt.setInt(2, loan.getMemberId());
            stmt.setDate(3, Date.valueOf(loan.getLoanDate()));
            stmt.setDate(4, Date.valueOf(loan.getDueDate()));

            stmt.setInt(1, loan.getItemCopyId()); // copyID
            stmt.setInt(2, loan.getMemberId());   // userID
            stmt.setTimestamp(3, Timestamp.valueOf(loan.getLoanDate().atStartOfDay())); // loanDate
            stmt.setTimestamp(4, Timestamp.valueOf(loan.getDueDate().atStartOfDay()));  // dueDate

            if (loan.getReturnDate() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(loan.getReturnDate().atStartOfDay()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            // Till exempel sätt "0" som status (t.ex. 0 = pågående lån, 1 = returnerad)
            stmt.setInt(6, 0);


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
