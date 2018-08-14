package com.example.mohamed.reachyourspot.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mohamed.reachyourspot.R;
import com.example.mohamed.reachyourspot.Retrofit.RetrofitInterface;
import com.example.mohamed.reachyourspot.Retrofit.ServiceGenerator;
import com.example.mohamed.reachyourspot.models.Location;
import com.example.mohamed.reachyourspot.models.MyPlace;
import com.example.mohamed.reachyourspot.models.Place;
import com.example.mohamed.reachyourspot.models.Result;
import com.example.mohamed.reachyourspot.utils.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int PERMISSION_CODE = 1000;
    private static final int ZoomingValue = 11;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private double latitude=30.0316702, longitude=31.2408997;
    private Location mLastlocation;
    private Marker mMarker;
    private LocationRequest mLocationRequest;
    private String mLocationName;
    private String mLocationTag;

    /**
     * ArrayList to store the Near By Place List
     */
    private ArrayList<Place> mNearByPlaceArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationTag = getIntent().getStringExtra(Constants.LOCATION_TYPE_EXTRA_TEXT);
        mLocationName = getIntent().getStringExtra(Constants.LOCATION_NAME_EXTRA_TEXT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);

        String actionBarTitleText = getResources().getString(R.string.near_by_tag) +
                " " + mLocationName + " " + getString(R.string.list_tag);
        setTitle(actionBarTitleText);
        actionBar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TextView) findViewById(R.id.place_list_placeholder_text_view))
                .setText(getResources().getString(R.string.near_by_tag) + " " + mLocationName +
                        " " + getString(R.string.list_tag));

        nearByPlace(mLocationTag);


        findViewById(R.id.place_list_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"Here",Toast.LENGTH_SHORT).show();
                for (int i = 0 ; i <mNearByPlaceArrayList.size();i++){
                    Log.d("Test", mNearByPlaceArrayList.get(i).getPlaceName());
                }

//                Intent placeDetailTransferIntent = new Intent(PlaceListOnMapActivity.this, PlaceListActivity.class);
//                placeDetailTransferIntent.putParcelableArrayListExtra(
//                        GoogleApiUrl.ALL_NEARBY_LOCATION_KEY, mNearByPlaceArrayList);
//                placeDetailTransferIntent.putExtra(GoogleApiUrl.LOCATION_TYPE_EXTRA_TEXT, mLocationTag);
//                placeDetailTransferIntent.putExtra(GoogleApiUrl.LOCATION_NAME_EXTRA_TEXT, mLocationName);
//                startActivity(placeDetailTransferIntent);
//                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_in);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nearByPlace(String placeType) {
        if (mMap != null)
            mMap.clear();
        String url = getUrl(latitude, longitude, placeType);
        RetrofitInterface mService=
                ServiceGenerator.createService(RetrofitInterface.class);

        mService.getPlaces(url).enqueue(new Callback<MyPlace>() {
            @Override
            public void onResponse(@NonNull Call<MyPlace> call, @NonNull Response<MyPlace> response) {
                Log.d("eshta", Integer.toString(response.body().getResults().size()));
                if (response.isSuccessful()) {
                    addMarksOnMap(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyPlace> call, @NonNull Throwable t) {
                Log.d("Failure", t.getMessage());
                t.printStackTrace();

            }
        });


    }

    private void addMarksOnMap(MyPlace places) {

        for (int i = 0; i < places.getResults().size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            Result googlePlaces = places.getResults().get(i);
            double lat = googlePlaces.getGeometry().getLocation().getLat();
            double lng = googlePlaces.getGeometry().getLocation().getLng();
            String placeName = googlePlaces.getName();
            String vicinity = googlePlaces.getVicinity();

            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(ZoomingValue));

            String status= "Status Not Available";
            if(googlePlaces.getOpeningHours().isOpenNow())
                 status= "open_now";


            Place singlePlace = new Place(
                googlePlaces.getPlaceId(), lat,lng,placeName,status,
                    googlePlaces.getRating(),vicinity
            );

            mNearByPlaceArrayList.add(singlePlace);

        }
    }

    /**
     * https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=30.3280983,-81.4855&radius=10000&type=market&sensor=true&key=AIzaSyDGaOnd40WcRJaSrNNrqdOBtXwip95KPDI
     **/
    private String getUrl(double latitude, double longitude, String placeType) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius=" + Constants.RADIUS);
        googlePlaceUrl.append("&type=" + placeType);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + getResources().getString(R.string.google_maps_key));

        Log.d("getUrl", googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    private boolean checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, PERMISSION_CODE);

            } else
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, PERMISSION_CODE);

            return false;

        } else
            return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null)
                            buildGoogleApiClient();

                        mMap.setMyLocationEnabled(true);

                    }
                } else
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

            }
            break;
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Init Google play Services
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED){

                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);

            }

        }

        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }

        mMap.getUiSettings().isMyLocationButtonEnabled();

    }
    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){


            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(android.location.Location location) {
       // mLastlocation =location;

        if (mMarker !=null)
        {
            mMarker.remove();
        }

        latitude = location.getLatitude();
        longitude = location.getLongitude();
            Log.d("eshta", "onLocationChanged: "+latitude+" " +longitude);
        LatLng latLng = new LatLng(latitude,longitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                    .title("Your Location")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        mMarker = mMap.addMarker(markerOptions);

        // Move Camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(ZoomingValue));

        if (mGoogleApiClient !=null)
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);


    }


}
