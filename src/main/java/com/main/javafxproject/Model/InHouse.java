package com.main.javafxproject.Model;

public class InHouse extends Part {

    private int machineId;

    public InHouse(int id, String name, Double price, int stock, int max, int min, int machineId) {
        super(id, name, price, stock, max, min);
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
