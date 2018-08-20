package com.example.mohamed.reachyourspot.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.reachyourspot.DB.PlaceDetailContract;
import com.example.mohamed.reachyourspot.R;
import com.example.mohamed.reachyourspot.activities.PlaceDetailActivity;
import com.example.mohamed.reachyourspot.utils.Constants;
import com.example.mohamed.reachyourspot.utils.Utilities;
import com.example.mohamed.reachyourspot.DB.PlaceDetailContract.PlaceDetailEntry;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class FavouritePlaceCursorAdapter extends CursorRecyclerViewAdapter{
    public FavouritePlaceCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavouritePlaceCursorAdapterViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.place_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        ((FavouritePlaceCursorAdapterViewHolder) viewHolder).bindView(cursor);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    private class FavouritePlaceCursorAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Cursor mCurrentDataCursor;
        //reference of the views
        private TextView mPlaceNameTextView;
        private TextView mPlaceAddressTextView;
        private TextView mPlaceOpenStatusTextView;
        private MaterialRatingBar mPlaceRating;
        private ImageView mLocationIcon;

        private Double lat,lng;

        public void setLatAndLng(Double lat,Double lng) {
            this.lat = lat;
            this.lng = lng;

        }

        private FavouritePlaceCursorAdapterViewHolder(View itemView) {
            super(itemView);

            mPlaceNameTextView = (TextView) itemView.findViewById(R.id.place_name);
            mPlaceAddressTextView = (TextView) itemView.findViewById(R.id.place_address);
            mPlaceOpenStatusTextView = (TextView) itemView.findViewById(R.id.place_open_status);
            mPlaceRating = (MaterialRatingBar) itemView.findViewById(R.id.place_rating);
            mLocationIcon = (ImageView) itemView.findViewById(R.id.location_icon);

            itemView.setOnClickListener(this);
        }

        private void bindView(Cursor currentPlaceDataCursor) {

            mCurrentDataCursor = currentPlaceDataCursor;

            mPlaceNameTextView.setText(currentPlaceDataCursor.getString(
                    currentPlaceDataCursor.getColumnIndex(PlaceDetailEntry.COLUMN_PLACE_NAME)));
            mPlaceNameTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                    "Roboto-Regular.ttf"));

            mPlaceAddressTextView.setText(currentPlaceDataCursor.getString(
                    currentPlaceDataCursor.getColumnIndex(PlaceDetailEntry.COLUMN_PLACE_ADDRESS)));
            mPlaceAddressTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                    "Roboto-Regular.ttf"));

            if (currentPlaceDataCursor.getString(currentPlaceDataCursor
                    .getColumnIndex(PlaceDetailEntry.COLUMN_PLACE_OPENING_HOUR_STATUS)).equals("true")) {
                mPlaceOpenStatusTextView.setText(R.string.open_now);
                mPlaceOpenStatusTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                        "Roboto-Regular.ttf"));
            } else if (currentPlaceDataCursor.getString(currentPlaceDataCursor
                    .getColumnIndex(PlaceDetailEntry.COLUMN_PLACE_OPENING_HOUR_STATUS)).equals("false")) {
                mPlaceOpenStatusTextView.setText(R.string.closed);
                mPlaceOpenStatusTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                        "Roboto-Regular.ttf"));
            } else {
                mPlaceOpenStatusTextView.setText(currentPlaceDataCursor.getString(currentPlaceDataCursor
                        .getColumnIndex(PlaceDetailEntry.COLUMN_PLACE_OPENING_HOUR_STATUS)));
                mPlaceOpenStatusTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                        "Roboto-Regular.ttf"));
            }
            mPlaceRating.setRating(Float.parseFloat(String.valueOf(currentPlaceDataCursor
                    .getDouble(currentPlaceDataCursor
                            .getColumnIndex(PlaceDetailContract.PlaceDetailEntry.COLUMN_PLACE_RATING)))));

            mLocationIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.color_divider));
        }

        @Override
        public void onClick(View v) {
            if (Utilities.isNetworkAvailableWithAsyncTask(v.getContext())) {
                Intent currentLocationDetailIntent = new Intent(mContext, PlaceDetailActivity.class);
                currentLocationDetailIntent.putExtra(Constants.LOCATION_ID_EXTRA_TEXT,
                        mCurrentDataCursor.getString(
                                mCurrentDataCursor.getColumnIndex(PlaceDetailContract.PlaceDetailEntry.COLUMN_PLACE_ID)));
                mContext.startActivity(currentLocationDetailIntent);

            } else
                Snackbar.make(mLocationIcon, R.string.no_connection_string,
                        Snackbar.LENGTH_SHORT).show();
        }


    }


}
