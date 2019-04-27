package com.example.x46yan.fotagx46yan;

public class ImageModel {
    private int rating;
    private int id;

    ImageModel(int r, int i) {
        rating = r;
        id = i;
    }

    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setRating(int r) {
        rating = r;
    }
}
