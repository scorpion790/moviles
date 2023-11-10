package com.example.sql2;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    private ListView userListView;
    private SQLiteDatabase database;
    ArrayList<String> id2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        int contador= 0;
        ArrayList<String> idList = new ArrayList<>();

        userListView = findViewById(R.id.userListView);


        principal dbHelper = new principal(this);
        database = dbHelper.getReadableDatabase();


        String[] projection = {principal.id, principal.nombre, principal.edad, principal.correo, principal.contrasena, principal.privilegios};
        Cursor cursor = database.query(principal.tablanombre, projection, null, null, null, null, null);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        while (cursor.moveToNext()) {
            idList.add(cursor.getString(cursor.getColumnIndex(principal.id)));
            String nombre = cursor.getString(cursor.getColumnIndex(principal.nombre));
            int edad = cursor.getInt(cursor.getColumnIndex(principal.edad));
            String correo = cursor.getString(cursor.getColumnIndex(principal.correo));
            String contrasena = cursor.getString(cursor.getColumnIndex(principal.contrasena));
            int privilegios = cursor.getInt(cursor.getColumnIndex(principal.privilegios));

            String userInfo = "Nombre: " + nombre + "\nEdad: " + edad + "\nCorreo: " + correo + "\nContraseña: " + contrasena + "\nPrivilegios: " + privilegios;
            adapter.add(userInfo);
            contador++;
        }


        userListView.setAdapter(adapter);


        cursor.close();
        database.close();
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion , long id) {
                Intent i = new Intent(MainActivity4.this, MainActivity5.class);
                i.putExtra("id", idList.get(posicion));
                startActivity(i);

            }

        });

    }
    public void cerrarsesion(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
    public void crearadmin(View view) {
        Intent i = new Intent(this, MainActivity6.class);
        startActivity(i);
        finish();
    }
}


