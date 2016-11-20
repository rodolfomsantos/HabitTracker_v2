package com.example.rodolfosantos.habittracker_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.rodolfosantos.habittracker_v2.Data.HabitTrackerContract.HabitEntry;
import com.example.rodolfosantos.habittracker_v2.Data.HabitTrackerDbHelper;

import static com.example.rodolfosantos.habittracker_v2.R.id.data_button;


/**
 * Displays list of Activity's that were entered and stored in the app.
 */

public class MainActivity extends AppCompatActivity {

    //Variable for the user inputs search button
    private Button userInput;

    private HabitTrackerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the Button object
        userInput = (Button) findViewById(data_button);

        //Search Button action
        userInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertActivity();
            }
        });

        mDbHelper = new HabitTrackerDbHelper(this);
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the tracker's database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        HabitTrackerDbHelper mDbHelper = new HabitTrackerDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_ACTIVITY,
                HabitEntry.COLUMN_DATE,
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME, // The table to query
                projection,          // The columns to return
                null,                // The columns for the WHERE clause
                null,                // The values for the WHERE clause
                null,                // Don't group the rows
                null,                // Don't filter by row groups
                null);               // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_activity);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The tracker table contains <number of rows in Cursor> activity's.
            // _id - activity - date
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.

            displayView.setText("The tracker table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_ACTIVITY + " - " +
                    HabitEntry.COLUMN_DATE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int activityColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_ACTIVITY);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentActivity = cursor.getString(activityColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentActivity + " - " +
                        currentDate + " - "));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertActivity() {

        //Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Activity's attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_ACTIVITY, "Programming");
        values.put(HabitEntry.COLUMN_DATE, "20-11-2016");

        // Insert a new row for the Activity in the database, returning the ID of that new row.
        // The first argument for db.insert() is the HabitTracker table name.
        // The second argument provides the name of a column in which the framework
        // can insert Null in the event that ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for the Activity.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        Log.v("CatalogActivity", "NewRowId" + newRowId);

    }


}
