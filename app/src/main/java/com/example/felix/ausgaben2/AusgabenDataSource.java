package com.example.felix.ausgaben2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AusgabenDataSource {

    private static final String LOG_TAG = AusgabenDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private AusgabenDbHelper ausgabenDbHelper;

    private String[] columns = {
            AusgabenDbHelper.COLUMN_ID,
            AusgabenDbHelper.COLUMN_NAME,
            AusgabenDbHelper.COLUMN_DATE,
            AusgabenDbHelper.COLUMN_PRICE,
    };

    private int SIZE;

    public AusgabenDataSource(Context context) {
        Log.d(LOG_TAG, "DataSource erzeugt nun den DBHelper");
        ausgabenDbHelper = new AusgabenDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt");
        database = ausgabenDbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank " + database.getPath());
    }

    public void close() {
        ausgabenDbHelper.close();
        Log.d(LOG_TAG, "Datebank mit Hilfe des DbHelpers geschlossen");
    }

    public Ausgaben createAusgaben(String name, double price, Date date) {
        ContentValues values = new ContentValues();
        values.put(AusgabenDbHelper.COLUMN_NAME, name);

        values.put(AusgabenDbHelper.COLUMN_DATE, date.toString());
        values.put(AusgabenDbHelper.COLUMN_PRICE, price);


        long insertId = database.insert(AusgabenDbHelper.TABLE_AusgabenData, null, values);

        Cursor cursor = database.query(AusgabenDbHelper.TABLE_AusgabenData, columns, AusgabenDbHelper.COLUMN_ID + "=" + insertId, null, null, null, null);

        cursor.moveToFirst();
        Ausgaben ausgaben = cursorToAusgaben(cursor);


        //cursor.close();

        return ausgaben;
    }

    private Ausgaben cursorToAusgaben(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(AusgabenDbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(AusgabenDbHelper.COLUMN_NAME);
        int idDate = cursor.getColumnIndex(AusgabenDbHelper.COLUMN_DATE);
        int idPrice = cursor.getColumnIndex(AusgabenDbHelper.COLUMN_PRICE);

        String name = cursor.getString(idName);
        String date = cursor.getString(idDate);
        double price = cursor.getDouble(idPrice);
        long id = cursor.getLong(idIndex);

        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.GERMAN);
        Date giveMeDate = null;
        try {
            giveMeDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TODO ID muss noch mit übergeben werden
        Ausgaben ausgaben = new Ausgaben(name, price, new Date());

        return ausgaben;
    }

    public List<Ausgaben> getAllAusgaben() {
        List<Ausgaben> ausgabenListNeu = new ArrayList<>();

        Cursor cursor = database.query(AusgabenDbHelper.TABLE_AusgabenData, columns, null, null, null, null, null);

        cursor.moveToFirst();

        Ausgaben ausgaben;

        while (!cursor.isAfterLast()) {
            ausgaben = cursorToAusgaben(cursor);
            ausgabenListNeu.add(ausgaben);
        }
        cursor.close();

        SIZE = ausgabenListNeu.size();

        return ausgabenListNeu;
    }

    public int getSIZE(){
        return SIZE;
    }

}
