package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.EmpleadoRecyclerView.ListaEmpleadosAdapter;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.EmpleadoController;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.ConfiguracionesGeneralesDB;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.PaginationListener;

import java.util.ArrayList;

public class EmpleadosFragment extends Fragment {
    private RecyclerView rvEmpleados;
    private ArrayList<Empleado> empleados;
    private ListaEmpleadosAdapter listaEmpleadosAdapter;

    private int páginaActual;
    private int totalRegistros;
    private int totalPáginas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_empleados, container, false);

        totalRegistros = EmpleadoController.obtenerCantidadVideojuegos();
        totalPáginas = (totalRegistros / ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA) + 1;

        Log.i("SQL", "TOTAL DE REGISTROS -> " + String.valueOf(totalRegistros));
        Log.i("SQL", "TOTAL DE PÁGINAS -> " + String.valueOf(totalPáginas));

        páginaActual = 0;
        empleados = EmpleadoController.obtenerEmpleado(páginaActual);
        páginaActual = páginaActual + 1;
        if (empleados != null) {
            Log.i("SQL", "PÁGINA ACTUAL -> " + String.valueOf(páginaActual));
            Log.i("SQL", "VIDEOJUEGOS LEÍDOS -> " + String.valueOf(this.empleados.size()));
            rvEmpleados = vista.findViewById(R.id.rvVideojuegos);
            listaEmpleadosAdapter = new ListaEmpleadosAdapter(getActivity(), empleados);
            rvEmpleados.setAdapter(listaEmpleadosAdapter);
            int orientation = 1;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                rvEmpleados.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                rvEmpleados.setLayoutManager(new GridLayoutManager(getActivity(), ConfiguracionesGeneralesDB.LANDSCAPE_NUM_COLUMNAS));
            }
        }

        //PAGINACIÓN
        rvEmpleados.addOnScrollListener(new PaginationListener((LinearLayoutManager) rvEmpleados.getLayoutManager()) {
            private int empleadosLeídos = 0;

            @Override
            protected void loadMoreItems() {
                int totalRegistrosLeídos = rvEmpleados.getLayoutManager().getItemCount();
                if (totalRegistrosLeídos < totalRegistros) {
                    ArrayList<Empleado> nuevosEmpleados = EmpleadoController.obtenerEmpleado(páginaActual);
                    empleadosLeídos = nuevosEmpleados.size();
                    páginaActual = páginaActual + 1;

                    Boolean resultado = rvEmpleados.post(new Runnable() {
                        @Override
                        public void run() {
                            ListaEmpleadosAdapter listaEmpleadosAdapter1 = (ListaEmpleadosAdapter) rvEmpleados.getAdapter();
                            ArrayList<Empleado> empleadosRv = listaEmpleadosAdapter1.getListaEmpleados();
                            empleadosRv.addAll(nuevosEmpleados);
                            rvEmpleados.getAdapter().notifyDataSetChanged();
                        }});
                    Log.i("SQL", "SIGIENTE PÁGINA -> " + String.valueOf(páginaActual));
                    Log.i("SQL", "TOTAL REGISTROS -> " + String.valueOf(totalRegistros));
                    Log.i("SQL", "TOTAL REGISTROS LEÍDOS -> " + String.valueOf(totalRegistrosLeídos));
                    Log.i("SQL", "VIDEOJUEGOS LEÍDOS -> " + String.valueOf(this.empleadosLeídos));
                }else{
                    empleadosLeídos = 0;
                }
            }

            @Override
            public boolean isLastPage() {
                return false;
            }
        });

        //DESLIZAR ITEMS DEL RV HACIA UN LADO O HACIA OTRO
        /*
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
         */
        return vista;
    }

    /*
    private void mostrarToast(String texto) {
        Toast.makeText(getActivity(), texto, Toast.LENGTH_SHORT).show();
    }
    */

    //MÉTODO DEL BOTÓN PARA REFRESCAR EL RV DE EMPLEADOS
    /*
    public void refrescarRecyclerViewEmpleados(View view) {
        empleados = EmpleadoController.obtenerEmpleado();
        if(empleados != null){
            listaEmpleadosAdapter.setListaEmpleados(empleados);
            rvEmpleados.getAdapter().notifyDataSetChanged();
        }
    }
    */
}