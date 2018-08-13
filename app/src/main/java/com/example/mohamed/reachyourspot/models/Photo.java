
package com.example.mohamed.reachyourspot.models;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable
{

    @SerializedName("height")
    private int height;
    @SerializedName("html_attributions")

    private ArrayList<String> htmlAttributions = new ArrayList<String>();
    @SerializedName("photo_reference")
    private String photoReference;
    @SerializedName("width")
    private int width;
    public final static Parcelable.Creator<Photo> CREATOR = new Creator<Photo>() {



        public Photo createFromParcel(Parcel in) {
            Photo instance = new Photo();
            instance.height = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.htmlAttributions, (java.lang.String.class.getClassLoader()));
            instance.photoReference = ((String) in.readValue((String.class.getClassLoader())));
            instance.width = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Photo[] newArray(int size) {
            return (new Photo[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The htmlAttributions
     */
    public ArrayList<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    /**
     * 
     * @param htmlAttributions
     *     The html_attributions
     */
    public void setHtmlAttributions(ArrayList<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    /**
     * 
     * @return
     *     The photoReference
     */
    public String getPhotoReference() {
        return photoReference;
    }

    /**
     * 
     * @param photoReference
     *     The photo_reference
     */
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    /**
     * 
     * @return
     *     The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(height);
        dest.writeList(htmlAttributions);
        dest.writeValue(photoReference);
        dest.writeValue(width);
    }

    public int describeContents() {
        return  0;
    }

}
