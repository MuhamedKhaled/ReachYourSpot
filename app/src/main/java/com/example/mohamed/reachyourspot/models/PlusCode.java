
package com.example.mohamed.reachyourspot.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlusCode implements Parcelable
{

    @SerializedName("compound_code")
    private String compoundCode;
    @SerializedName("global_code")
    private String globalCode;
    public final static Parcelable.Creator<PlusCode> CREATOR = new Creator<PlusCode>() {



        public PlusCode createFromParcel(Parcel in) {
            PlusCode instance = new PlusCode();
            instance.compoundCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.globalCode = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public PlusCode[] newArray(int size) {
            return (new PlusCode[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The compoundCode
     */
    public String getCompoundCode() {
        return compoundCode;
    }

    /**
     * 
     * @param compoundCode
     *     The compound_code
     */
    public void setCompoundCode(String compoundCode) {
        this.compoundCode = compoundCode;
    }

    /**
     * 
     * @return
     *     The globalCode
     */
    public String getGlobalCode() {
        return globalCode;
    }

    /**
     * 
     * @param globalCode
     *     The global_code
     */
    public void setGlobalCode(String globalCode) {
        this.globalCode = globalCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(compoundCode);
        dest.writeValue(globalCode);
    }

    public int describeContents() {
        return  0;
    }

}
