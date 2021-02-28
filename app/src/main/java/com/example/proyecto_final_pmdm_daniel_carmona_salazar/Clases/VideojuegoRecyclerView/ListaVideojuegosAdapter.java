package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.VideojuegoRecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.Utilidades.bytesABitmap;

public class ListaVideojuegosAdapter extends RecyclerView.Adapter<VideojuegosViewHolder> {
    //ATRIBUTOS
    private LayoutInflater inflater;
    private ArrayList<Videojuego> listaVideojuegos;
    private Context contexto;

    //CONSTRUCTORES
    public ListaVideojuegosAdapter(Context contexto, ArrayList<Videojuego> listaVideojuegos) {
        this.listaVideojuegos = listaVideojuegos;
        this.contexto = contexto;
        inflater = LayoutInflater.from(contexto);
    }

    //GETTERS & SETTERS
    public ArrayList<Videojuego> getListaVideojuegos() {
        return listaVideojuegos;
    }

    public void setListaVideojuegos(ArrayList<Videojuego> listaVideojuegos) {
        this.listaVideojuegos = listaVideojuegos;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    //MÉTODOS
    @NonNull
    @Override
    public VideojuegosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_view_videojuego_item, parent, false);
        return new VideojuegosViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegosViewHolder holder, int position) {
        Videojuego vActual = listaVideojuegos.get(position);

        String títuloVideojuego = String.valueOf("Título del videojuego: " + vActual.getTítuloVideojuego() + "\n");
        if(títuloVideojuego.length() > 175){
            holder.txtTítuloVideojuego.setText(títuloVideojuego.substring(0, 174) + "...");
        }else {
            holder.txtTítuloVideojuego.setText(títuloVideojuego);
        }
    }

    @Override
    public int getItemCount() { return listaVideojuegos.size(); }
}