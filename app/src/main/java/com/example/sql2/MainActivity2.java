
package com.example.sql2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private SQLiteDatabase database;
    private principal dbHelper;
    EditText correo2, contrasena2, nombre2, edad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new principal(this);
        database = dbHelper.getReadableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        correo2 = findViewById(R.id.correo1);
        contrasena2 = findViewById(R.id.contrasena1);
        nombre2 = findViewById(R.id.nombre1);
        edad2 = findViewById(R.id.edad1);
    }

    public void insertardatos(View view) {
        String Correo = correo2.getText().toString().trim();
        String Contrasena = contrasena2.getText().toString().trim();
        String Nombre = nombre2.getText().toString().trim();
        String edadStr = edad2.getText().toString().trim();
        if (TextUtils.isEmpty(Correo) || TextUtils.isEmpty(Contrasena) || TextUtils.isEmpty(Nombre) || TextUtils.isEmpty(edadStr)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

            String texto = correo2.getText().toString().trim();
            boolean encontrado = false;
            String[] projection = {principal.correo};

            Cursor cursor = database.query(
                    principal.tablanombre,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String correo1 = cursor.getString(cursor.getColumnIndex(principal.correo));
                    if (correo1.equals(texto)) {
                        encontrado = true;
                        break;
                    }
                } while (cursor.moveToNext());

                cursor.close();
            }
            if (encontrado){
                Toast.makeText(this, "El usuario ya está registrado", Toast.LENGTH_SHORT).show();
            } else {
                d dataSource = new d(this);
                dataSource.open();
                    if (!TextUtils.isEmpty(edadStr)) {
                        int Edad = Integer.parseInt(edadStr);
                        dataSource.insertDato(Correo, Contrasena, Nombre, Edad);
                        Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show();
                        Cursor cursor2 = dataSource.getAllDatos();
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                dataSource.close();
            }

    }

    public void regresar(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

