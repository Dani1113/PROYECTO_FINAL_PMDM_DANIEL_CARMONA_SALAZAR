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

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.VideojuegoRecyclerView.ListaVideojuegosAdapter;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VideojuegoController;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.ConfiguracionesGeneralesDB;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.PaginationListener;

import java.util.ArrayList;

public class VideojuegosFragment extends Fragment {
    private RecyclerView rvVideojuegos;
    private ArrayList<Videojuego> videojuegos;
    private ListaVideojuegosAdapter listaVideojuegosAdapter;

    private int páginaActual;
    private int totalRegistros;
    private int totalPáginas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_videojuegos, container, false);

        totalRegistros = VideojuegoController.obtenerCantidadVideojuegos();
        totalPáginas = (totalRegistros / ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA) + 1;

        Log.i("SQL", "TOTAL DE REGISTROS -> " + String.valueOf(totalRegistros));
        Log.i("SQL", "TOTAL DE PÁGINAS -> " + String.valueOf(totalPáginas));

        páginaActual = 0;
        videojuegos = VideojuegoController.obtenerVideojuegos(páginaActual);
        páginaActual = páginaActual + 1;
        if (videojuegos != null) {
            Log.i("SQL", "PÁGINA ACTUAL -> " + String.valueOf(páginaActual));
            Log.i("SQL", "VIDEOJUEGOS LEÍDOS -> " + String.valueOf(this.videojuegos.size()));
            rvVideojuegos = vista.findViewById(R.id.rvVideojuegos);
            listaVideojuegosAdapter = new ListaVideojuegosAdapter(getActivity(), videojuegos);
            rvVideojuegos.setAdapter(listaVideojuegosAdapter);
            int orientation = 1;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                rvVideojuegos.setLayoutManager(new LinearLayoutManager(getActivity()));
            } else {
                rvVideojuegos.setLayoutManager(new GridLayoutManager(getActivity(), ConfiguracionesGeneralesDB.LANDSCAPE_NUM_COLUMNAS));
            }

            //PAGINACIÓN
            rvVideojuegos.addOnScrollListener(new PaginationListener((LinearLayoutManager) rvVideojuegos.getLayoutManager()) {
                private int videojuegosLeídos = 0;

                @Override
                protected void loadMoreItems() {
                    int totalRegistrosLeídos = rvVideojuegos.getLayoutManager().getItemCount();
                    if (totalRegistrosLeídos < totalRegistros) {
                        ArrayList<Videojuego> nuevosVideojuegos = VideojuegoController.obtenerVideojuegos(páginaActual);
                        videojuegosLeídos = nuevosVideojuegos.size();
                        páginaActual = páginaActual + 1;

                        Boolean resultado = rvVideojuegos.post(new Runnable() {
                        @Override
                        public void run() {
                            ListaVideojuegosAdapter listaVideojuegosAdapter1 = (ListaVideojuegosAdapter) rvVideojuegos.getAdapter();
                            ArrayList<Videojuego> videojuegosRv = listaVideojuegosAdapter1.getListaVideojuegos();
                            videojuegosRv.addAll(nuevosVideojuegos);
                            rvVideojuegos.getAdapter().notifyDataSetChanged();
                        }});
                        Log.i("SQL", "SIGIENTE PÁGINA -> " + String.valueOf(páginaActual));
                        Log.i("SQL", "TOTAL REGISTROS -> " + String.valueOf(totalRegistros));
                        Log.i("SQL", "TOTAL REGISTROS LEÍDOS -> " + String.valueOf(totalRegistrosLeídos));
                        Log.i("SQL", "VIDEOJUEGOS LEÍDOS -> " + String.valueOf(this.videojuegosLeídos));
                    }else{
                        videojuegosLeídos = 0;
                    }
                }

                @Override
                public boolean isLastPage() {
                    return false;
                }
            });

            //DESLIZAR ITEMS DEL RV HACIA UN LADO O HACIA OTRO
            /*
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
             */
        }else {
            Log.i("SQL", "Videojuegos = null");
        }
        return vista;
    }

    /*
    private void mostrarToast(String texto) {
        Toast.makeText(getActivity(), texto, Toast.LENGTH_SHORT).show();
    }
    */

    //MÉTODO DEL BOTÓN PARA REFRESCAR EL RV DE VIDEOJUEGOS
    /*
    public void refrescarRecyclerViewVideojuegos(View view) {
        videojuegos = VideojuegoController.obtenerVideojuego();
        if(videojuegos != null){
            listaVideojuegosAdapter.setListaVideojuegos(videojuegos);
            rvVideojuegos.getAdapter().notifyDataSetChanged();
        }
    }
    */
}