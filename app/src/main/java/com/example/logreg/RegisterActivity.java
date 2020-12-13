package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button regisztracio, vissza;
    EditText email, felhnev, jelszo, nev;
    dbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        adatLekerdez();

        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
        regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatLekerdez();
            }
        });
    }

    private void adatLekerdez() {

        String Email = email.getText().toString();
        String felhNev = felhnev.getText().toString();
        String Jelszo = jelszo.getText().toString();
        String Nev = nev.getText().toString();



        if (Nev.isEmpty() || Jelszo.isEmpty() || Email.isEmpty() || felhNev.isEmpty()){
            Toast.makeText(this, "Nem lehet üres mező", Toast.LENGTH_SHORT).show();
            return;
        }else{
            db.adatrogzites(Email,felhNev, Jelszo, Nev);
            Toast.makeText(this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
            Intent vissza = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(vissza);
            finish();
            return;
        }

    }


    private void init() {
        vissza = findViewById(R.id.button2);
        regisztracio = findViewById(R.id.button);
        email = findViewById(R.id.editText);
        felhnev = findViewById(R.id.editText2);
        jelszo = findViewById(R.id.editText3);
        nev = findViewById(R.id.editText4);
        db = new dbHelper(RegisterActivity.this);
    }

}