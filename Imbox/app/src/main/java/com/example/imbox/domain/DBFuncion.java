package com.example.imbox.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.imbox.model.Perro;

import java.util.ArrayList;

/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class DBFuncion extends DBAyuda{

    Context context;

    public DBFuncion(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPerro(Perro perro){
        long id = 0;
        try {
            DBAyuda dbAyuda = new DBAyuda(context);
            SQLiteDatabase db = dbAyuda.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombre", perro.getNombreCompleto());

             id =  db.insert( TABLE_PERROS, null, valores);
        }catch (Exception e){
            e.toString();
        }


       return id;
    }
    public long insertarFotos(Perro perro,Integer id){
        try {
            DBAyuda dbAyuda = new DBAyuda(context);
            SQLiteDatabase db = dbAyuda.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("imagen_uno",perro.getUrlImagen_uno());
            valores.put("imagen_dos",perro.getUrlImagen_dos());
            valores.put("imagen_tres",perro.getUrlImagen_tres());
            valores.put("imagen_cuatro",perro.getUrlImagen_cuatro());

            db.update( TABLE_PERROS, valores,"id="+id,null);
        }catch (Exception e){
            e.toString();
        }


        return id;
    }


    public ArrayList<Perro> leerDatos(){
        ArrayList<Perro> lista = new ArrayList<Perro>();

        try {
            DBAyuda dbAyuda = new DBAyuda(context);
            SQLiteDatabase db = dbAyuda.getWritableDatabase();
            Cursor cursosLista = null;

            cursosLista = db.rawQuery("SELECT * FROM " +TABLE_PERROS,null);
            if(cursosLista.moveToFirst()){ // lo pasamos al primer elemento
                do{
                    Integer id = cursosLista.getInt(0);
                    String nombreCompleto = cursosLista.getString(1);
                    String urlImage_uno = cursosLista.getString(2);
                    String urlImage_dos = cursosLista.getString(3);
                    String urlImage_tres = cursosLista.getString(4);
                    String urlImage_cuatro= cursosLista.getString(5);

                    String[] valores = nombreCompleto.split(" ");
                    if(valores.length == 1){
                        Perro p = new Perro(id,valores[0], "",urlImage_uno,urlImage_dos,urlImage_tres,urlImage_cuatro);
                        lista.add(p);
                    }else{
                        Perro p = new Perro(id,valores[0],valores[1],urlImage_uno,urlImage_dos,urlImage_tres,urlImage_cuatro);
                        lista.add(p);
                    }

                }while(cursosLista.moveToNext());

            }
            cursosLista.close();

        }catch (Exception e){
            e.toString();
        }
        return lista;

    }
    public boolean eliminar(Integer id){
        boolean correcto = false;
        DBAyuda dbAyuda = new DBAyuda(context);
        SQLiteDatabase db = dbAyuda.getWritableDatabase();
        try{
            db.execSQL("DELETE FROM " + TABLE_PERROS + " WHERE id = '" + id +"' ");
            correcto = true;
        }catch (Exception e){
            e.toString();
            correcto = false;

        }finally {
            db.close();
        }
        return correcto;
    }
}
