package com.main.javafxproject.Controllers;

import com.main.javafxproject.Main;
import com.main.javafxproject.Model.Inventory;
import com.main.javafxproject.Model.Part;
import com.main.javafxproject.Model.Product;
import com.main.javafxproject.Toolkit.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static com.main.javafxproject.Toolkit.Utility.*;
import static com.main.javafxproject.Toolkit.Utility.partsSearch;

public class AddProductController implements Initializable {

    @FXML
    private TextField addProductName;
    @FXML
    private TextField addProductInv;
    @FXML
    private TextField addProductPriceCost;
    @FXML
    private TextField addProductMax;
    @FXML
    private TextField addProductMin;
    @FXML
    private TableView<Part> productPartsTable;
    @FXML
    TableColumn<Part, Integer> productPartsTablePartId;
    @FXML
    TableColumn<Part, String> productPartsTablePartName;
    @FXML
    TableColumn<Part, Integer> productPartsTableInventoryLevel;
    @FXML
    TableColumn<Part, Double> productPartsTablePriceCost;
    @FXML
    TableView<Part> associatedPartsTable;
    @FXML
    TableColumn<Part, Integer> associatedPartsTablePartId;
    @FXML
    TableColumn<Part, String> associatedPartsTablePartName;
    @FXML
    TableColumn<Part, Integer> associatedPartsTableInventoryLevel;
    @FXML
    TableColumn<Part, Double> associatedPartsTablePriceCost;
    @FXML
    TextField addProductSearchText;

    ObservableList<Part> tempPartsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productPartsTable.setItems(Inventory.getAllParts());
        productPartsTablePartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartsTablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartsTableInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartsTablePriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsTablePartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsTablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsTableInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsTablePriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Product add button.
     *
     * @param event the event
     */
    @FXML
    void productAddButton(ActionEvent event) {
        Part part = productPartsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            errorAlert("", "You must select a part to associate");
        } else {
            tempPartsList.add(part);
            associatedPartsTable.setItems(tempPartsList);
        }
    }

    /**
     * Add product save button.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void addProductSaveButton(ActionEvent event) throws IOException {
        Random rand = new Random();

        try {
            int id = rand.nextInt(1000000);
            String name = addProductName.getText();
            double price = Double.parseDouble(addProductPriceCost.getText());
            int stock = Integer.parseInt(addProductInv.getText());
            int max = Integer.parseInt(addProductMax.getText());
            int min = Integer.parseInt(addProductMin.getText());

            if (min >= max) {
                errorAlert("Value Error", "Your min value must be inferior to your max value");
                return;
            }
            if (stock < min || stock > max) {
                errorAlert("Value Error", " Your inventory quantity must be between min and max values");
                return;
            }

            Product product = new Product(id, name, price, stock, max, min);

            for (Part part: associatedPartsTable.getItems()) {
                product.addAssociatedPart(part);
            }
            Inventory.addProduct(product);
        } catch (Exception e) {
            errorAlert("","Invalid Data");
            return;
        }


        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }

    /**
     * Remove associated part.
     *
     * @param event the event
     */
    @FXML
    void removeAssociatedPart(ActionEvent event) {
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            errorAlert("", "You must select a part to remove");
        } else {
            if (confirmationAlert("Confirm Removal", "Are you sure you want to remove this associated part?")) {
                tempPartsList.remove(part);
                associatedPartsTable.setItems(tempPartsList);
            }
        }
    }

    /**
     * Cancel add product button.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void cancelAddProductButton(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }

    /**
     * Add product search handler.
     *
     * @param event the event
     */
    @FXML
    void addProductSearchHandler(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.BACK_SPACE && addProductSearchText.getText().length() == 0) {
                productPartsTable.setItems(Inventory.getAllParts());
            } else {
                int searchID = Integer.parseInt(addProductSearchText.getText());
                productPartsTable.setItems(partsSearch(searchID));
            }
        } catch (NumberFormatException e) {
            String searchName = addProductSearchText.getText();
            productPartsTable.setItems(partsSearch(searchName));
        }
    }
}
