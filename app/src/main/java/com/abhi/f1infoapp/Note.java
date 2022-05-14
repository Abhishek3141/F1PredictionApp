package com.abhi.f1infoapp;

public class Note {
    private int id;
    private String prediction;

    public Note(int id, String prediction) {
        this.id = id;
        this.prediction = prediction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
