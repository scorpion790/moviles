package com.example.sql2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private principal dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new principal(this);
        database = dbHelper.getReadableDatabase();
        admin();
    }

    public void reguistro(View view) {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        String correo, texto, contrasena, contrasena2, id;
        int privilegio;
        id = " ";
        TextView text_name = findViewById(R.id.correo);
        texto = text_name.getText().toString().trim();

        TextView contrasena1 = findViewById(R.id.contrasena);
        contrasena2 = contrasena1.getText().toString().trim();
        boolean encontrado = false;
        boolean erro1 = false;
        boolean admin = false;

        String[] projection = {principal.correo, principal.contrasena, principal.id,principal.privilegios};

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
                correo = cursor.getString(cursor.getColumnIndex(principal.correo));
                contrasena = cursor.getString(cursor.getColumnIndex(principal.contrasena));
                privilegio = cursor.getInt(cursor.getColumnIndex(principal.privilegios));

                if (correo.equals(texto) && contrasena.equals(contrasena2)) {
                    encontrado = true;
                    if (privilegio == 1){
                        admin = true;
                    }
                    id = cursor.getString(cursor.getColumnIndex(principal.id));
                    break;
                } else if (correo.equals(texto)) {
                    erro1 = true;
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        if (encontrado) {
            if (admin){
                Intent i = new Intent(this, MainActivity4.class);
                i.putExtra("clave", id);
                startActivity(i);
                finish();
            }else{
                Intent i = new Intent(this, MainActivity3.class);
                i.putExtra("clave", id);
                startActivity(i);
                finish();
            }

        } else if (erro1) {
            Toast.makeText(this, "contrasena erronea", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "el correo no existe", Toast.LENGTH_SHORT).show();
        }
    }
    public void admin() {
        boolean encontrado = false;

        String correo2 = "admin";

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

        } else {
            d dataSource = new d(this);
            dataSource.open();
            String Correo = "admin";
            String Contrasena = "admin";
            dataSource.admins(Correo, Contrasena);
            dataSource.close();
        }


    }

}




