package database;


import model.Purchase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

    public void addPurchase(LocalDateTime date, int quantity, String supplier, int costPerItem,
                            int totalCost, int itemID, int staffID) {

        String query = "INSERT INTO purchase (purchaseDate, quantity, supplier, costPerItem, totalCost, itemID, staffID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setTimestamp(1, Timestamp.valueOf(date));
            stmt.setInt(2, quantity);
            stmt.setString(3, supplier);
            stmt.setInt(4, costPerItem);
            stmt.setInt(5, totalCost);
            stmt.setInt(6, itemID);
            stmt.setInt(7, staffID);

            stmt.executeUpdate();
            System.out.println("Purchase added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchase";

        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Purchase p = new Purchase(
                        rs.getInt("purchaseID"),
                        rs.getTimestamp("purchaseDate").toLocalDateTime(),
                        rs.getInt("quantity"),
                        rs.getString("supplier"),
                        rs.getInt("costPerItem"),
                        rs.getInt("totalCost"),
                        rs.getInt("itemID"),
                        rs.getInt("staffID")
                );
                purchases.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    public List<Purchase> getPurchasesByStaff(int staffId) {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchase WHERE staffID = ?";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, staffId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Purchase p = new Purchase(
                        rs.getInt("purchaseID"),
                        rs.getTimestamp("purchaseDate").toLocalDateTime(),
                        rs.getInt("quantity"),
                        rs.getString("supplier"),
                        rs.getInt("costPerItem"),
                        rs.getInt("totalCost"),
                        rs.getInt("itemID"),
                        rs.getInt("staffID")
                );
                purchases.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }
}

