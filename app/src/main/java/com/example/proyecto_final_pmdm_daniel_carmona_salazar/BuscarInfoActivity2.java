package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.EmpleadoRecyclerView.ListaEmpleadosAdapter;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.VideojuegoRecyclerView.ListaVideojuegosAdapter;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.EmpleadoController;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VideojuegoController;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_EMPLEADO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN;

public class BuscarInfoActivity2 extends AppCompatActivity {

    private RecyclerView rvVideojuegos = null;
    private ArrayList<Videojuego> videojuegos = null;
    private ListaVideojuegosAdapter listaVideojuegosAdapter = null;
    private RecyclerView rvEmpleados = null;
    private ArrayList<Empleado> empleados = null;
    private ListaEmpleadosAdapter listaEmpleadosAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_info2);
        rvVideojuegos = (RecyclerView) findViewById(R.id.rvVideojuegos);
        rvEmpleados = (RecyclerView) findViewById(R.id.rvEmpleados);

        //Intent intent = getIntent();
        videojuegos = VideojuegoController.obtenerVideojuego(); /*(ArrayList<Videojuego>) intent.getSerializableExtra(EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN);*/
        if (videojuegos != null) {
            listaVideojuegosAdapter = new ListaVideojuegosAdapter(this, videojuegos);
            rvVideojuegos.setAdapter(listaVideojuegosAdapter);
            rvVideojuegos.setLayoutManager(new LinearLayoutManager(this));
        }
        ItemTouchHelper helperVideojuegos = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int desde = viewHolder.getAdapterPosition();
                int a = target.getAdapterPosition();
                Collections.swap(videojuegos, desde, a);
                listaVideojuegosAdapter.notifyItemMoved(desde, a);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    mostrarToast("Has encontrado el mensaje secreto del RecyclerView de videojuegos");
                    videojuegos.remove(viewHolder.getAdapterPosition());
                    listaVideojuegosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                } else if (direction == ItemTouchHelper.RIGHT) {
                    mostrarToast("Lo que has hecho no hace nada, refresca el layout");
                    videojuegos.remove(viewHolder.getAdapterPosition());
                    listaVideojuegosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            }
        });
        helperVideojuegos.attachToRecyclerView(rvVideojuegos);

        empleados = EmpleadoController.obtenerEmpleado(); /*(ArrayList<Empleado>) intent.getSerializableExtra(EXTRA_OBJETO_EMPLEADO);*/
        if (empleados != null) {
            listaEmpleadosAdapter = new ListaEmpleadosAdapter(this, empleados);
            rvEmpleados.setAdapter(listaEmpleadosAdapter);
            rvEmpleados.setLayoutManager(new LinearLayoutManager(this));
        }
        ItemTouchHelper helperEmpleados = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int desde = viewHolder.getAdapterPosition();
                int a = target.getAdapterPosition();
                Collections.swap(empleados, desde, a);
                listaEmpleadosAdapter.notifyItemMoved(desde, a);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    mostrarToast("Has encontrado el mensaje secreto del RecyclerView de empleados");
                    empleados.remove(viewHolder.getAdapterPosition());
                    listaEmpleadosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                } else if (direction == ItemTouchHelper.RIGHT) {
                    mostrarToast("Lo que has hecho no hace nada, refresca el layout");
                    empleados.remove(viewHolder.getAdapterPosition());
                    listaEmpleadosAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            }
        });
        helperEmpleados.attachToRecyclerView(rvEmpleados);
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void refrescarRecyclerViewEmpleados(View view) {
        empleados = EmpleadoController.obtenerEmpleado();
        if(empleados != null){
            listaEmpleadosAdapter.setListaEmpleados(empleados);
            rvEmpleados.getAdapter().notifyDataSetChanged();
        }
    }

    public void refrescarRecyclerViewVideojuegos(View view) {
        videojuegos = VideojuegoController.obtenerVideojuego();
        if(videojuegos != null){
            listaVideojuegosAdapter.setListaVideojuegos(videojuegos);
            rvVideojuegos.getAdapter().notifyDataSetChanged();
        }
    }
}