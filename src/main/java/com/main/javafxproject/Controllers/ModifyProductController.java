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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.main.javafxproject.Toolkit.Utility.errorAlert;
import static com.main.javafxproject.Toolkit.Utility.getStage;

public class ModifyProductController implements Initializable {
    @FXML
    TextField modifyProductName;
    @FXML
    TextField modifyProductID;
    @FXML
    TextField modifyProductInv;
    @FXML
    TextField modifyProductPrice;
    @FXML
    TextField modifyProductMax;
    @FXML
    TextField modifyProductMin;
    @FXML
    TableView<Part> modifyProductsTable;
    @FXML
    TableColumn<Part, Integer> productPartsTablePartId;
    @FXML
    TableColumn<Part, String> productPartsTablePartName;
    @FXML
    TableColumn<Part, Integer> productPartsTableInventoryLevel;
    @FXML
    TableColumn<Part, Double> productPartsTablePriceCost;
    @FXML
    TableView<Part> modifyAssociatedProductTable;
    @FXML
    TableColumn<Part, Integer> associatedPartsTablePartId;
    @FXML
    TableColumn<Part, String> associatedPartsTablePartName;
    @FXML
    TableColumn<Part, Integer> associatedPartsTableInventoryLevel;
    @FXML
    TableColumn<Part, Double> associatedPartsTablePriceCost;

    Product selectedProduct = MainController.selectedProduct;
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

    @FXML
    void modifyAssociatedPartButton(ActionEvent event) {
        Part part = modifyAssociatedProductTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            errorAlert("", "You must select a part to remove");
        } else {
            tempPartsList.remove(part);
            modifyAssociatedProductTable.setItems(tempPartsList);
        }
    }

    @FXML
    void modifyProductSaveButton(ActionEvent event) {

    }

    @FXML
    void modifyProductCancelButton(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }
}
