package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void insertarVenta(View view) {
        Intent intent = new Intent(this, InsertarVentaActivity.class);
        startActivity(intent);
    }

    public void borrarVenta(View view) {
        Intent intent = new Intent(this, BorrarVentaActivity.class);
        startActivity(intent);
    }

    public void actualizarVenta(View view) {
        Intent intent = new Intent(this, ActualizarVentaActivity1.class);
        startActivity(intent);
    }

    public void mostrarInformación(View view) {
        Intent intent = new Intent(this, MostrarInfoVentaActivity.class);
        startActivity(intent);
    }

    public void buscarInformación(View view) {
        Intent intent = new Intent(this, BuscarInfoActivity.class);
        startActivity(intent);
    }
}