package com.example.rodolfosantos.habittracker_v2.Data;

import android.provider.BaseColumns;

/**
 * API Contract for the HabitTracker app.
 */

public class HabitTrackerContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitTrackerContract() {
    }

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public final class HabitEntry implements BaseColumns {

        /**
         * Name of database table for HabitTracker
         */
        public final static String TABLE_NAME = "tracker";

        /**
         * Unique ID number for the Activity (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the activity.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_ACTIVITY = "activity";

        /**
         * Date of the activity.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_DATE = "date";
    }
}
