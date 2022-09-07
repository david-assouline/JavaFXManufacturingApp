package com.main.javafxproject.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModifyPartController {

    @FXML
    private Label MachineCompanyLabel;

    @FXML
    private TextField addPartName;

    @FXML
    private TextField addPartInv;

    @FXML
    private TextField addPartPriceCost;

    @FXML
    private TextField addPartMax;

    @FXML
    private TextField addPartMin;

    @FXML
    private TextField addPartMachineCompanyID;

    private boolean inHouse = true;
    private boolean outsourced = false;

    @FXML
    void InHouseRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Machine ID");
    }

    @FXML
    void OutsourcedRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Company Name");
    }

    @FXML
    void inHouseRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Machine ID");
        inHouse = true;
        outsourced = false;
    }

    @FXML
    void outsourcedRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Company Name");
        inHouse = false;
        outsourced = true;
    }
}
