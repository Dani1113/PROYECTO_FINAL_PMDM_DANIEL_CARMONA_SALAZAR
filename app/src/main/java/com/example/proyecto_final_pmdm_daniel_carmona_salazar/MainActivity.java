package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void empezar(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}