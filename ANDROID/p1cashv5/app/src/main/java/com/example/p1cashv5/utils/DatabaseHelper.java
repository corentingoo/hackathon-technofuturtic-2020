package com.example.p1cashv5.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CashMachine.db";

    public static final String TABLE_MACHINE = "T_machine";
    public static final String COLUMN_MACHINE_ID = "ID_machine";
    public static final String COLUMN_MACHINE_MAX_CAPACITY = "MaxCapacity";
    public static final String COLUMN_MACHINE_THIS_CAPACITY = "ThisCapacity";

    public static final String TABLE_WITHDRAWAL = "T_withdrawal";
    public static final String COLUMN_WITHDRAWAL_ID = "ID_retrait";
    public static final String COLUMN_WITHDRAWAL_QTY = "Quantity";

    public static final String CREATE_TABLE_MACHINE="CREATE TABLE IF NOT EXISTS " + TABLE_MACHINE + "(\n" +
            COLUMN_MACHINE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            COLUMN_MACHINE_MAX_CAPACITY+" INTEGER NOT NULL,\n" +
            COLUMN_MACHINE_THIS_CAPACITY+" INTEGER NOT NULL\n" +
            ");\n";
    public static final String CREATE_TABLE_WITHDRAWAL="CREATE TABLE IF NOT EXISTS "+ TABLE_WITHDRAWAL + "(\n" +
            COLUMN_WITHDRAWAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            COLUMN_WITHDRAWAL_QTY+" INTEGER NOT NULL,\n" +
            "FK_machine INTEGER NOT NULL,\n" +
            "FOREIGN KEY(FK_machine) REFERENCES T_machine(ID_machine)\n" +
            ")";
    public static final String DROP_TABLE ="DROP_TABLE IF EXISTS";
    public static final String POPULATE_TABLE_MACHINE="INSERT INTO "+TABLE_MACHINE+" ("+COLUMN_MACHINE_MAX_CAPACITY+","+COLUMN_MACHINE_THIS_CAPACITY+")"+
            "VALUES (50, 0),"+
            "(100, 50),"+
            "(150, 50),"+
            "(200, 160)";


    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MACHINE);
        db.execSQL(CREATE_TABLE_WITHDRAWAL);
        db.execSQL(POPULATE_TABLE_MACHINE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + TABLE_MACHINE);
        db.execSQL(DROP_TABLE + TABLE_WITHDRAWAL);
        onCreate(db);
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_MACHINE,null);
        return res;
    }
}
