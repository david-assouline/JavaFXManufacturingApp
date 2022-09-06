package com.main.javafxproject.model;

import javafx.collections.ObservableList;

import java.util.Objects;

public class Inventory {
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (Part part: allParts) {
            if (part.getId() == partId) {
                return part;
            }
        } return null;
    }

    public static Part lookupPart(String partName) {
        for (Part part: allParts) {
            if (Objects.equals(part.getName(), partName)) {
                return part;
            }
        } return null;
    }

    public static Product lookupProduct(int productId) {
        for (Product product: allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        } return null;
    }

    public static Product lookupProduct(String productName) {
        for (Product product: allProducts) {
            if (Objects.equals(product.getName(), productName)) {
                return product;
            }
        } return null;
    }

    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return allParts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}
