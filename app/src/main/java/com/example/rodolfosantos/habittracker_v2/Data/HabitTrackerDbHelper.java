package com.example.rodolfosantos.habittracker_v2.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rodolfosantos.habittracker_v2.Data.HabitTrackerContract.HabitEntry;

/**
 * Database helper for Pets app. Manages database creation and version management.
 */

public class HabitTrackerDbHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = HabitTrackerDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "tracker.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitTrackerDbHelper}.
     *
     * @param context of the app
     */
    public HabitTrackerDbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override

    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_HABIT_TRACKER_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_ACTIVITY + " TEXT , "
                + HabitEntry.COLUMN_DATE + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABIT_TRACKER_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}