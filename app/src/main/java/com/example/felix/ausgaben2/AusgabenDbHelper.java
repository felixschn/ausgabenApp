package com.example.felix.ausgaben2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class AusgabenDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = AusgabenDbHelper.class.getSimpleName();

    public static final String DB_NAME = "ausgaben_data.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_AusgabenData = "ausgaben_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PRICE = "price";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_AusgabenData +
                    "(" + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_DATE + " TEXT NOT NULL, " +
                    COLUMN_PRICE+ " INTEGER NOT NULL);";

    public AusgabenDbHelper(Context context){
        super(context, DB_NAME,null,DB_VERSION);
        Log.d(LOG_TAG, "DBHelper hat die Datenbank: "+ getDatabaseName()+ "erzeugt");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Tabelle sollte hier erzeugt werden\n");
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
