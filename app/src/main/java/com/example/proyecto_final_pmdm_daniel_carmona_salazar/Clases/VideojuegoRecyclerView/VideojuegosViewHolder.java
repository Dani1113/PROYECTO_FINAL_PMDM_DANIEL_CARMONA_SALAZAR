package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.VideojuegoRecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.MostrarInfoDetalleVideojuegoActivity;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.R;

import java.io.ByteArrayOutputStream;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_IMAGEN_VIDEOJUEGO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN;

public class VideojuegosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ListaVideojuegosAdapter listaVideojuegosAdapter;
    public TextView txtTítuloVideojuego = null;
    public ImageView imgVideojuego2 = null;

    public VideojuegosViewHolder(@NonNull View itemView, ListaVideojuegosAdapter listaVideojuegosAdapter) {
        super(itemView);
        txtTítuloVideojuego = (TextView) itemView.findViewById(R.id.txtTítuloVideojuego);
        imgVideojuego2 = (ImageView) itemView.findViewById(R.id.imgVideojuego2);
        this.listaVideojuegosAdapter = listaVideojuegosAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int posición = getLayoutPosition();
        Videojuego vSeleccionado = this.listaVideojuegosAdapter.getListaVideojuegos().get(posición);
        listaVideojuegosAdapter.notifyDataSetChanged();
        Intent intent = new Intent(listaVideojuegosAdapter.getContexto(), MostrarInfoDetalleVideojuegoActivity.class);

        //Videojuego
        String títuloVideojuego = vSeleccionado.getTítuloVideojuego();
        int pegiVideojuego = vSeleccionado.getPegiVideojuego();
        String géneroVideojuego = vSeleccionado.getGéneroVideojuego();
        Videojuego videojuego = new Videojuego(títuloVideojuego, pegiVideojuego, géneroVideojuego);
        intent.putExtra(EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN, videojuego);
        Bitmap logo = vSeleccionado.getLogoVideojuego();
        if(logo != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            logo.compress(Bitmap.CompressFormat.PNG, 0, baos);
            byte[] byteArrayLogo = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_VIDEOJUEGO, byteArrayLogo);
        }
        listaVideojuegosAdapter.getContexto().startActivity(intent);
    }
}