package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.ListaVentasAdapter;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VentaController;

import java.util.ArrayList;
import java.util.Collections;

public class MostrarInfoActivity extends AppCompatActivity {

    private RecyclerView rvVentas;
    private ArrayList<Venta> ventas;
    private ListaVentasAdapter listaVentasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info);
        rvVentas = (RecyclerView) findViewById(R.id.rvVentas);
        ventas = VentaController.obtenerVenta();
        if (ventas != null) {
            rvVentas = findViewById(R.id.rvVentas);
            listaVentasAdapter = new ListaVentasAdapter(this, ventas);
            rvVentas.setAdapter(listaVentasAdapter);
            rvVentas.setLayoutManager(new LinearLayoutManager(this));
        }

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int desde = viewHolder.getAdapterPosition();
                int a = target.getAdapterPosition();
                Collections.swap(ventas, desde, a);
                listaVentasAdapter.notifyItemMoved(desde, a);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    mostrarToast("Has encontrado el mensaje secreto");
                    ventas.remove(viewHolder.getAdapterPosition());
                    listaVentasAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                } else if (direction == ItemTouchHelper.RIGHT) {
                    Venta venta = ventas.get(viewHolder.getAdapterPosition());
                    boolean borradoOk = VentaController.borrarVenta(venta);
                    if (borradoOk){
                        mostrarToast("Venta borrada correctamente");
                    }
                    ventas.remove(viewHolder.getAdapterPosition());
                    listaVentasAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            }
        });
        helper.attachToRecyclerView(rvVentas);
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void refrescarRecyclerView(View view) {
        ventas = VentaController.obtenerVenta();
        if(ventas != null){
            listaVentasAdapter.setListaVentas(ventas);
            rvVentas.getAdapter().notifyDataSetChanged();
        }
    }
}