package com.example.sql2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {
    private SQLiteDatabase database;
    private principal dbHelper;

    private String id;
    EditText CORREO, NOMBRE, CONTRASENA, EDAD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
         NOMBRE = findViewById(R.id.nombre);
         CORREO = findViewById(R.id.correo);
         CONTRASENA = findViewById(R.id.contrasena);
         EDAD = findViewById(R.id.edad);



        dbHelper = new principal(this);
        database = dbHelper.getReadableDatabase();

        String[] projection = {
                principal.id,
                principal.correo,
                principal.contrasena,
                principal.nombre,
                principal.edad
        };

        String selection = principal.id + " = ?";
        String[] selectionArgs = {id};

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
            CORREO.setText(correo);
            String contrasena = cursor.getString(cursor.getColumnIndex(principal.contrasena));
            CONTRASENA.setText(contrasena);
            String nombre = cursor.getString(cursor.getColumnIndex(principal.nombre));
            NOMBRE.setText(nombre);
            int edad = cursor.getInt(cursor.getColumnIndex(principal.edad));
            EDAD.setText(String.valueOf(edad));
            cursor.close();
        } else {

        }

        database.close();
    }
    public void atras(View view) {
        Intent i = new Intent(this, MainActivity4.class);
        startActivity(i);
        finish();
    }
    public void actualizardatos(View view) {
        dbHelper = new principal(this);
        database = dbHelper.getWritableDatabase();


        String nuevoNombre = NOMBRE.getText().toString();
        String nuevoCorreo = CORREO.getText().toString();
        String nuevaContrasena = CONTRASENA.getText().toString();
        String nuevaEdad = EDAD.getText().toString();

        ContentValues values = new ContentValues();
        values.put(principal.nombre, nuevoNombre);
        values.put(principal.correo, nuevoCorreo);
        values.put(principal.contrasena, nuevaContrasena);
        values.put(principal.edad, nuevaEdad);

        String selection = principal.id + " = ?";
        String[] selectionArgs = {id};

        int numRowsUpdated = database.update(
                principal.tablanombre,
                values,
                selection,
                selectionArgs
        );

        database.close();
    }
    public void borrar(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = principal.id + " = ?";
        String[] selectionArgs = { String.valueOf(id)};
        db.delete(principal.tablanombre, selection , selectionArgs);

        db.close();


        Intent i = new Intent(this, MainActivity4.class);
        startActivity(i);
        finish();


    }
}
