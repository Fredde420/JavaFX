package Databas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;



public class Database{

    private static final String URL = "jdbc:mysql://localhost:3306/librarydb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Kunde inte ansluta till databasen.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



public static List<Fines> getAllFines() {
    List<Fines> fines = new ArrayList<>();
    String query = "SELECT * FROM fine";

    try (Connection conn = connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            int fineId = rs.getInt("fineID");
            Integer loanId = rs.getObject("loanID") != null ? rs.getInt("loanID") : null;
            BigDecimal amount = rs.getBigDecimal("fineAmount");
            Boolean paid = rs.getObject("isPaid") != null ? rs.getBoolean("isPaid") : null;

            Fines fine = new Fines(fineId, loanId, amount, paid);
            fines.add(fine);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        disconnect();
    }

    return fines;
}



public static void main(String[] args) {
    List<Fines> fines = getAllFines();
    for (Fines fine : fines) {
        System.out.println(fine);
    }
}
}
