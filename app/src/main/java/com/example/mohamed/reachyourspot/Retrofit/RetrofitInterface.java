package com.example.mohamed.reachyourspot.Retrofit;


import com.example.mohamed.reachyourspot.models.MyPlace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mohamed on 09/03/18.
 */

public interface RetrofitInterface {

    @GET
    Call<MyPlace> getPlaces(@Url String url);

}
