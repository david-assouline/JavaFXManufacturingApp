package com.main.javafxproject.Controllers;

import com.main.javafxproject.Main;
import com.main.javafxproject.Model.InHouse;
import com.main.javafxproject.Model.Inventory;
import com.main.javafxproject.Model.Outsourced;
import com.main.javafxproject.Toolkit.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

import static com.main.javafxproject.Toolkit.Utility.getStage;

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
    void addPartSaveButton(ActionEvent event) throws IOException {
        Random rand = new Random();

        int id = rand.nextInt(1000000);
        String name = addPartName.getText();
        Double price = Double.parseDouble(addPartPriceCost.getText());
        int stock = Integer.parseInt(addPartInv.getText());
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
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }

    @FXML
    void addPartCancelButton(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }
}
