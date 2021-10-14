package com.example.imbox.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class DBAyuda extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "lista_perros.db";
    public  static final String TABLE_PERROS =  "t_info_perro";

    public DBAyuda(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_PERROS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "imagen_uno TEXT," +
                "imagen_dos TEXT," +
                "imagen_tres TEXT," +
                "imagen_cuatro TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_PERROS);
        onCreate(db);

    }
}
