package com.example.aditya.simpleapp.model;

import java.io.Serializable;

/**
 * Created by aditya on 13/9/16.
 *
 * Base model
 */
public class Content implements Serializable{

    String image;

    String description;

    String title;

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
