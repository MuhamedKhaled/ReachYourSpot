
package com.example.mohamed.reachyourspot.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Northeast implements Parcelable
{

    @SerializedName("lat")

    private double lat;
    @SerializedName("lng")

    private double lng;
    public final static Parcelable.Creator<Northeast> CREATOR = new Creator<Northeast>() {


        public Northeast createFromParcel(Parcel in) {
            Northeast instance = new Northeast();
            instance.lat = ((double) in.readValue((double.class.getClassLoader())));
            instance.lng = ((double) in.readValue((double.class.getClassLoader())));
            return instance;
        }

        public Northeast[] newArray(int size) {
            return (new Northeast[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * 
     * @param lng
     *     The lng
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lng);
    }

    public int describeContents() {
        return  0;
    }

}
