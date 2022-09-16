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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    @FXML
    TextField partsSearchText;
    @FXML
    TextField productsSearchText;

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

    /**
     * Part add button handler.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void partAddButtonHandler(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("AddPartView.fxml"), "Add Part");
    }

    /**
     * Part modify button handler.
     *
     * @param event the event
     * @throws IOException the io exception
     *
     * RUNTIME ERROR => "NullPointerException" will be caused if the user clicks on the modify button without having
     * selected a part. To avoid this, an if-else statement has been added to validate that a part has been selected
     * prior to clicking on the modify button.
     */
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

    /**
     * Part delete button handler.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Product add button handler.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void productAddButtonHandler(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage((Main.class.getResource("AddProductView.fxml")), "Add Product");
    }

    /**
     * Product modify button handler.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Product delete button handler.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void productDeleteButtonHandler(ActionEvent event) throws IOException {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if (product == null) {
            errorAlert("", "You must select a product to delete");
        } else if (product.getAllAssociatedParts().size() != 0) {
            errorAlert("","You can not delete a product with associated parts");
        } else {
            if (confirmationAlert("","Are you sure you want to delete this product?")) {
                Inventory.deleteProduct(product);
            }
        }
    }

    /**
     * Exit button handler.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void exitButtonHandler(ActionEvent event) throws IOException {
        Platform.exit();
    }

    /**
     * Parts search handler.
     *
     * @param event the event
     */
    @FXML
    void partsSearchHandler(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.BACK_SPACE && partsSearchText.getText().length() == 0) {
                partsTable.setItems(Inventory.getAllParts());
            } else {
                int searchID = Integer.parseInt(partsSearchText.getText());
                partsTable.setItems(partsSearch(searchID));
            }
        } catch (NumberFormatException e) {
            String searchName = partsSearchText.getText();
            partsTable.setItems(partsSearch(searchName));
        }
    }

    /**
     * Products search handler.
     *
     * @param event the event
     */
    @FXML
    void productsSearchHandler(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.BACK_SPACE && productsSearchText.getText().length() == 0) {
                productsTable.setItems(Inventory.getAllProducts());
            } else {
                int searchID = Integer.parseInt(productsSearchText.getText());
                productsTable.setItems(productsSearch(searchID));
            }
        } catch (NumberFormatException e) {
            String searchName = productsSearchText.getText();
            productsTable.setItems(productsSearch(searchName));
        }
    }
}
