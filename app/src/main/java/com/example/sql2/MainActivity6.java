package com.example.sql2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    EditText correo1, contrasena1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        correo1 = findViewById(R.id.correo);
        contrasena1 = findViewById(R.id.contraseña);
    }
    public void crearadmin(View view) {
        d dataSource = new d(this);
        dataSource.open();
        String Correo = correo1.getText().toString().trim();
        String Contrasena = contrasena1.getText().toString().trim();
        dataSource.admins(Correo, Contrasena);
        Cursor cursor = dataSource.getAllDatos();
        dataSource.close();
        }
}
