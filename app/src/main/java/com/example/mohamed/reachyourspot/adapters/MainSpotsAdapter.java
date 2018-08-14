package com.example.mohamed.reachyourspot.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohamed.reachyourspot.R;
import com.example.mohamed.reachyourspot.activities.MapsActivity;
import com.example.mohamed.reachyourspot.utils.Constants;
import com.example.mohamed.reachyourspot.utils.PlaceDetailProvider;
import com.example.mohamed.reachyourspot.utils.Utilities;

import java.util.Random;

public class MainSpotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Context of the activity
    private Context mContext;
    private String[] mPlacesListTag;


    public MainSpotsAdapter(Context context, String[] placesListTag) {
        mContext = context;
        mPlacesListTag = placesListTag;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainSpotsHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.home_screen_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MainSpotsHolder) holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return mPlacesListTag.length;
    }

    private class MainSpotsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mPlaceTextView;
        private ImageView mPlaceImageView;
        private int mItemPosition;

        private MainSpotsHolder(View itemView) {
            super(itemView);
            mPlaceTextView = (TextView) itemView.findViewById(R.id.place_text_view);
            mPlaceImageView = (ImageView) itemView.findViewById(R.id.place_icon);

            mPlaceImageView.setOnClickListener(this);
        }

        private void bindView(int position) {
            mPlaceTextView.setText(mPlacesListTag[position]);

            mPlaceImageView.setImageDrawable(ContextCompat.getDrawable(mContext,
                    PlaceDetailProvider.popularPlaceTagIcon[position]));

            mPlaceTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                    "Roboto-Regular.ttf"));
            int[] colorArray = PlaceDetailProvider.accentColor;

            int randomColor = colorArray[new Random().nextInt(colorArray.length)];

            ((GradientDrawable) mPlaceImageView.getBackground())
                    .setColor(ContextCompat.getColor(mContext, randomColor));

            mItemPosition = position;
        }

        @Override
        public void onClick(View v) {

            if (Utilities.isNetworkAvailable(mContext)) {
                String locationTag = mPlacesListTag[mItemPosition];

                if (locationTag.equals("Bus Stand"))
                    locationTag = "bus_station";
                else if (locationTag.equals("Government Office"))
                    locationTag = "local_government_office";
                else if (locationTag.equals("Railway Station"))
                    locationTag = "train_station";
                else if (locationTag.equals("Hotel"))
                    locationTag = "restaurant";
                else if (locationTag.equals("Police Station"))
                    locationTag = "police";
                else
                    locationTag = locationTag.replace(' ', '_').toLowerCase();


                // Intent to start Maps activity with locationTag as extra data.

                Intent intent = new Intent(mContext, MapsActivity.class);
                intent.putExtra(Constants.LOCATION_NAME_EXTRA_TEXT,PlaceDetailProvider.popularPlaceTagName[mItemPosition]);
                intent.putExtra(Constants.LOCATION_TYPE_EXTRA_TEXT,locationTag);
                mContext.startActivity(intent);

            }else {
                Snackbar.make(mPlaceImageView, R.string.no_connection_string,
                        Snackbar.LENGTH_SHORT).show();
            }

        }


    }



}
