package com.example.sarveshagrawal.canadiandetails.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sarvesh Agrawal on 27-07-2018.
 */

public class Canada_List_Model implements Parcelable {
    String title;
    String description;
    String imageHref;

    public Canada_List_Model() {
    }
    private Canada_List_Model(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageHref = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(description);
        out.writeString(imageHref);
    }
    public static final Parcelable.Creator<Canada_List_Model> CREATOR = new Parcelable.Creator<Canada_List_Model>() {
        public Canada_List_Model createFromParcel(Parcel in) {
            return new Canada_List_Model(in);
        }

        public Canada_List_Model[] newArray(int size) {
            return new Canada_List_Model[size];
        }
    };
}
