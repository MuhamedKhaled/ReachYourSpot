package com.example.mohamed.reachyourspot.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("utc_offset")
	private int utcOffset;

	@SerializedName("formatted_address")
	private String formattedAddress;

	@SerializedName("types")
	private ArrayList<String> types;

	@SerializedName("website")
	private String website;

	@SerializedName("icon")
	private String icon;

	@SerializedName("rating")
	private Double rating;

	@SerializedName("address_components")
	private List<AddressComponentsItem> addressComponents;

	@SerializedName("url")
	private String url;

	@SerializedName("reference")
	private String reference;

	@SerializedName("reviews")
	private ArrayList<ReviewsItem> reviews;

	@SerializedName("scope")
	private String scope;

	@SerializedName("name")
	private String name;

	@SerializedName("opening_hours")
	private OpeningHours openingHours;

	@SerializedName("geometry")
	private Geometry geometry;

	@SerializedName("vicinity")
	private String vicinity;

	@SerializedName("id")
	private String id;

	@SerializedName("adr_address")
	private String adrAddress;

	@SerializedName("plus_code")
	private PlusCode plusCode;

	@SerializedName("formatted_phone_number")
	private String formattedPhoneNumber;

	@SerializedName("international_phone_number")
	private String internationalPhoneNumber;

	@SerializedName("place_id")
	private String placeId;

	public void setUtcOffset(int utcOffset){
		this.utcOffset = utcOffset;
	}

	public int getUtcOffset(){
		return utcOffset;
	}

	public void setFormattedAddress(String formattedAddress){
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedAddress(){
		return formattedAddress;
	}

	public void setTypes(ArrayList<String> types){
		this.types = types;
	}

	public ArrayList<String> getTypes(){
		return types;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setRating(Double rating){
		this.rating = rating;
	}

	public Double getRating(){
		return rating;
	}

	public void setAddressComponents(List<AddressComponentsItem> addressComponents){
		this.addressComponents = addressComponents;
	}

	public List<AddressComponentsItem> getAddressComponents(){
		return addressComponents;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setReference(String reference){
		this.reference = reference;
	}

	public String getReference(){
		return reference;
	}

	public void setReviews(ArrayList<ReviewsItem> reviews){
		this.reviews = reviews;
	}

	public ArrayList<ReviewsItem> getReviews(){
		return reviews;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public String getScope(){
		return scope;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setOpeningHours(OpeningHours openingHours){
		this.openingHours = openingHours;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}

	public String getVicinity(){
		return vicinity;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAdrAddress(String adrAddress){
		this.adrAddress = adrAddress;
	}

	public String getAdrAddress(){
		return adrAddress;
	}

	public void setPlusCode(PlusCode plusCode){
		this.plusCode = plusCode;
	}

	public PlusCode getPlusCode(){
		return plusCode;
	}

	public void setFormattedPhoneNumber(String formattedPhoneNumber){
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	public String getFormattedPhoneNumber(){
		return formattedPhoneNumber;
	}

	public void setInternationalPhoneNumber(String internationalPhoneNumber){
		this.internationalPhoneNumber = internationalPhoneNumber;
	}

	public String getInternationalPhoneNumber(){
		return internationalPhoneNumber;
	}

	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public String getPlaceId(){
		return placeId;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"utc_offset = '" + utcOffset + '\'' + 
			",formatted_address = '" + formattedAddress + '\'' + 
			",types = '" + types + '\'' + 
			",website = '" + website + '\'' + 
			",icon = '" + icon + '\'' + 
			",rating = '" + rating + '\'' + 
			",address_components = '" + addressComponents + '\'' + 
			",url = '" + url + '\'' + 
			",reference = '" + reference + '\'' + 
			",reviews = '" + reviews + '\'' + 
			",scope = '" + scope + '\'' + 
			",name = '" + name + '\'' + 
			",opening_hours = '" + openingHours + '\'' + 
			",geometry = '" + geometry + '\'' + 
			",vicinity = '" + vicinity + '\'' + 
			",id = '" + id + '\'' + 
			",adr_address = '" + adrAddress + '\'' + 
			",plus_code = '" + plusCode + '\'' + 
			",formatted_phone_number = '" + formattedPhoneNumber + '\'' + 
			",international_phone_number = '" + internationalPhoneNumber + '\'' + 
			",place_id = '" + placeId + '\'' + 
			"}";
		}
}