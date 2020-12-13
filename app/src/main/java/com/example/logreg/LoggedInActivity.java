package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    Button kijelentkezes;
    TextView nevtxt;
    dbHelper db;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        String nev = sharedPref.getString("nev","");
        nevtxt.setText("Üdvözöllek: "+nev);
        kijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

    private void init() {
        kijelentkezes = findViewById(R.id.button);
        nevtxt = findViewById(R.id.editText);
        db = new dbHelper(LoggedInActivity.this);
        sharedPref = getSharedPreferences("adatok", Context.MODE_PRIVATE);
    }
}