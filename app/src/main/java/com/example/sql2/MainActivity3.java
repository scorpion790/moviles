package com.example.sql2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity3 extends AppCompatActivity {
    private SQLiteDatabase database;
    private principal dbHelper;

    private String datoRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        dbHelper = new principal(this);
        database = dbHelper.getReadableDatabase();
        Intent intent = getIntent();
        datoRecibido = intent.getStringExtra("clave");
        mostrarDatosPorID(datoRecibido);
    }
    public void mostrarDatosPorID(String id) {
        TextView texto = findViewById(R.id.nombre);
        String idText = (id);


        String[] projection = {
                principal.id,
                principal.correo,
                principal.contrasena,
                principal.nombre,
                principal.edad
        };


        String selection = principal.id + " = ?";
        String[] selectionArgs = {idText};

        Cursor cursor = database.query(
                principal.tablanombre,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String correo = cursor.getString(cursor.getColumnIndex(principal.correo));
            String contrasena = cursor.getString(cursor.getColumnIndex(principal.contrasena));
            String nombre = cursor.getString(cursor.getColumnIndex(principal.nombre));
            texto.setText("Hola " + nombre );
            int edad = cursor.getInt(cursor.getColumnIndex(principal.edad));

            cursor.close();
        } else {

        }

    }
        public void borrar(View view) {
            Intent intent = getIntent();
            String datoRecibido = intent.getStringExtra("clave");
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String selection = principal.id + " = ?";
            String[] selectionArgs = { String.valueOf(datoRecibido)};
            db.delete(principal.tablanombre, selection , selectionArgs);

            db.close();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();


        }

    }