package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VentaController;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ActualizarVentaActivity1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN = "Objeto videojuego sin imagen";
    public static final String EXTRA_IMAGEN_VIDEOJUEGO = "Imagen del objeto videojuego";
    public static final String EXTRA_OBJETO_EMPLEADO = "Objeto empleado";
    public static final String EXTRA_OBJETO_VENTA_SIN_VIDEOJUEGO_NI_EMPLEADO = "Objeto venta sin videojuego ni empleado";
    private Spinner spActualizarVenta = null;
    private ArrayList<Venta> ventas = null;
    public static ArrayAdapter<Venta> ventasAdapter = null;
    private Venta vSeleccionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_venta1);
        spActualizarVenta = (Spinner) findViewById(R.id.spActualizarVenta);
        ventas = VentaController.obtenerVenta();
        spActualizarVenta.setOnItemSelectedListener(this);
        ventasAdapter = new ArrayAdapter<Venta>(this, R.layout.spinner_item, ventas);
        ventasAdapter.setDropDownViewResource(R.layout.spinner_item);
        spActualizarVenta.setAdapter(ventasAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        vSeleccionada = (Venta) spActualizarVenta.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void actualizarVentaSiguiente(View view) {
        Intent intent = new Intent(this, ActualizarVentaActivity2.class);

        //Empleado
        String nombreEmpleado = vSeleccionada.getEmpleado().getNombreEmpleado();
        String apellidosEmpleado = vSeleccionada.getEmpleado().getApellidosEmpleado();
        String domicilioEmpleado = vSeleccionada.getEmpleado().getDomicilioEmpleado();
        String teléfonoEmpleado = vSeleccionada.getEmpleado().getTelefonoEmpleado();
        Empleado empleado = new Empleado(nombreEmpleado, apellidosEmpleado, domicilioEmpleado, teléfonoEmpleado);
        intent.putExtra(EXTRA_OBJETO_EMPLEADO, empleado);

        //Videojuego
        String títuloVideojuego = vSeleccionada.getVideojuego().getTítuloVideojuego();
        int pegiVideojuego = vSeleccionada.getVideojuego().getPegiVideojuego();
        String géneroVideojuego = vSeleccionada.getVideojuego().getGéneroVideojuego();
        Videojuego videojuego = new Videojuego(títuloVideojuego, pegiVideojuego, géneroVideojuego);
        intent.putExtra(EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN, videojuego);
        Bitmap logo = vSeleccionada.getVideojuego().getLogoVideojuego();
        if(logo != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            logo.compress(Bitmap.CompressFormat.PNG, 0, baos);
            byte[] byteArrayLogo = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_VIDEOJUEGO, byteArrayLogo);
        }

        //Venta
        int nVenta = vSeleccionada.getNúmeroVenta();
        Venta venta = new Venta(nVenta);
        intent.putExtra(EXTRA_OBJETO_VENTA_SIN_VIDEOJUEGO_NI_EMPLEADO, venta);
        startActivity(intent);
    }
}