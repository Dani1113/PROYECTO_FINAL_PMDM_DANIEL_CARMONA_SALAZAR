package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_IMAGEN_VIDEOJUEGO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.Utilidades.bytesABitmap;

public class ListaVentasAdapter extends RecyclerView.Adapter<VentaViewHolder> {
    //ATRIBUTOS
    private LayoutInflater inflater;
    private ArrayList<Venta> listaVentas;
    private Context contexto;

    //CONSTRUCTORES
    public ListaVentasAdapter(Context contexto, ArrayList<Venta> listaVentas) {
        this.listaVentas = listaVentas;
        this.contexto = contexto;
        inflater = LayoutInflater.from(contexto);
    }

    //GETTERS & SETTERS
    public ArrayList<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(ArrayList<Venta> listaVentas) {
        this.listaVentas = listaVentas;
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
    public VentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new VentaViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull VentaViewHolder holder, int position) {
        Venta vActual = listaVentas.get(position);

        Bitmap logo = vActual.getVideojuego().getLogoVideojuego();
        if(logo != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            logo.compress(Bitmap.CompressFormat.PNG, 0, baos);
            byte[] byteArrayLogo = baos.toByteArray();
            if(byteArrayLogo != null){
                holder.imgVideojuego.setImageBitmap(bytesABitmap(byteArrayLogo));
            }
        }

        holder.txtNVenta.setText(String.valueOf("Nº de venta: " + vActual.getNúmeroVenta()));

        String videojuego = "Videojuego: " + vActual.getVideojuego().getTítuloVideojuego();
        if(videojuego.length() > 35){
            holder.txtVideojuego.setText(videojuego.substring(0, 30) + "...");
        }else{
            holder.txtVideojuego.setText(videojuego);
        }

        String empleado = "Empleado: " + vActual.getEmpleado().getNombreEmpleado();
        if(empleado.length() > 35){
            holder.txtEmpleado.setText(empleado.substring(0, 30) + "...");
        }else{
            holder.txtEmpleado.setText(empleado);
        }
    }

    @Override
    public int getItemCount() {
        return listaVentas.size();
    }
}
