package com.main.javafxproject;

import com.main.javafxproject.toolkit.InHousePartDataObject;
import com.main.javafxproject.toolkit.JavaFXHelper;
import com.main.javafxproject.toolkit.OutsourcedPartDataObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;

public class AddPartController {

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

    @FXML
    void addPartSaveButton(ActionEvent event) {
        Random rand = new Random();

        String ID = Integer.toString(rand.nextInt(1000000));
        String name = addPartName.getText();
        String inv = addPartInv.getText();
        String priceCost = addPartPriceCost.getText();
        String max = addPartMax.getText();
        String min = addPartMin.getText();
        String machineId;
        String companyName;

        if (inHouse) {
            machineId = addPartMachineCompanyID.getText();
            InHousePartDataObject inHousePartDataObject = new InHousePartDataObject(ID, name, inv, priceCost, max, min, machineId);
        } else if (outsourced) {
            companyName = addPartMachineCompanyID.getText();
            OutsourcedPartDataObject outsourcedPartDataObject = new OutsourcedPartDataObject(ID, name, inv, priceCost, max, min, companyName);
        }

        JavaFXHelper.closeWindow(event);

    }

    @FXML
    void addPartCancelButton(ActionEvent event){
        JavaFXHelper.closeWindow(event);
    }
}
