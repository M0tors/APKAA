package com.ostaszewski.damian.aplikacja3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBTelefon extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "telefonManager";

    // Contacts table name
    private static final String TABLE_KSIAZKA = "ksiazka";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_IMIE = "imie";
    private static final String KEY_NAZWISKO = "nazwisko";
    private static final String KEY_NUMER = "numer";

    public DBTelefon(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_KSIAZKA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMIE + " TEXT,"
                + KEY_NAZWISKO + " TEXT," + KEY_NUMER + " TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KSIAZKA);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addTelefon(Ctelefon contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMIE, contact.getImie()); // Contact Name
        values.put(KEY_NAZWISKO, contact.getNazwisko()); // Contact Phone
        values.put(KEY_NUMER, contact.getNumer());

        // Inserting Row
        db.insert(TABLE_KSIAZKA, null, values);
//        db.close(); // Closing database connection
    }



    // Getting single contact
    Ctelefon getTelefon(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KSIAZKA, new String[] { KEY_ID,
                        KEY_IMIE, KEY_NAZWISKO, KEY_NUMER, }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Ctelefon contact = new Ctelefon(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<Ctelefon> getAllTelefon() {
        List<Ctelefon> contactList = new ArrayList<Ctelefon>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KSIAZKA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ctelefon contact = new Ctelefon();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setNazwisko(cursor.getString(2));
                contact.setNumer(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }



    // Updating single contact
    public int updateContact(Ctelefon contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMIE, contact.getImie());
        values.put(KEY_NAZWISKO, contact.getNazwisko());
        values.put(KEY_NUMER, contact.getNumer());

        // updating row
        return db.update(TABLE_KSIAZKA, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteTelefon(Ctelefon contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KSIAZKA, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_KSIAZKA, null, null);

    }

    // Getting contacts Count
    public int getAlarmCount() {
        String countQuery = "SELECT  * FROM " + TABLE_KSIAZKA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int getLastAlarmID() {
        int lastId = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryty = "SELECT "+ KEY_ID +" FROM "+ TABLE_KSIAZKA + " order by " + KEY_ID + " DESC limit 1";
        Cursor c = db.rawQuery(queryty, null);
        if (c != null && c.moveToFirst()) {
            lastId = (int) c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
            c.close();
        }
        return lastId;
    }

}