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

public class MainActivity6 extends AppCompatActivity {
    private SQLiteDatabase database;
    private principal dbHelper;
    EditText correo1, contrasena1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new principal(this);
        database = dbHelper.getReadableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        correo1 = findViewById(R.id.correo);
        contrasena1 = findViewById(R.id.contraseña);
    }
    public void crearadmin(View view) {
        String correo2 = correo1.getText().toString().trim();
        String contraseña2 = contrasena1.getText().toString().trim();
        boolean encontrado = false;
        if (TextUtils.isEmpty(correo2) || TextUtils.isEmpty(contraseña2)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }


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
                if (correo1.equals(correo2)) {
                    encontrado = true;
                    break;
                }
            } while (cursor.moveToNext());

            cursor.close();
        }
        if (encontrado){
            Toast.makeText(this, "el correo del administrados ya esta registrado", Toast.LENGTH_SHORT).show();
        } else {
            d dataSource = new d(this);
            dataSource.open();
            String Correo = correo1.getText().toString().trim();
            String Contrasena = contrasena1.getText().toString().trim();
            dataSource.admins(Correo, Contrasena);
            dataSource.close();
            Intent i = new Intent(this, MainActivity4.class);
            startActivity(i);
            finish();
        }


        }
}
