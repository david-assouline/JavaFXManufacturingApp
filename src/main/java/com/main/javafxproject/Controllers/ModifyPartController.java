package com.main.javafxproject.Controllers;

import com.main.javafxproject.Main;
import com.main.javafxproject.Model.InHouse;
import com.main.javafxproject.Model.Inventory;
import com.main.javafxproject.Model.Outsourced;
import com.main.javafxproject.Model.Part;
import com.main.javafxproject.Toolkit.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.main.javafxproject.Toolkit.Utility.errorAlert;
import static com.main.javafxproject.Toolkit.Utility.getStage;

/**
 * The type Modify part controller.
 */
public class ModifyPartController implements Initializable {

    /**
     * The Selected part.
     */
    Part selectedPart = MainController.selectedPart;

    @FXML
    private Label MachineCompanyLabel;

    @FXML
    private TextField modifyPartID;

    @FXML
    private TextField modifyPartName;

    @FXML
    private TextField modifyPartInv;

    @FXML
    private TextField modifyPartPriceCost;

    @FXML
    private TextField modifyPartMax;

    @FXML
    private TextField modifyPartMin;

    @FXML
    private TextField modifyPartMachineCompanyID;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private RadioButton outsourcedRadio;

    private boolean inHouse = true;
    private boolean outsourced = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modifyPartID.setText(Integer.toString(selectedPart.getId()));
        modifyPartName.setText(selectedPart.getName());
        modifyPartInv.setText(Integer.toString(selectedPart.getStock()));
        modifyPartPriceCost.setText(Double.toString(selectedPart.getPrice()));
        modifyPartMax.setText(Integer.toString(selectedPart.getMax()));
        modifyPartMin.setText(Integer.toString(selectedPart.getMin()));
        if (selectedPart instanceof InHouse) {
            MachineCompanyLabel.setText("Machine ID");
            inHouseRadio.setSelected(true);
            inHouse = true;
            outsourced = false;
            modifyPartMachineCompanyID.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        } else {
            MachineCompanyLabel.setText("Company Name");
            outsourcedRadio.setSelected(true);
            inHouse = false;
            outsourced = true;
            modifyPartMachineCompanyID.setText(((Outsourced) selectedPart).getCompanyName());
        }
    }

    /**
     * In house radio handler.
     *
     * @param event the event
     */
    @FXML
    void InHouseRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Machine ID");
        inHouse = true;
        outsourced = false;
    }

    /**
     * Outsourced radio handler.
     *
     * @param event the event
     */
    @FXML
    void OutsourcedRadioHandler(ActionEvent event) {
        MachineCompanyLabel.setText("Company Name");
        inHouse = false;
        outsourced = true;
    }

    /**
     * Modify part cancel button.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void modifyPartCancelButton(ActionEvent event) throws IOException {
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");
    }

    /**
     * Modify part save button.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void modifyPartSaveButton(ActionEvent event) throws IOException {
        String name = modifyPartName.getText();
        Double price = Double.parseDouble(modifyPartPriceCost.getText());
        int stock = Integer.parseInt(modifyPartInv.getText());
        int max = Integer.parseInt(modifyPartMax.getText());
        int min = Integer.parseInt(modifyPartMin.getText());
        int machineId;
        String companyName;

        if (min >= max) {
            errorAlert("Value Error", "Your min value must be inferior to your max value");
            return;
        }
        if (stock < min || stock > max) {
            errorAlert("Value Error", " Your inventory quantity must be between min and max values");
            return;
        }

        if (inHouse) {
            machineId = Integer.parseInt(modifyPartMachineCompanyID.getText());
            InHouse inHouse = new InHouse(selectedPart.getId(), name, price, stock, max, min, machineId);
            Inventory.deletePart(selectedPart);
            Inventory.addPart(inHouse);
        } else if (outsourced) {
            companyName = modifyPartMachineCompanyID.getText();
            Outsourced outsourced = new Outsourced(selectedPart.getId(), name, price, stock, max, min, companyName);
            Inventory.deletePart(selectedPart);
            Inventory.addPart(outsourced);
        }
        Utility.closeWindow(event);
        getStage(Main.class.getResource("MainView.fxml"), "Add Part");

    }
}
