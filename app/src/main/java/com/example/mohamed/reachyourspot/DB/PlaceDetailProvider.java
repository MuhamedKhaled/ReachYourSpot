package com.example.mohamed.reachyourspot.DB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.mohamed.reachyourspot.DB.PlaceDetailContract.CONTENT_AUTHORITY;
import static com.example.mohamed.reachyourspot.DB.PlaceDetailContract.PATH_PLACE;
import static com.example.mohamed.reachyourspot.DB.PlaceDetailContract.PlaceDetailEntry.PLACE_TABLE_NAME;
import static com.example.mohamed.reachyourspot.DB.PlaceDetailContract.PlaceDetailEntry._ID;

public class PlaceDetailProvider extends ContentProvider {

    //TAG for the debug purpose
    public static final String TAG = PlaceDetailProvider.class.getSimpleName();

    //Path for retrieve data from database
    private static final int PLACE = 100;
    private static final int PLACE_ID = 101;


    //Database object
    private PlaceDetailDbHelper mPlaceDetailDbHelper;

    //Uri matcher for matching Uri
    public static final UriMatcher uriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(CONTENT_AUTHORITY,PATH_PLACE,PLACE);
        matcher.addURI(CONTENT_AUTHORITY, PATH_PLACE + "/#", PLACE_ID);
        return matcher;
    }



    @Override
    public boolean onCreate() {
        mPlaceDetailDbHelper = new PlaceDetailDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase db = mPlaceDetailDbHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        Cursor returnCursor;


        switch (match) {
            case PLACE:
                returnCursor = db.query(PLACE_TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PLACE_ID:
                selection = _ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                returnCursor = db.query(PLACE_TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        //Match Uri inorder to insert data into database
        int matchPlaceUri = uriMatcher.match(uri);
        switch (matchPlaceUri) {
            case PLACE:
                return insertPlaceDetails(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not support for " + uri);
        }
    }

    private Uri insertPlaceDetails(Uri uri, ContentValues values) {

        //Get writable database for inserting value in database
        SQLiteDatabase placeDetailDatabase = mPlaceDetailDbHelper.getWritableDatabase();

        //Insert new Place detail with given values
        long id = placeDetailDatabase.insert(PLACE_TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(TAG, "Failed to insert data into DB" + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        //return the new uri with the ID append at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        //Get writable database for deleting content from DB
        SQLiteDatabase placeDetailDatabase = mPlaceDetailDbHelper.getWritableDatabase();

        //Track the number of row deleted from place_detail database
        int rowDeleted;

        //Match Uri in order to delete the data
        int matchPlaceUri = uriMatcher.match(uri);
        switch (matchPlaceUri) {
            case PLACE:
                //Delete all the Place details (All Row) from Database
                rowDeleted = placeDetailDatabase.delete(PLACE_TABLE_NAME,
                        selection, selectionArgs);
                break;

            case PLACE_ID:
                //Delete single Place detail from the Database
                selection = _ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowDeleted = placeDetailDatabase.delete(PLACE_TABLE_NAME,
                        selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        //If one or more rows deleted, then notify all listener that the data at
        //Given Url Change
        getContext().getContentResolver().notifyChange(uri, null);

        //Return number deleted row
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        int matchPlaceUri = uriMatcher.match(uri);
        switch (matchPlaceUri) {
            case PLACE:
                return PlaceDetailContract.PlaceDetailEntry.CONTENT_LIST_TYPE;
            case PLACE_ID:
                return PlaceDetailContract.PlaceDetailEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri + " With match " + matchPlaceUri);
        }
    }
}
