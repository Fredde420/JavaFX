package com.example.javafx;

import database.ItemDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import model.Item;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



public class UserSearchFunction {





    @FXML private TextField searchField;
    @FXML private TableView<Item> itemTable;
    @FXML private TableColumn<Item, String> titleCol;
    @FXML private TableColumn<Item, String> authorCol;
    @FXML private TableColumn<Item, String> isbnCol;

    private final ItemDAO itemDAO = new ItemDAO();

    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        authorCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthorOrArtist()));
        isbnCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBarcode()));

        itemTable.setOnMouseClicked(this::handleRowDoubleClick);
    }






    private void handleRowDoubleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            Item selected = itemTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafx/BookDetails.fxml"));
                    Parent root = loader.load();

                    BookDetailsController controller = loader.getController();
                    controller.setItem(selected);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void onSearchClicked() {
        String query = searchField.getText().toLowerCase().trim();
        List<Item> allItems = itemDAO.getAllItems();

        List<Item> filtered = allItems.stream()
                .filter(i -> i.getTitle().toLowerCase().contains(query) ||
                        i.getAuthorOrArtist().toLowerCase().contains(query) ||
                        i.getBarcode().toLowerCase().contains(query))
                .collect(Collectors.toList());

        itemTable.setItems(FXCollections.observableArrayList(filtered));
    }



   /* private void showDetailsDialog(Item item) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Objektdetaljer");
        alert.setHeaderText(item.getTitle());
        alert.setContentText(
                "Kategori: " + item.getCategory() + "\n" +
                        "Författare/Artist: " + item.getAuthorOrArtist() + "\n" +
                        "Streckkod: " + item.getBarcode() + "\n" +
                        "Plats i bibliotek: " + item.getPhysicalLocation() + "\n" +
                        "Klassificering: " + item.getClassification()
        );
        alert.showAndWait();
    }*/

    @FXML
    public void switchToStartSida(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));  // Byt namn om filen heter något annat
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToUserDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/javafx/UserDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }




}


