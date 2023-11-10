package com.example.sql2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class d {

    private SQLiteDatabase database;
    private principal dbHelper;

    public d(Context context) {
        dbHelper = new principal(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertDato(String correo, String contrasena, String nombre, int edad) {
        ContentValues values = new ContentValues();
        values.put(principal.correo, correo);
        values.put(principal.contrasena, contrasena);
        values.put(principal.nombre, nombre);
        values.put(principal.edad, edad);
        values.put(principal.privilegios, 0);
        database.insert(principal.tablanombre, null, values);
    }
    public void admins(String correo, String contrasena) {
        ContentValues values = new ContentValues();
        values.put(principal.correo, correo);
        values.put(principal.contrasena, contrasena);
        values.put(principal.privilegios, 1);
        database.insert(principal.tablanombre, null, values);
    }

    public Cursor getAllDatos() {
        return database.query(principal.tablanombre, null, null, null, null, null, null);
    }
}