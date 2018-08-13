
package com.example.mohamed.reachyourspot.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPlace implements Parcelable
{

    @SerializedName("html_attributions")

    private ArrayList<String> htmlAttributions = new ArrayList<String>();
    @SerializedName("next_page_token")

    private String nextPageToken;
    @SerializedName("results")
    private ArrayList<Result> results = new ArrayList<Result>();
    @SerializedName("status")

    private String status;
    public final static Parcelable.Creator<MyPlace> CREATOR = new Creator<MyPlace>() {



        public MyPlace createFromParcel(Parcel in) {
            MyPlace instance = new MyPlace();
            in.readList(instance.htmlAttributions, (java.lang.Object.class.getClassLoader()));
            instance.nextPageToken = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.results, (com.example.mohamed.reachyourspot.models.Result.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public MyPlace[] newArray(int size) {
            return (new MyPlace[size]);
        }

    }
    ;

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
     *     The nextPageToken
     */
    public String getNextPageToken() {
        return nextPageToken;
    }

    /**
     * 
     * @param nextPageToken
     *     The next_page_token
     */
    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    /**
     * 
     * @return
     *     The results
     */
    public ArrayList<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(htmlAttributions);
        dest.writeValue(nextPageToken);
        dest.writeList(results);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
