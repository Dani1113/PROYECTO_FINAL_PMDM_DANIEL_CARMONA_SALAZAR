package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.MostrarInfoDetalleActivity;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.R;

import java.io.ByteArrayOutputStream;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_IMAGEN_VIDEOJUEGO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_EMPLEADO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VENTA_SIN_VIDEOJUEGO_NI_EMPLEADO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN;

public class VentaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ListaVentasAdapter listaVentasAdapter;
    public TextView txtNVenta = null;
    public TextView txtVideojuego = null;
    public TextView txtEmpleado = null;
    public ImageView imgVideojuego = null;

    public VentaViewHolder(@NonNull View itemView, ListaVentasAdapter listaVentasAdapter) {
        super(itemView);
        txtNVenta = (TextView) itemView.findViewById(R.id.txtNVenta);
        txtVideojuego = (TextView) itemView.findViewById(R.id.txtVideojuego);
        txtEmpleado = (TextView) itemView.findViewById(R.id.txtEmpleado);
        imgVideojuego = (ImageView) itemView.findViewById(R.id.imgVideojuego);
        this.listaVentasAdapter = listaVentasAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int posición = getLayoutPosition();
        Venta vSeleccionada = this.listaVentasAdapter.getListaVentas().get(posición);
        listaVentasAdapter.notifyDataSetChanged();
        Intent intent = new Intent(listaVentasAdapter.getContexto(), MostrarInfoDetalleActivity.class);

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

        listaVentasAdapter.getContexto().startActivity(intent);
    }
}
