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

/**
 * The type Modify product controller.
 */
public class ModifyProductController implements Initializable {
    /**
     * The Modify product name.
     */
    @FXML
    TextField modifyProductName;
    /**
     * The Modify product id.
     */
    @FXML
    TextField modifyProductID;
    /**
     * The Modify product inv.
     */
    @FXML
    TextField modifyProductInv;
    /**
     * The Modify product price.
     */
    @FXML
    TextField modifyProductPrice;
    /**
     * The Modify product max.
     */
    @FXML
    TextField modifyProductMax;
    /**
     * The Modify product min.
     */
    @FXML
    TextField modifyProductMin;
    /**
     * The Modify products table.
     */
    @FXML
    TableView<Part> modifyProductsTable;
    /**
     * The Product parts table part id.
     */
    @FXML
    TableColumn<Part, Integer> productPartsTablePartId;
    /**
     * The Product parts table part name.
     */
    @FXML
    TableColumn<Part, String> productPartsTablePartName;
    /**
     * The Product parts table inventory level.
     */
    @FXML
    TableColumn<Part, Integer> productPartsTableInventoryLevel;
    /**
     * The Product parts table price cost.
     */
    @FXML
    TableColumn<Part, Double> productPartsTablePriceCost;
    /**
     * The Modify associated product table.
     */
    @FXML
    TableView<Part> modifyAssociatedProductTable;
    /**
     * The Associated parts table part id.
     */
    @FXML
    TableColumn<Part, Integer> associatedPartsTablePartId;
    /**
     * The Associated parts table part name.
     */
    @FXML
    TableColumn<Part, String> associatedPartsTablePartName;
    /**
     * The Associated parts table inventory level.
     */
    @FXML
    TableColumn<Part, Integer> associatedPartsTableInventoryLevel;
    /**
     * The Associated parts table price cost.
     */
    @FXML
    TableColumn<Part, Double> associatedPartsTablePriceCost;
    /**
     * The Modify product search text.
     */
    @FXML
    TextField modifyProductSearchText;

    /**
     * The Selected product.
     */
    Product selectedProduct = MainController.selectedProduct;
    /**
     * The Temp parts list.
     */
    ObservableList<Part> tempPartsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tempPartsList.addAll(selectedProduct.getAllAssociatedParts());

        modifyProductsTable.setItems(Inventory.getAllParts());
        productPartsTablePartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartsTablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartsTableInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartsTablePriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyAssociatedProductTable.setItems(tempPartsList);
        associatedPartsTablePartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsTablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsTableInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsTablePriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProductID.setText(Integer.toString(selectedProduct.getId()));
        modifyProductName.setText(selectedProduct.getName());
        modifyProductInv.setText(Integer.toString(selectedProduct.getStock()));
        modifyProductPrice.setText(Double.toString(selectedProduct.getPrice()));
        modifyProductMax.setText(Integer.toString(selectedProduct.getMax()));
        modifyProductMin.setText(Integer.toString(selectedProduct.getMin()));
    }

    /**
     * Modify product add button.
     *
     * @param event the event
     */
    @FXML
    void modifyProductAddButton(ActionEvent event) {
        Part part = modifyProductsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            errorAlert("", "You must select a part to associate");
        } else {
            tempPartsList.add(part);
            modifyAssociatedProductTable.setItems(tempPartsList);
        }
    }

    /**
     * Modify associated part button.
     *
     * @param event the event
     */
    @FXML
    void modifyAssociatedPartButton(ActionEvent event) {
        Part part = modifyAssociatedProductTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            errorAlert("", "You must select a part to remove");
        } else {
            if (confirmationAlert("Confirm Removal", "Are you sure you want to remove this associated part?")) {
                tempPartsList.remove(part);
                modifyAssociatedProductTable.setItems(tempPartsList);
            }
        }
    }

    /**
     * Modify product save button.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void modifyProductSaveButton(ActionEvent event) throws IOException {
        Random rand = new Random();
        try {
            int id = rand.nextInt(1000000);
            String name = modifyProductName.getText();
            double price = Double.parseDouble(modifyProductPrice.getText());
            int stock = Integer.parseInt(modifyProductInv.getText());
            int max = Integer.parseInt(modifyProductMax.getText());
            int min = Integer.parseInt(modifyProductMin.getText());

            if (min >= max) {
                errorAlert("Value Error", "Your min value must be inferior to your max value");
            }
            if (stock < min || stock > max) {
                errorAlert("Value Error", " Your inventory quantity must be between min and max values");
            }
            Product product = new Product(id, name, price, stock, max, min);

            for (Part part: tempPartsList) {
                product.addAssociatedPart(part);
            }

            Inventory.deleteProduct(selectedProduct);
            Inventory.addProduct(product);
        } catch (Exception e) {
            errorAlert("","Invalid Data");
            return;
        }
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }

    /**
     * Modify product cancel button.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void modifyProductCancelButton(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }

    /**
     * Modify product search handler.
     *
     * @param event the event
     */
    @FXML
    void modifyProductSearchHandler(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.BACK_SPACE && modifyProductSearchText.getText().length() == 0) {
                modifyProductsTable.setItems(Inventory.getAllParts());
            } else {
                int searchID = Integer.parseInt(modifyProductSearchText.getText());
                modifyProductsTable.setItems(partsSearch(searchID));
            }
        } catch (NumberFormatException e) {
            String searchName = modifyProductSearchText.getText();
            modifyProductsTable.setItems(partsSearch(searchName));
        }
    }
}
