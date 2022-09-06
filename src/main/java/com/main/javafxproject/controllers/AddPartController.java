package com.main.javafxproject.controllers;

import com.main.javafxproject.model.InHouse;
import com.main.javafxproject.model.Inventory;
import com.main.javafxproject.toolkit.JavaFXHelper;
import com.main.javafxproject.model.Outsourced;
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

        int id = rand.nextInt(1000000);
        String name = addPartName.getText();
        Double price = Double.parseDouble(addPartInv.getText());
        int stock = Integer.parseInt(addPartPriceCost.getText());
        int max = Integer.parseInt(addPartMax.getText());
        int min = Integer.parseInt(addPartMin.getText());
        int machineId;
        String companyName;

        if (inHouse) {
            machineId = Integer.parseInt(addPartMachineCompanyID.getText());
            InHouse inHouse = new InHouse(id, name, price, stock, max, min, machineId);
            Inventory.addPart(inHouse);
        } else if (outsourced) {
            companyName = addPartMachineCompanyID.getText();
            Outsourced outsourced = new Outsourced(id, name, price, stock, max, min, companyName);
            Inventory.addPart(outsourced);
        }
        JavaFXHelper.closeWindow(event);

    }

    @FXML
    void addPartCancelButton(ActionEvent event){
        JavaFXHelper.closeWindow(event);
    }
}
