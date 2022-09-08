package com.main.javafxproject.Controllers;

import com.main.javafxproject.Main;
import com.main.javafxproject.Model.Inventory;
import com.main.javafxproject.Model.Part;
import com.main.javafxproject.Model.Product;
import com.main.javafxproject.Toolkit.Utility;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.main.javafxproject.Toolkit.Utility.*;

public class MainController implements Initializable {

    @FXML
    TableView<Part> partsTable;
    @FXML
    TableColumn<Part, Integer> partsTablePartId;
    @FXML
    TableColumn<Part, String> partsTablePartName;
    @FXML
    TableColumn<Part, Integer> partsTableInventoryLevel;
    @FXML
    TableColumn<Part, Double> partsTablePriceCost;

    @FXML
    TableView<Product> productsTable;
    @FXML
    TableColumn<Product, Integer> productsID;
    @FXML
    TableColumn<Product, String> productsName;
    @FXML
    TableColumn<Product, Integer> productsInventory;
    @FXML
    TableColumn<Product, Double> productsPriceCost;

    public static Part selectedPart;
    public static Product selectedProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTable.setItems(Inventory.getAllParts());
        partsTablePartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsTablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsTableInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsTablePriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        productsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void partAddButtonHandler(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("AddPartView.fxml"), "Add Part");
    }

    @FXML
    void partModifyButtonHandler(ActionEvent event) throws IOException {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            errorAlert("", "You must select a part to modify");
        } else {
            Utility.closeWindow(event);
            selectedPart = part;
            getStage((Main.class.getResource("ModifyPartView.fxml")), "Modify Part");
        }
    }

    @FXML
    void partDeleteButtonHandler(ActionEvent event) throws IOException {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            errorAlert("", "You must select a part to delete");
        } else {
            if (confirmationAlert("Confirm Delete", "Are you sure you want to delete this part?")) {
                Inventory.deletePart(part);
            }
        }
    }

    @FXML
    void productAddButtonHandler(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage((Main.class.getResource("AddProductView.fxml")), "Add Product");
    }

    @FXML
    void productModifyButtonHandler(ActionEvent event) throws IOException {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if (product == null) {
            errorAlert("", "You must select a product to modify");
        } else {
            Utility.closeWindow(event);
            selectedProduct = product;
            getStage((Main.class.getResource("ModifyProductView.fxml")), "Modify Product");
        }
    }

    @FXML
    void productDeleteButtonHandler(ActionEvent event) throws IOException {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if (product == null) {
            errorAlert("", "You must select a product to delete");
        } else {
            if (confirmationAlert("Confirm Delete", "Are you sure you want to delete this product?")) {
                Inventory.deleteProduct(product);
            }
        }
    }

    @FXML
    void exitButtonHandler(ActionEvent event) throws IOException {
        Platform.exit();
    }

}
