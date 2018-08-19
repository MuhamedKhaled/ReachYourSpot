package com.example.mohamed.reachyourspot.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mohamed.reachyourspot.DB.PlaceDetailContract.PlaceDetailEntry;

public class PlaceDetailDbHelper extends SQLiteOpenHelper {

    //TAG for the class
    public static final String TAG = PlaceDetailDbHelper.class.getSimpleName();

    //Place Database Name and Version
    public static final String DATABASE_NAME = "place_detail.db";
    public static final int DATABASE_VERSION = 1;


    public PlaceDetailDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Query for Place Detail
        String SQL_CREATE_PLACE_DETAIL_QUERY =
                "CREATE TABLE " + PlaceDetailContract.PlaceDetailEntry.PLACE_TABLE_NAME + "(" +
                        PlaceDetailEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PlaceDetailEntry.COLUMN_PLACE_ID + " TEXT, " +
                        PlaceDetailEntry.COLUMN_PLACE_LATITUDE + " REAL, " +
                        PlaceDetailEntry.COLUMN_PLACE_LONGITUDE + " REAL, " +
                        PlaceDetailEntry.COLUMN_PLACE_NAME + " TEXT, " +
                        PlaceDetailEntry.COLUMN_PLACE_OPENING_HOUR_STATUS + " TEXT, " +
                        PlaceDetailEntry.COLUMN_PLACE_RATING + " REAL ," +
                        PlaceDetailEntry.COLUMN_PLACE_ADDRESS + " TEXT, " +
                        PlaceDetailEntry.COLUMN_PLACE_PHONE_NUMBER + " TEXT, " +
                        PlaceDetailEntry.COLUMN_PLACE_WEBSITE + " TEXT, " +
                        PlaceDetailEntry.COLUMN_PLACE_SHARE_LINK + " TEXT" + ")";
        db.execSQL(SQL_CREATE_PLACE_DETAIL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_PLACE_DETAIL_QUERY =
                "DELETE FROM " + PlaceDetailEntry.PLACE_TABLE_NAME;
        db.execSQL(SQL_DELETE_PLACE_DETAIL_QUERY);
        onCreate(db);
    }
}
