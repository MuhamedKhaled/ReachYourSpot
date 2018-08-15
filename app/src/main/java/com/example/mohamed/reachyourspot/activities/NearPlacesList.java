package com.example.mohamed.reachyourspot.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.mohamed.reachyourspot.R;
import com.example.mohamed.reachyourspot.adapters.NearPlacesListAdapter;
import com.example.mohamed.reachyourspot.models.Place;
import com.example.mohamed.reachyourspot.utils.Constants;

import java.util.ArrayList;

public class NearPlacesList extends AppCompatActivity {

    public static final String TAG = NearPlacesList.class.getSimpleName();

    private ArrayList<Place> mNearByPlaceArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private NearPlacesListAdapter mPlaceListAdapter;
    private Double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_places_list);

        // get the intent and get the location Tag
        String locationTag = getIntent().getStringExtra(Constants.LOCATION_TYPE_EXTRA_TEXT);
        String locationName = getIntent().getStringExtra(Constants.LOCATION_NAME_EXTRA_TEXT);
         lat = getIntent().getDoubleExtra(Constants.CURRENT_LOCATION_LAT,0.0);
         lng = getIntent().getDoubleExtra(Constants.CURRENT_LOCATION_lNG,0.0);
        mNearByPlaceArrayList = getIntent()
                .getParcelableArrayListExtra(Constants.ALL_NEARBY_LOCATION_KEY);

        Log.d(TAG,  lat+" "+lng);
        Log.d(TAG,  Integer.toString(mNearByPlaceArrayList.size()));


        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        String actionBarTitleText = getResources().getString(R.string.near_by_tag) +
                " " + locationName + " " + getString(R.string.list_tag);
        setTitle(actionBarTitleText);
        actionBar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.place_list_recycler_view);

        if (mNearByPlaceArrayList.size()!=0)
        {
            setEmptyViewVisiblity(false);
            setUpRecyclerView();
        }
        else {
            setEmptyViewVisiblity(true);
        }

    }

    private void setEmptyViewVisiblity(boolean visible){
        if (visible){
            findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
        else {
            findViewById(R.id.empty_view).setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void setUpRecyclerView(){
        mGridLayoutManager = new GridLayoutManager(this, 1);
        mPlaceListAdapter = new NearPlacesListAdapter(this, mNearByPlaceArrayList);
        mPlaceListAdapter.setLatAndLog(lat,lng);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mPlaceListAdapter);
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
}
