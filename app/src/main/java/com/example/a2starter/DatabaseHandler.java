package com.example.a2starter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Scanner;
import static android.content.Context.MODE_PRIVATE;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Flavours";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String PRICE = "PRICE";
    public static final String QUANTITY = "QUANTITY";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "IceCreamShop.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " ( ID  INTEGER PRIMARY KEY," +
                    NAME + " TEXT," +
                    DESCRIPTION + " TEXT ,"
                    + PRICE +" INTERGER , "
                    + QUANTITY + " INTEGER )";

    private SQLiteDatabase database;


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Flavours Moose_Tracksflavours = new Flavours("Moose Tracks","Vanilla ice cream with peanut butter chunks.",10,4);
        Flavours Orange_Creamsicleflavours = new Flavours("Orange Creamsicle","A childhood favourite! Orange sherbert mixed with french vanilla ice cream.",2,3);


        dataInsert(Moose_Tracksflavours);
        dataInsert(Orange_Creamsicleflavours);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void dataInsert(Flavours flavours)
    {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,flavours.getName());
        contentValues.put(DESCRIPTION,flavours.getDescription());
        contentValues.put(PRICE,flavours.getPrice());
        contentValues.put(QUANTITY,flavours.getQuantity());

        database.insert(TABLE_NAME,null,contentValues);

        database.close();
    }

    public Flavours ShowData()
    {
        database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME  + " = 'Moose Tracks' ";
        Cursor cursor = database.rawQuery(query, null);

        Flavours flavours = new Flavours();
        cursor.moveToFirst();

        flavours.setName(cursor.getString(1));
        flavours.setDescription(cursor.getString(2));
        flavours.setPrice(cursor.getInt(3));
        flavours.setQuantity(cursor.getInt(4));

        return flavours;
    }

    public void UpdateData(int quantity , String name){
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUANTITY, quantity );
        database.update(TABLE_NAME, contentValues, NAME + " = ?", new String[]{name});
    }
}
