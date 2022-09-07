package com.main.javafxproject.Model;

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, Double price, int stock, int max, int min, String companyName) {
        super(id, name, price, stock, max, min);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
