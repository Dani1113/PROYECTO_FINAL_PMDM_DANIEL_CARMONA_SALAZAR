package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class MostrarInfoActivity extends AppCompatActivity {

    private Button btVideojuegos;
    private Button btEmpleados;

    private String subtítulo = "";

    public static final String EXTRA_SUBTÍTULO = "Subtítulo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info);

        btVideojuegos = (Button) findViewById(R.id.btVideojuegos);
        btEmpleados = (Button) findViewById(R.id.btEmpleados);

        btVideojuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setSubtitle(R.string.listaVideojuegos);
                subtítulo = getString(R.string.listaVideojuegos);
                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_ir_a_empleados);
            }
        });

        btEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportActionBar().setSubtitle(R.string.listaEmpleados);
                subtítulo = getString(R.string.listaEmpleados);
                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_ir_a_videojuegos);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onRestoreInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}