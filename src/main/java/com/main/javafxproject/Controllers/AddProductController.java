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
 * The type Add product controller.
 */
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
     * The Associated parts table.
     */
    @FXML
    TableView<Part> associatedPartsTable;
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
     * The Add product search text.
     */
    @FXML
    TextField addProductSearchText;

    /**
     * The Temp parts list.
     */
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
            } else if (event.getCode() == KeyCode.ENTER){
                int searchID = Integer.parseInt(addProductSearchText.getText());
                if (partsSearch(searchID).size() > 0) {
                    productPartsTable.setItems(partsSearch(searchID));
                } else {
                    errorAlert("No Parts Found",  String.format("\"%s\" did not return any results", addProductSearchText.getText()));
                    addProductSearchText.clear();
                    productPartsTable.setItems(Inventory.getAllParts());
                }

            }
        } catch (NumberFormatException e) {
            String searchName = addProductSearchText.getText();
            if (partsSearch(searchName).size() > 0) {
                productPartsTable.setItems(partsSearch(searchName));
            } else {
                errorAlert("No Parts Found", String.format("\"%s\" did not return any results", addProductSearchText.getText()));
                addProductSearchText.clear();
                productPartsTable.setItems(Inventory.getAllParts());
            }
        }
    }
}
