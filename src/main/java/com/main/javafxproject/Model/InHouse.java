package com.main.javafxproject.Model;

/**
 * The type In house.
 */
public class InHouse extends Part {

    private int machineId;

    /**
     * Instantiates a new In house.
     *
     * @param id        the id
     * @param name      the name
     * @param price     the price
     * @param stock     the stock
     * @param max       the max
     * @param min       the min
     * @param machineId the machine id
     */
    public InHouse(int id, String name, Double price, int stock, int max, int min, int machineId) {
        super(id, name, price, stock, max, min);
        this.machineId = machineId;
    }

    /**
     * Gets machine id.
     *
     * @return the machine id
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets machine id.
     *
     * @param machineId the machine id
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
