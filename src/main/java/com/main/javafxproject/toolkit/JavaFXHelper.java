package com.main.javafxproject.toolkit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXHelper {
    public static void getStage(String targetUrl, String stageTitle, Class classObject) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(classObject.getResource(targetUrl));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(stageTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
