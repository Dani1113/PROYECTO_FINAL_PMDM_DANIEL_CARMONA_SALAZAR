package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class MostrarInfoActivity extends AppCompatActivity {

    private String subtítulo = "";

    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemIrAInicio:
                Intent intent = new Intent(MostrarInfoActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemMostrarEmpleados:
                getSupportActionBar().setSubtitle(R.string.listaEmpleados);
                subtítulo = getString(R.string.listaEmpleados);
                navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                findNavController(navHostFragment).navigate(R.id.action_ir_a_empleados);
                return true;
            case R.id.itemMostrarVideojuegos:
                getSupportActionBar().setSubtitle(R.string.listaVideojuegos);
                subtítulo = getString(R.string.listaVideojuegos);
                navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                findNavController(navHostFragment).navigate(R.id.action_ir_a_videojuegos);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}