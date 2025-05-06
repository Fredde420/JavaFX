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
                        rs.getInt("itemId"),
                        rs.getString("title"),
                        rs.getString("type"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("classification"),
                        rs.getString("genre"),
                        rs.getInt("ageLimit"),
                        rs.getString("country")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}
