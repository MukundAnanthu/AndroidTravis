package com.example.admin.sixteen;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by admin on 09-01-2016.
 * Responsible for managing the database
 */
public class WatchOrNot {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";
    private static final String DATABASE_NAME = "HotOrNotdb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;
    private DbHelper ourHelper;
    private SQLiteDatabase ourDatabase;
    private Context ourContext;

    public long createEntry(String enteredName, String enteredRating) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,enteredName);
        cv.put(KEY_HOTNESS,enteredRating);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ("+
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_NAME + " TEXT NOT NULL," +
                    KEY_HOTNESS + " TEXT NOT NULL);"

            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(
                    "DROP TABLE IF EXISTS "+ DATABASE_TABLE
            );
            onCreate(db);
        }
    }

    public WatchOrNot (Context c) {
        ourContext = c;

    }

    public WatchOrNot open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close( ) {
        ourHelper.close();
    }

}
