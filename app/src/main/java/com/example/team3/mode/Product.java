package com.example.team3.mode;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int icon;

    public Product(int id, String name, int icon, double price, String description) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getIcon() {
        return icon;
    }
}
