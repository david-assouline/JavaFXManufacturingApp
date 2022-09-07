package com.main.javafxproject.Controllers;

import com.main.javafxproject.Main;
import com.main.javafxproject.Model.Inventory;
import com.main.javafxproject.Model.Part;
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

import static com.main.javafxproject.Toolkit.Utility.errorAlert;
import static com.main.javafxproject.Toolkit.Utility.getStage;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTable.setItems(Inventory.getAllParts());
        partsTablePartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsTablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsTableInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsTablePriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void partAddButtonHandler(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("AddPartView.fxml"), "Add Part", getClass());
    }

    @FXML
    void partModifyButtonHandler(ActionEvent event) throws IOException {
        if (partsTable.getSelectionModel().getSelectedItem() == null) {
            errorAlert("", "You must select a part to modify");

        } else {
            var temp = partsTable.getSelectionModel().getSelectedItem();
            getStage((Main.class.getResource("ModifyPartView.fxml")), "Modify Part", getClass());
        }
    }

    @FXML
    void partDeleteButtonHandler(ActionEvent event) throws IOException {
        if (partsTable.getSelectionModel().getSelectedItem() == null) {
            errorAlert("", "You must select a part to modify");

        } else {
        }
    }

    @FXML
    void productAddButtonHandler(ActionEvent event) throws IOException {
        getStage((Main.class.getResource("AddProductView.fxml")), "Add Product", getClass());
    }

    @FXML
    void productModifyButtonHandler(ActionEvent event) throws IOException {
        getStage((Main.class.getResource("ModifyProductView.fxml")), "Modify Product", getClass());
    }

    @FXML
    void productDeleteButtonHandler(ActionEvent event) throws IOException {
    }

    @FXML
    void exitButtonHandler(ActionEvent event) throws IOException {
        Platform.exit();
    }

}
