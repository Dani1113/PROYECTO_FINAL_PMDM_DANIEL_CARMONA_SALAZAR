package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VentaController;

import java.util.ArrayList;

public class BorrarVentaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spVenta = null;
    ArrayList<Venta> ventas = null;
    ArrayAdapter<Venta> ventasAdapter = null;
    Venta vSeleccionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_venta);
        spVenta = (Spinner) findViewById(R.id.spVenta);
        ventas = VentaController.obtenerVenta();
        for(Venta v: ventas) {
            System.out.println(v.toString());
        }
        spVenta.setOnItemSelectedListener(this);
        ventasAdapter = new ArrayAdapter<Venta>(this, R.layout.support_simple_spinner_dropdown_item, ventas);
        spVenta.setAdapter(ventasAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        vSeleccionada = (Venta) spVenta.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void borrarVenta(View view) {
        if(vSeleccionada == null){
            Toast.makeText(this, "Debes seleccionar una provincia para borrarla", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder alertaBorrar = new AlertDialog.Builder(this);
        alertaBorrar.setTitle("¿Desea borrar la venta?");
        alertaBorrar.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean borradoOK = VentaController.borrarVenta(vSeleccionada);
                if(borradoOK) {
                    mostrarToast("Venta borrada correctamente");
                    ventasAdapter.remove(vSeleccionada);
                }
                else{
                    mostrarToast("Error al borrar la venta");
                }
            }
        });
        alertaBorrar.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("Borrado de la venta cancelado");
            }
        });
        alertaBorrar.show();
    }

    public void mostrarToast (String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }


}