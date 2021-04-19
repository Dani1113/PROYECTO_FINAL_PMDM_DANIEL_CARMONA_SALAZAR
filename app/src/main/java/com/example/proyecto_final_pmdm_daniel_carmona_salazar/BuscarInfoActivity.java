package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.EmpleadoController;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VideojuegoController;

import java.util.ArrayList;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_EMPLEADO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN;

public class BuscarInfoActivity extends AppCompatActivity {

    private TextView edtBuscarTítuloV = null;
    private TextView edtBuscarNombreE = null;
    private ArrayList<Videojuego> videojuegosEncontrados = null;
    private ArrayList<Empleado> empleadosEncontrados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_info);
        edtBuscarNombreE = (TextView) findViewById(R.id.edtBuscarNombreE);
        edtBuscarTítuloV = (TextView) findViewById(R.id.edtBuscarTítuloV);
    }

    public void buscarInformaciónSiguiente(View view) {
        Intent intent = new Intent(this, MostrarInfoActivity.class);

        //Empleado
        String nombre = String.valueOf(edtBuscarNombreE.getText());
        empleadosEncontrados = EmpleadoController.buscarEmpleado(nombre);
        if(empleadosEncontrados != null){
            Toast.makeText(this, "Has encontrado empleados con el título introducido", Toast.LENGTH_SHORT).show();
            intent.putExtra(EXTRA_OBJETO_EMPLEADO, empleadosEncontrados);
        }else{
            Toast.makeText(this, "No has encontrado empleados con el título introducido", Toast.LENGTH_SHORT).show();
        }

        //Videojuego
        String título = String.valueOf(edtBuscarTítuloV.getText());
        videojuegosEncontrados = VideojuegoController.buscarVideojuego(título);
        if(videojuegosEncontrados != null){
            Toast.makeText(this, "Has encontrado videojuegos con el título introducido", Toast.LENGTH_SHORT).show();
            intent.putExtra(EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN, videojuegosEncontrados);
        }else{
            Toast.makeText(this, "No has encontrado videojuegos con el título introducido", Toast.LENGTH_SHORT).show();
        }

        startActivity(intent);
    }
}