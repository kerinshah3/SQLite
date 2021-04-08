package com.example.a2starter;

public class Flavours {

    String Name;
    String Description;
    int quantity;
    int price;

    public Flavours() {
    }

    public Flavours(String name, String description, int quantity, int price) {
        Name = name;
        Description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
