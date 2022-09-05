package com.main.javafxproject.toolkit;

public class OutsourcedPartDataObject extends PartDataObject{

    String companyName;

    public OutsourcedPartDataObject(String ID, String name, String inv, String priceCost, String max, String min, String companyName) {
        super();
        this.ID = ID;
        this.name = name;
        this.inv = inv;
        this.priceCost = priceCost;
        this.max = max;
        this.min = min;
        this.companyName = companyName;

    }
}
