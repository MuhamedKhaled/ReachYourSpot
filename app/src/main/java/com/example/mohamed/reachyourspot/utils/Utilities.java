package com.example.mohamed.reachyourspot.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utilities {

    public static boolean isNetworkAvailable(Context mContext) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static Double getDistanceFromLatLonInKm(Double firstPointLatitude,
                                                   Double firstPointLongitude,
                                                   Double secondPointLatitude,
                                                   Double secondPointLongitude) {
        // Radius of the earth in km
        final int radiusOfEarth = 6371;
        Double latitudeDistance = Math.toRadians(secondPointLatitude - firstPointLatitude);
        Double longitudeDistance = Math.toRadians(secondPointLongitude - firstPointLongitude);

        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(firstPointLatitude)) * Math.cos(Math.toRadians(secondPointLatitude))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//         convert to meters
        double distance = radiusOfEarth * c ;
        distance = Math.pow(distance, 2) + Math.pow(0.0, 2);

        return Math.sqrt(distance);
    }

}
