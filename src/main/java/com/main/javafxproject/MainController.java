package com.main.javafxproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static com.main.javafxproject.toolkit.JavaFXHelper.getStage;

public class MainController {

    @FXML
    void partAddButtonHandler(ActionEvent event) throws IOException {
        getStage("AddPartView.fxml", "Add Part", getClass());
    }

    @FXML
    void partModifyButtonHandler(ActionEvent event) throws IOException {
        getStage("ModifyPartView.fxml", "Modify Part", getClass());
    }

    @FXML
    void partDeleteButtonHandler(ActionEvent event) throws IOException {
    }

    @FXML
    void productAddButtonHandler(ActionEvent event) throws IOException {
        getStage("AddProductView.fxml", "Add Product", getClass());
    }

    @FXML
    void productModifyButtonHandler(ActionEvent event) throws IOException {
        getStage("ModifyProductView.fxml", "Modify Product", getClass());
    }

    @FXML
    void productDeleteButtonHandler(ActionEvent event) throws IOException {
    }

    @FXML
    void exitButtonHandler(ActionEvent event) throws IOException {
        Platform.exit();
    }

}
