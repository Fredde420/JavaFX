package com.example.javafx;

import database.ItemDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.util.List;

public class HelloController {

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, String> titleColumn;

    @FXML
    private TableColumn<Item, String> authorColumn;

    @FXML
    private TableColumn<Item, String> typeColumn;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        ItemDAO itemDAO = new ItemDAO();
        List<Item> items = itemDAO.getAllItems();

        itemTable.getItems().addAll(items);
    }
}
