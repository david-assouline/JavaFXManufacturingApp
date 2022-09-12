package com.main.javafxproject.Toolkit;

import com.main.javafxproject.Model.Inventory;
import com.main.javafxproject.Model.Part;
import com.main.javafxproject.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * The type Utility.
 */
public class Utility {

    /**
     * Gets stage.
     *
     * @param targetUrl  the target url
     * @param stageTitle the stage title
     * @throws IOException the io exception
     */
    public static void getStage(URL targetUrl, String stageTitle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(targetUrl);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(stageTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Close window.
     *
     * @param event the event
     */
    public static void closeWindow(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Error alert.
     *
     * @param title     the title
     * @param alertText the alert text
     */
    public static void errorAlert(String title, String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, alertText);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.showAndWait();
    }

    /**
     * Confirmation alert boolean.
     *
     * @param title     the title
     * @param alertText the alert text
     * @return the boolean
     */
    public static boolean confirmationAlert(String title, String alertText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, alertText, ButtonType.CANCEL, ButtonType.YES);
        alert.setTitle(title);
        alert.setHeaderText(title);
        Optional<ButtonType> confirm = alert.showAndWait();
        return (confirm.isPresent() && confirm.get() == ButtonType.YES);
    }

    /**
     * Parts search observable list.
     *
     * @param searchID the search id
     * @return the observable list
     */
    public static ObservableList<Part> partsSearch(int searchID) {
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        for (Part part : Inventory.getAllParts()) {
            if (part.getId() == searchID) {
                tempList.add(part);
            }
        }
        return tempList;
    }

    /**
     * Parts search observable list.
     *
     * @param searchName the search name
     * @return the observable list
     */
    public static ObservableList<Part> partsSearch(String searchName) {
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        for (Part part : Inventory.getAllParts()) {
            if (part.getName().toLowerCase().equals(searchName.toLowerCase())) {
                tempList.add(part);
            }
        }
        return tempList;
    }

    /**
     * Products search observable list.
     *
     * @param searchID the search id
     * @return the observable list
     */
    public static ObservableList<Product> productsSearch(int searchID) {
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        for (Product product : Inventory.getAllProducts()) {
            if (product.getId() == searchID) {
                tempList.add(product);
            }
        }
        return tempList;
    }

    /**
     * Products search observable list.
     *
     * @param searchName the search name
     * @return the observable list
     */
    public static ObservableList<Product> productsSearch(String searchName) {
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        for (Product product : Inventory.getAllProducts()) {
            if (product.getName().toLowerCase().equals(searchName.toLowerCase())) {
                tempList.add(product);
            }
        }
        return tempList;
    }
}
