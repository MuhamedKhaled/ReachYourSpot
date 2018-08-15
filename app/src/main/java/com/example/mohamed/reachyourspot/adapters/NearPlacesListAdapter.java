package com.example.mohamed.reachyourspot.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.reachyourspot.R;
import com.example.mohamed.reachyourspot.models.Place;
import com.example.mohamed.reachyourspot.utils.Utilities;

import java.util.ArrayList;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class NearPlacesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Context of the activity
    private Context mContext;
    private ArrayList<Place> mNearByPlaceArrayList = new ArrayList<>();
    private double currentlat , currentlog;

    public void setLatAndLog(double currentlat, double currentlog) {
        this.currentlat = currentlat;
        this.currentlog = currentlog;
    }


    public NearPlacesListAdapter(Context mContext, ArrayList<Place> mNearByPlaceArrayList) {
        this.mContext = mContext;
        this.mNearByPlaceArrayList = mNearByPlaceArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaceListAdapterViewHolder(LayoutInflater
                .from(mContext).inflate(R.layout.place_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PlaceListAdapterViewHolder) holder).bindViews(position);

    }

    @Override
    public int getItemCount() {
        return mNearByPlaceArrayList.size();
    }

    private class PlaceListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //reference of the views
        private TextView mPlaceDistanceTextView;
        private TextView mPlaceNameTextView;
        private TextView mPlaceAddressTextView;
        private TextView mPlaceOpenStatusTextView;
        private MaterialRatingBar mPlaceRating;
        private ImageView mLocationIcon;

        int mItemPosition;

        private PlaceListAdapterViewHolder(View itemView) {
            super(itemView);

            mPlaceDistanceTextView = (TextView) itemView.findViewById(R.id.place_distance_text_view);
            mPlaceNameTextView = (TextView) itemView.findViewById(R.id.place_name);
            mPlaceAddressTextView = (TextView) itemView.findViewById(R.id.place_address);
            mPlaceOpenStatusTextView = (TextView) itemView.findViewById(R.id.place_open_status);
            mPlaceRating = (MaterialRatingBar) itemView.findViewById(R.id.place_rating);
            mLocationIcon = (ImageView) itemView.findViewById(R.id.location_icon);

            itemView.setOnClickListener(this);
        }

        private void bindViews(int position){
            mItemPosition = position;

            mPlaceNameTextView.setText(mNearByPlaceArrayList.get(mItemPosition).getPlaceName());
            mPlaceNameTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                    "Roboto-Regular.ttf"));
            mPlaceAddressTextView.setText(mNearByPlaceArrayList.get(mItemPosition).getPlaceAddress());
            mPlaceAddressTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                    "Roboto-Regular.ttf"));

            if (mNearByPlaceArrayList.get(mItemPosition).getPlaceOpeningHourStatus().equals("true")) {
                mPlaceOpenStatusTextView.setText(R.string.open_now);
                mPlaceOpenStatusTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                        "Roboto-Regular.ttf"));
            }

            else if (mNearByPlaceArrayList.get(mItemPosition).getPlaceOpeningHourStatus().equals("false")) {
                mPlaceOpenStatusTextView.setText(R.string.closed);
                mPlaceOpenStatusTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                        "Roboto-Regular.ttf"));
            }
            else {
                mPlaceOpenStatusTextView.setText(mNearByPlaceArrayList.get(mItemPosition)
                        .getPlaceOpeningHourStatus());
                mPlaceOpenStatusTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                        "Roboto-Regular.ttf"));
            }
            mPlaceRating.setRating(Float.parseFloat(String.valueOf(mNearByPlaceArrayList.get(mItemPosition)
                    .getPlaceRating())));

            mLocationIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.color_divider));

            Double distance = Utilities.getDistanceFromLatLonInKm(
                    currentlat,
                    currentlog,
                    mNearByPlaceArrayList.get(mItemPosition).getPlaceLatitude(),
                    mNearByPlaceArrayList.get(mItemPosition).getPlaceLongitude()
            );
            Log.d("aywa", currentlat +" "+currentlog);
            Log.d("aywa", mNearByPlaceArrayList.get(mItemPosition).getPlaceLatitude() +" "+mNearByPlaceArrayList.get(mItemPosition).getPlaceLongitude());
            String distanceBetweenTwoPlace = String.valueOf(distance);
            mPlaceDistanceTextView.setText("~ " + distanceBetweenTwoPlace.substring(0, 3) + " Km");

        }

        @Override
        public void onClick(View v) {

            if (Utilities.isNetworkAvailable(mContext)) {
                Toast.makeText(v.getContext(),"Here",Toast.LENGTH_SHORT).show();

//                Intent currentLocationDetailIntent = new Intent(mContext, PlaceDetailActivity.class);
//                currentLocationDetailIntent.putExtra(GoogleApiUrl.LOCATION_ID_EXTRA_TEXT,
//                        mNearByPlaceArrayList.get(mItemPosition).getPlaceId());
//                mContext.startActivity(currentLocationDetailIntent);

            } else
                Snackbar.make(mLocationIcon, R.string.no_connection_string,
                        Snackbar.LENGTH_SHORT).show();
        }






    }


 }
