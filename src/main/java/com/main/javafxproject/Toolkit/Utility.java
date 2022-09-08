package com.main.javafxproject.Toolkit;

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

public class Utility {

    public static void getStage(URL targetUrl, String stageTitle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(targetUrl);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(stageTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void closeWindow(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static boolean errorAlert(String title, String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, alertText);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.showAndWait();
        return false;
        }

    public static boolean confirmationAlert(String title, String alertText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, alertText, ButtonType.CANCEL, ButtonType.YES);
        alert.setTitle(title);
        alert.setHeaderText(title);
        Optional<ButtonType> confirm = alert.showAndWait();
        return (confirm.isPresent() && confirm.get() == ButtonType.YES);
    }
}
