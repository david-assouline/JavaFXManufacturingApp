package com.main.javafxproject.toolkit;

public class InHousePartDataObject extends PartDataObject {

    String machineId;

    public InHousePartDataObject(String ID, String name, String inv, String priceCost, String max, String min, String machineId) {
        super();
        this.ID = ID;
        this.name = name;
        this.inv = inv;
        this.priceCost = priceCost;
        this.max = max;
        this.min = min;
        this.machineId = machineId;
    }
}
