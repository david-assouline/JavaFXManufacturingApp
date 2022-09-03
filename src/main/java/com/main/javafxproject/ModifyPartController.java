package com.main.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ModifyPartController {

    @FXML
    private Label MachineCompanyLabel;

    @FXML
    void InHouseRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Machine ID");
    }

    @FXML
    void OutsourcedRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Company Name");
    }
}
