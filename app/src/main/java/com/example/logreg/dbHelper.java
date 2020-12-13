package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class dbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="felhasznalok.db";
    public static final int DB_VERSION= 1;
    public static final String FELHASZNALO_TABLE= "felhasznalo";

    public static final String COL_ID="id";
    public static final String COL_NEV="felhnev";
    public static final String COL_TELNEV="teljesnev";
    public static final String COL_EMAIL="email";
    public static final String COL_JELSZO="jelszo";
    public dbHelper(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE IF NOT EXISTS "+FELHASZNALO_TABLE+"(" +
                COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NEV+ " VARCHAR(64) NOT NULL, " +
                COL_EMAIL+ " VARCHAR(64) NOT NULL, " +
                COL_JELSZO+ " VARCHAR(64) NOT NULL, " +
                COL_TELNEV+ " VARCHAR(64) NOT NULL " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String sql ="DROP TABLE IF EXISTS "+FELHASZNALO_TABLE;
    db.execSQL(sql);
    onCreate(db);
    }

    public boolean adatrogzites(String email, String felhasznalonev, String jelszo,String teljesnev){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_NEV, felhasznalonev);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELNEV, teljesnev);
        return db.insert(FELHASZNALO_TABLE,null,values) != -1;
    }
    public Cursor adatLekerdezes(){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+FELHASZNALO_TABLE,null);
    }
}
