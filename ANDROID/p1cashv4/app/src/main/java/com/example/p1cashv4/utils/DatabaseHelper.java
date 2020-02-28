package com.example.p1cashv4.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CashMachine.db";
    public static final String COMM_INT_P_K_AUTO= "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String COMM_CREA_T_NOT_E = "CREATE TABLE IF NOT EXISTS ";


    public static final String TABLE_MACHINE = "T_machine";
    public static final String COLUMN_MACHINE_ID = "ID_machine";
    public static final String COLUMN_MACHINE_PARKING_ID="ID_parking";
    public static final String COLUMN_FK_MACHINE="FK_machine";
    public static final String CONSTRAINT_FK_MACHINE ="FOREIGN KEY("+COLUMN_FK_MACHINE+") REFERENCES "+TABLE_MACHINE+"("+COLUMN_MACHINE_ID+")";

    public static final String TABLE_VALUES = "T_values";
    public static final String COLUMN_VALUES_ID_VALUES="ID_value";
    public static final String COLUMN_VALUES_TYPE="Type_value";
    public static final String COLUMN_VALUES_MAX_CAPACITY = "Max_capacity";
    public static final String COLUMN_VALUES_CURRENT_CAPACITY = "Current_capacity";





    public static final String CREATE_TABLE_MACHINES=COMM_CREA_T_NOT_E + TABLE_MACHINE + "(\n" +
            COLUMN_MACHINE_ID + COMM_INT_P_K_AUTO + ",\n" +
            COLUMN_MACHINE_PARKING_ID +" INTEGER NOT NULL\n" +
            ");\n";

    public static final String CREATE_TABLE_VALUES=COMM_CREA_T_NOT_E + TABLE_VALUES + "(\n" +
            COLUMN_VALUES_ID_VALUES + COMM_INT_P_K_AUTO +",\n" +
            COLUMN_VALUES_TYPE +" INTEGER NOT NULL\n" +


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
