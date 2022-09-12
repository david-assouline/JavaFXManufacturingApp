package com.main.javafxproject.Model;

/**
 * The type Outsourced.
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     * Instantiates a new Outsourced.
     *
     * @param id          the id
     * @param name        the name
     * @param price       the price
     * @param stock       the stock
     * @param max         the max
     * @param min         the min
     * @param companyName the company name
     */
    public Outsourced(int id, String name, Double price, int stock, int max, int min, String companyName) {
        super(id, name, price, stock, max, min);
        this.companyName = companyName;
    }

    /**
     * Gets company name.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets company name.
     *
     * @param companyName the company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
