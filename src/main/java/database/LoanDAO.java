package database;

import model.Loan;
import model.LoanWithItemTitle;
import model.StaffLoanView;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    public void insertLoan(Loan loan) {
        String sql = "INSERT INTO loan (copyID, userID, loanDate, dueDate, returnedDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        ;

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

    public List<LoanWithItemTitle> getLoansByUserId(int userId) {
        List<LoanWithItemTitle> loans = new ArrayList<>();

        String query =
                "SELECT loan.loanID, loan.loanDate, loan.dueDate, loan.status, item.title " +
                        "FROM loan " +
                        "LEFT JOIN itemcopy ON loan.copyID = itemcopy.copyID " +
                        "LEFT JOIN item ON itemcopy.itemID = item.itemID " +
                        "WHERE loan.userID = ?";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int loanID = rs.getInt("loanID");
                String loanDate = rs.getString("loanDate");
                String dueDate = rs.getString("dueDate");
                int statusInt = rs.getInt("status");
                String title = rs.getString("title");

                String status;
                switch (statusInt) {
                    case 0:
                        status = "Pågående";
                        break;
                    case 1:
                        status = "Återlämnad";
                        break;
                    case 2:
                        status = "Försenad";
                        break;
                    default:
                        status = "Okänd";
                        break;
                }

                loans.add(new LoanWithItemTitle(loanID, loanDate, dueDate, status, title));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }

    public List<StaffLoanView> getUnreturnedLoansForStaff() {
        List<StaffLoanView> loans = new ArrayList<>();
        String query =
                "SELECT loan.loanID, item.title, loan.loanDate, loan.dueDate, user.username " +
                        "FROM loan " +
                        "LEFT JOIN itemcopy ON loan.copyID = itemcopy.copyID " +
                        "LEFT JOIN item ON itemcopy.itemID = item.itemID " +
                        "LEFT JOIN user ON loan.userID = user.userID " +
                        "WHERE loan.returnedDate IS NULL";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int loanID = rs.getInt("loanID");
                String title = rs.getString("title");
                String loanDate = rs.getString("loanDate");
                String dueDate = rs.getString("dueDate");
                String username = rs.getString("username");

                long daysRemaining = 0;
                try {
                    LocalDate due = LocalDate.parse(dueDate);
                    daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), due);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("Laddar lån: " + loanID + " | " + title + " | " + username);
                loans.add(new StaffLoanView(loanID, title, loanDate, dueDate, username, daysRemaining));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }
}
