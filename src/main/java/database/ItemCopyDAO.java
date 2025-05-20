package database;

import model.ItemCopy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCopyDAO {
    public List<ItemCopy> getAllItemCopies() {
        List<ItemCopy> copies = new ArrayList<>();
        String query = "SELECT * FROM item_copy";

        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ItemCopy copy = new ItemCopy(
                        rs.getInt("itemCopyId"),
                        rs.getInt("itemId"),
                        rs.getString("barcode"),
                        rs.getString("location"),
                        rs.getBoolean("available")
                );
                copies.add(copy);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return copies;
    }

    public ItemCopy getAvailableCopyByItemId(int itemId) {
        String query = "SELECT * FROM item_copy WHERE itemId = ? AND available = true LIMIT 1";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ItemCopy(
                        rs.getInt("itemCopyId"),
                        rs.getInt("itemId"),
                        rs.getString("barcode"),
                        rs.getString("location"),
                        rs.getBoolean("available")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // ingen ledig kopia
    }

    public void markCopyAsUnavailable(int itemCopyId) {
        String query = "UPDATE item_copy SET available = false WHERE itemCopyId = ?";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemCopyId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
