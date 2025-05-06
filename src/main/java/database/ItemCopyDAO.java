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
}
