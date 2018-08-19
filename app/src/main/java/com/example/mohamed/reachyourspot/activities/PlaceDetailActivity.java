package com.example.mohamed.reachyourspot.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mohamed.reachyourspot.R;
import com.example.mohamed.reachyourspot.Retrofit.RetrofitInterface;
import com.example.mohamed.reachyourspot.Retrofit.ServiceGenerator;
import com.example.mohamed.reachyourspot.fragments.AboutTabFragment;
import com.example.mohamed.reachyourspot.models.DetailsResponse;
import com.example.mohamed.reachyourspot.models.MyPlace;
import com.example.mohamed.reachyourspot.models.Place;
import com.example.mohamed.reachyourspot.models.PlaceUserRating;
import com.example.mohamed.reachyourspot.models.ReviewsItem;
import com.example.mohamed.reachyourspot.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String TAG = PlaceDetailActivity.class.getSimpleName();
    private ArrayList<PlaceUserRating> mPlaceUserRatingsArrayList = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String mPlaceShareUrl;
    private ProgressBar mProgressBar;
    private Double lat ,lng;
    private String currentPlaceDetailId,mCurrentPlaceDetailUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        currentPlaceDetailId = getIntent().getStringExtra(Constants.LOCATION_ID_EXTRA_TEXT);
        lat = getIntent().getDoubleExtra(Constants.CURRENT_LOCATION_LAT,0.0);
        lng = getIntent().getDoubleExtra(Constants.CURRENT_LOCATION_lNG,0.0);

        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        setTitle(R.string.app_name);
        actionBar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindViews();

        getCurrentPlaceAllDetails(currentPlaceDetailId);
    }

    /**
     *  Method to get the all details about place
     * @param currentPlaceDetailId
     */
    private void getCurrentPlaceAllDetails(String currentPlaceDetailId) {
        String url = getUrl(currentPlaceDetailId);
        RetrofitInterface mService=
                ServiceGenerator.createService(RetrofitInterface.class);
        Log.d("here", url);

        mService.getPlaceDetails(url).enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<DetailsResponse> call, @NonNull Response<DetailsResponse> response) {

                if (response.isSuccessful()) {
                    Log.d("do", response.body().getResult().getPlaceId());
                    handleProgressBarVisibality(false);

                    String status= "Status Not Available";
                    if( response.body().getResult().getOpeningHours()!=null && response.body().getResult().getOpeningHours().isOpenNow())
                        status= "open_now";

                    Double currentPlaceRating=0.0;
                    if (response.body().getResult().getRating()!=null)
                        currentPlaceRating = response.body().getResult().getRating();

                    String currentPlaceAddress ="Address Not Available";
                    if (response.body().getResult().getFormattedAddress()!=null)
                        currentPlaceAddress =response.body().getResult().getFormattedAddress();
                    String currentPlacePhoneNumber =  "Phone Number Not Registered";
                    if (response.body().getResult().getInternationalPhoneNumber()!=null)
                        currentPlacePhoneNumber = response.body().getResult().getInternationalPhoneNumber();

                    String currentPlaceWebsite = "Website Not Registered";
                    if (response.body().getResult().getWebsite()!=null)
                        currentPlaceWebsite = response.body().getResult().getWebsite();


                    Place currentPlaceDetail = new Place(
                            response.body().getResult().getPlaceId(),
                            response.body().getResult().getGeometry().getLocation().getLat(),
                            response.body().getResult().getGeometry().getLocation().getLng(),
                            response.body().getResult().getName(),
                            status,
                            currentPlaceRating,
                            currentPlaceAddress,
                            currentPlacePhoneNumber,
                            currentPlaceWebsite, response.body().getResult().getUrl());

                    ArrayList<ReviewsItem> reviewsItem = response.body().getResult().getReviews();
                    if (reviewsItem!=null)
                    {

                        for (int i = 0 ;i<reviewsItem.size();i++){

                            PlaceUserRating placeUserRating = new PlaceUserRating(
                                    reviewsItem.get(i).getAuthorName(),
                                    reviewsItem.get(i).getProfilePhotoUrl(),
                                    reviewsItem.get(i).getRating(),
                                    reviewsItem.get(i).getRelativeTimeDescription(),
                                    reviewsItem.get(i).getText()
                            );

                            mPlaceUserRatingsArrayList.add(placeUserRating);

                        }


                    }

                    Bundle currentPlaceDetailData = new Bundle();
                    currentPlaceDetailData.putParcelable(
                            Constants.CURRENT_LOCATION_DATA_KEY, currentPlaceDetail);
                    Bundle currentPlaceUserRatingDetail = new Bundle();
                    currentPlaceUserRatingDetail.putParcelableArrayList(
                            Constants.CURRENT_LOCATION_USER_RATING_KEY,
                            mPlaceUserRatingsArrayList);

                    setupViewPager(mViewPager, currentPlaceDetailData,
                            currentPlaceUserRatingDetail);

                    mTabLayout.setupWithViewPager(mViewPager);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DetailsResponse> call, @NonNull Throwable t) {
                Log.d("Failure", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void bindViews(){
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        handleProgressBarVisibality(true);
    }

    //        https://maps.googleapis.com/maps/api/place/details/json?placeid=&key=""

    private String getUrl(String placeId) {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json?");
        googlePlaceUrl.append("&placeid=" + placeId);
        googlePlaceUrl.append("&key=" + getResources().getString(R.string.google_maps_key));

        Log.d("getUrl", googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.share_icon:
                Intent sharePlaceUrlIntent = new Intent(Intent.ACTION_SEND);
                sharePlaceUrlIntent.setType("text/plain");
                sharePlaceUrlIntent.putExtra(Intent.EXTRA_TEXT, mPlaceShareUrl);
                startActivity(sharePlaceUrlIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleProgressBarVisibality(Boolean isVisible){
        if (isVisible)
            mProgressBar.setVisibility(View.VISIBLE);
        else
            mProgressBar.setVisibility(View.GONE);
    }


    private void setupViewPager(ViewPager viewPager, Bundle currentPlaceDetailData,
                                Bundle currentPlaceUserRatingDetail) {
        ViewPagerAdapter viewpagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        viewpagerAdapter.setBundleData(currentPlaceDetailData, currentPlaceUserRatingDetail);
        viewPager.setAdapter(viewpagerAdapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        Bundle placeAboutFragmentBundle;
        Bundle placeReviewFragmentBundle;

        private ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    AboutTabFragment placeAboutDetailFragment = new AboutTabFragment();
                    placeAboutDetailFragment.setArguments(placeAboutFragmentBundle);
                    placeAboutDetailFragment.setLatAndLng(lat,lng);
                    return placeAboutDetailFragment;
                case 1:
//                    PlaceReviewDetail placeReviewDetailFragment = new PlaceReviewDetail();
//                    placeReviewDetailFragment.setArguments(placeReviewFragmentBundle);
//                    return placeReviewDetailFragment;
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        private void setBundleData(Bundle aboutFragmentBundle, Bundle reviewFragmentBundle) {
            placeAboutFragmentBundle = aboutFragmentBundle;
            placeReviewFragmentBundle = reviewFragmentBundle;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.about_string);
                case 1:
                    return getString(R.string.review_string);
            }
            return null;
        }
    }
}
