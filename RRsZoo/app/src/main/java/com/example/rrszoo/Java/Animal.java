package com.example.rrszoo.Java;

import android.media.Image;

public class Animal {
    private String name;
    private String location;
    private String lifetime;
    private String food;
    private String numberOfChildrens;
    private Image image;

    public Animal(String name, String location, String lifetime, String food, String numberOfChildrens,Image image) {
        this.name = name;
        this.location = location;
        this.lifetime = lifetime;
        this.food = food;
        this.numberOfChildrens = numberOfChildrens;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getNumberOfChildrens() {
        return numberOfChildrens;
    }

    public void setNumberOfChildrens(String numberOfChildrens) {
        this.numberOfChildrens = numberOfChildrens;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
