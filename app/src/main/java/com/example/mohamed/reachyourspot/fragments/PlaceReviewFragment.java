package com.example.mohamed.reachyourspot.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.reachyourspot.R;
import com.example.mohamed.reachyourspot.adapters.PlaceReviewAdapter;
import com.example.mohamed.reachyourspot.models.PlaceUserRating;
import com.example.mohamed.reachyourspot.utils.Constants;

import java.util.ArrayList;

public class PlaceReviewFragment extends Fragment {
    /**
     * all references
     */
    private ArrayList<PlaceUserRating> mPlaceUserRatingArrayList = new ArrayList<>();
    private PlaceReviewAdapter mPlaceUserRatingAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_place_review, container, false);
    mPlaceUserRatingArrayList = getArguments()
                .getParcelableArrayList(Constants.CURRENT_LOCATION_USER_RATING_KEY);
    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        if (mPlaceUserRatingArrayList.size() == 0) {
        rootView.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    } else {
        rootView.findViewById(R.id.empty_view).setVisibility(View.GONE);
        mPlaceUserRatingAdapter = new PlaceReviewAdapter(getActivity(), mPlaceUserRatingArrayList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerView.setAdapter(mPlaceUserRatingAdapter);
    }
        return rootView;
    }

}
