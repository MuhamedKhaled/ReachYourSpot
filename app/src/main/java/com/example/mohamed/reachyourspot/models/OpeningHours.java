
package com.example.mohamed.reachyourspot.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class OpeningHours implements Parcelable
{

    @SerializedName("open_now")
    private boolean openNow;
    public final static Parcelable.Creator<OpeningHours> CREATOR = new Creator<OpeningHours>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OpeningHours createFromParcel(Parcel in) {
            OpeningHours instance = new OpeningHours();
            instance.openNow = ((boolean) in.readValue((boolean.class.getClassLoader())));
            return instance;
        }

        public OpeningHours[] newArray(int size) {
            return (new OpeningHours[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The openNow
     */
    public boolean isOpenNow() {
        return openNow;
    }

    /**
     * 
     * @param openNow
     *     The open_now
     */
    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(openNow);
    }

    public int describeContents() {
        return  0;
    }

}
