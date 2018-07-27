package com.example.sarveshagrawal.canadiandetails.Data;

/**
 * Created by Sarvesh Agrawal on 27-07-2018.
 */

public class Canada_List_Model {
    String title;
    String description;
    String imageHref;

    public Canada_List_Model(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
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

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
