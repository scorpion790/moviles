package com.example.sql2;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.view.View;

public class principal extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MiBaseDeDatos.db";
    private static final int DATABASE_VERSION = 1;
    public static final String tablanombre  = "MiTabla";

    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String contrasena = "contrasena";
    public static final String privilegios = "privilegios";


    public principal(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + tablanombre + " (" +
                id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                privilegios + " INTEGER, " +
                correo + " TEXT, " +
                contrasena + " TEXT, " +
                nombre + " TEXT, " +
                edad + " INTEGER)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablanombre);
        onCreate(db);
    }

}
