package com.example.ph26471_com1032_asmfinal.SQL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQL_SV extends SQLiteOpenHelper {
    final static String DB_NAME="qlsvx";
    final static int DB_VERSION=1;

    public  SQL_SV(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create_tb_class="CREATE TABLE tb_class ( ID_CLASS INTEGER NOT NULL, NAMECLASS TEXT NOT NULL UNIQUE, PRIMARY KEY(ID_CLASS AUTOINCREMENT))";
        sqLiteDatabase.execSQL(create_tb_class);

        String create_tb_sv="CREATE TABLE tb_sv ( ID INTEGER NOT NULL, NAME TEXT NOT NULL, NGANH TEXT NOT NULL, EMAIL TEXT NOT NULL, PHONE TEXT NOT NULL, ID_CLASS INTEGER NOT NULL, PRIMARY KEY(ID AUTOINCREMENT))";
        sqLiteDatabase.execSQL(create_tb_sv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
