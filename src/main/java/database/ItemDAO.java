package database;

import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM item";

        try (Connection conn = database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("itemID"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getString("authorOrArtist"),
                        rs.getString("barcode"),
                        rs.getString("physicalLocation"),
                        rs.getString("classification")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<Item> searchItems(String keyword) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM item WHERE title LIKE ? OR authorOrArtist LIKE ? OR barcode LIKE ?";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String like = "%" + keyword + "%";
            stmt.setString(1, like);
            stmt.setString(2, like);
            stmt.setString(3, like);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("itemID"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getString("authorOrArtist"),
                        rs.getString("barcode"),
                        rs.getString("physicalLocation"),
                        rs.getString("classification")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}

