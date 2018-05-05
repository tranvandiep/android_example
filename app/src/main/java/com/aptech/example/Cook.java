package com.aptech.example;

import java.io.Serializable;

/**
 * Created by Diep.Tran on 5/3/18.
 */

public class Cook implements Serializable{
    String imageUrl;
    String title;
    String description;
    String price;

    public Cook(String imageUrl, String title, String description, String price) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
