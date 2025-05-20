package database;

import model.ItemCopy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCopyDAO {

    public List<ItemCopy> getAllItemCopies() {
        List<ItemCopy> copies = new ArrayList<>();
        String query = "SELECT * FROM itemcopy";

        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ItemCopy copy = new ItemCopy(
                        rs.getInt("copyID"),
                        rs.getInt("itemID"),
                        rs.getString("barcode"),
                        rs.getString("physicalLocation"),
                        rs.getBoolean("isAvailable")
                );
                copies.add(copy);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return copies;
    }

    public ItemCopy getAvailableCopyByItemId(int itemId) {
        String query = "SELECT * FROM itemcopy WHERE itemID = ? AND isAvailable = true LIMIT 1";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ItemCopy(
                        rs.getInt("copyID"),
                        rs.getInt("itemID"),
                        rs.getString("barcode"),
                        rs.getString("physicalLocation"),
                        rs.getBoolean("isAvailable")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // ingen ledig kopia
    }

    public void printAllCopiesByItemId(int itemId) {
        String query = "SELECT * FROM itemcopy WHERE itemID = ?";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Kopia ID: " + rs.getInt("copyID") +
                        ", isAvailable: " + rs.getBoolean("isAvailable") +
                        " (Rått värde: " + rs.getString("isAvailable") + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markCopyAsUnavailable(int copyId) {
        String query = "UPDATE itemcopy SET isAvailable = false WHERE copyID = ?";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, copyId);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

