package com.example.yourmeal.model;

public class Ingredient {

    private String name, img, measure;

    public Ingredient(String name, String img, String measure) {
        this.name = name;
        this.img = img;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getMeasure() {
        return measure;
    }
}
