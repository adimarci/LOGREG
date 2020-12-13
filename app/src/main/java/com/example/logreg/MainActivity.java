package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bejelentkezes, regisztracio;
    EditText felhnev, jelszo;
    dbHelper db;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(RegisterActivity);
                finish();
            }
        });
        bejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatlekerdezes();
            }
        });
    }

    private void adatlekerdezes() {
        String nev = felhnev.getText().toString();
        String Jelszo = jelszo.getText().toString();

        Cursor adatok = db.adatLekerdezes();
        if (nev.isEmpty() || Jelszo.isEmpty()){
            Toast.makeText(this, "Nem lehet üres mező!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            editor.putString("nev",nev);
            editor.putString("jelszo",Jelszo);
            editor.commit();
            Intent LoggedInActivity = new Intent(MainActivity.this, LoggedInActivity.class);
            startActivity(LoggedInActivity);
            finish();
        }

    }

    private void init() {
        bejelentkezes = findViewById(R.id.button);
        regisztracio = findViewById(R.id.button2);
        felhnev = findViewById(R.id.editText);
        jelszo = findViewById(R.id.editText2);
        db = new dbHelper(MainActivity.this);
        sharedPref = getSharedPreferences("adatok", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }
}