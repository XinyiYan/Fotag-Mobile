package com.example.x46yan.fotagx46yan;


import android.graphics.Bitmap;
import java.util.*;
import java.util.List;

public class Model extends Observable {
    /** The observers that are watching this model for changes. */
    private List<Observer> observers;

    private int res[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10

    };

    private ArrayList<ImageModel> uploadedImages;
    private ArrayList<ImageModel> displayedImages;
    private HashMap<Integer, Bitmap> cache;

    private boolean gridView = false;

    private int filterby = 0;

    private ImageAdapter imageAdapter = null;

    private int identifier = -1;



    /**
     * Create a new model.
     */
    public Model() {

        this.observers = new ArrayList();
        this.uploadedImages = new ArrayList<>();
        this.displayedImages = new ArrayList<>();
        this.cache = new HashMap<>();
    }

    /**
     * Add an observer to be notified when this model changes.
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        super.notifyObservers();
    }


    public void setGridView(boolean t) {
        this.gridView = t;
    }

    public boolean getGridView() {
        return this.gridView;
    }

    public void updateImages() {
        displayedImages.clear();
        for(int i=0;i<uploadedImages.size();i++) {
            if(uploadedImages.get(i).getRating() >= filterby) {
                displayedImages.add(uploadedImages.get(i));
            }
        }
        imageAdapter.notifyDataSetChanged();
    }

    public void setFilter(int val) {
        this.filterby = val;
      //  System.out.print("clear filter");
        this.updateImages();
    }



    public void load() {
       // if (this.uploadedImages.isEmpty()) {
            for(int i=0; i<res.length; ++i) {
                uploadedImages.add(new ImageModel(0, res[i]));
            }
            this.updateImages();
       // }
    }

    public void AddImage(Bitmap bitmap) {
        this.uploadedImages.add(new ImageModel(0, this.identifier));
        cache.put(this.identifier, bitmap);
        this.identifier -= 1;
        this.updateImages();

    }

    public void removeAll() {
        this.displayedImages.clear();
        this.uploadedImages.clear();
        this.cache.clear();
        this.filterby = 0;
    }


    public int getNumOfDisplayedImages() {
        return this.displayedImages.size();
    }

    public int getRes(int i) {
        return this.displayedImages.get(i).getId();
    }

    public HashMap<Integer, Bitmap> getCache() {
        return cache;
    }

    public void setImageRating(int index, int rating) {
        this.displayedImages.get(index).setRating(rating);
        if(rating < this.filterby) {
            this.updateImages();
            this.imageAdapter.notifyDataSetChanged();
        }
    }

    public int getRatingAt(int index) {
        return this.displayedImages.get(index).getRating();
    }

    public void addAdapter(ImageAdapter im) {
        this.imageAdapter = im;

    }

    public void clearRatingAt(int index) {
        this.displayedImages.get(index).setRating(0);
        this.imageAdapter.notifyDataSetChanged();
    }
}
