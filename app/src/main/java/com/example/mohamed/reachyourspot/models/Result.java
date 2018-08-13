
package com.example.mohamed.reachyourspot.models;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("geometry")

    private Geometry geometry;
    @SerializedName("icon")

    private String icon;
    @SerializedName("id")

    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("photos")
    private List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("reference")
    private String reference;
    @SerializedName("scope")
    private String scope;
    @SerializedName("types")
    private List<String> types = new ArrayList<String>();
    @SerializedName("vicinity")
    private String vicinity;
    @SerializedName("opening_hours")
    private OpeningHours openingHours;
    @SerializedName("plus_code")
    private PlusCode plusCode;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("price_level")
    private int priceLevel;

    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {



        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            instance.geometry = ((Geometry) in.readValue((Geometry.class.getClassLoader())));
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.photos, (com.example.mohamed.reachyourspot.models.Photo.class.getClassLoader()));
            instance.placeId = ((String) in.readValue((String.class.getClassLoader())));
            instance.reference = ((String) in.readValue((String.class.getClassLoader())));
            instance.scope = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.types, (java.lang.String.class.getClassLoader()));
            instance.vicinity = ((String) in.readValue((String.class.getClassLoader())));
            instance.openingHours = ((OpeningHours) in.readValue((OpeningHours.class.getClassLoader())));
            instance.plusCode = ((PlusCode) in.readValue((PlusCode.class.getClassLoader())));
            instance.rating = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.priceLevel = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * 
     * @param geometry
     *     The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * 
     * @param photos
     *     The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * 
     * @param reference
     *     The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 
     * @return
     *     The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * 
     * @param scope
     *     The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * 
     * @return
     *     The vicinity
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     * 
     * @param vicinity
     *     The vicinity
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    /**
     * 
     * @return
     *     The openingHours
     */
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    /**
     * 
     * @param openingHours
     *     The opening_hours
     */
    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * 
     * @return
     *     The plusCode
     */
    public PlusCode getPlusCode() {
        return plusCode;
    }

    /**
     * 
     * @param plusCode
     *     The plus_code
     */
    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The priceLevel
     */
    public int getPriceLevel() {
        return priceLevel;
    }

    /**
     * 
     * @param priceLevel
     *     The price_level
     */
    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geometry);
        dest.writeValue(icon);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(photos);
        dest.writeValue(placeId);
        dest.writeValue(reference);
        dest.writeValue(scope);
        dest.writeList(types);
        dest.writeValue(vicinity);
        dest.writeValue(openingHours);
        dest.writeValue(plusCode);
        dest.writeValue(rating);
        dest.writeValue(priceLevel);
    }

    public int describeContents() {
        return  0;
    }

}
