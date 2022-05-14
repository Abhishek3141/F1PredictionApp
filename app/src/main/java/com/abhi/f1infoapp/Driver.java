package com.abhi.f1infoapp;

public class Driver {
    private String name;
    private int image; //Images inside drawable folder
    private String description;
    private int prediction;
    private int id;

    public Driver(int id,String name, int image,String description,int prediction){
        this.name = name;
        this.image = image;
        this.description = description;
        this.prediction = prediction;
        this.id = id;



    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
