package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.VentaRecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VentaController;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.MostrarInfoDetalleVentaActivity;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.R;

import java.io.ByteArrayOutputStream;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_IMAGEN_VIDEOJUEGO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_EMPLEADO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VENTA_SIN_VIDEOJUEGO_NI_EMPLEADO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN;

public class VentaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,  View.OnLongClickListener{

    public ListaVentasAdapter listaVentasAdapter;
    public TextView txtNVenta = null;
    public TextView txtVideojuego = null;
    public ImageView imgVideojuego = null;

    public VentaViewHolder(@NonNull View itemView, ListaVentasAdapter listaVentasAdapter) {
        super(itemView);
        txtNVenta = (TextView) itemView.findViewById(R.id.txtNVenta);
        txtVideojuego = (TextView) itemView.findViewById(R.id.txtVideojuego);
        imgVideojuego = (ImageView) itemView.findViewById(R.id.imgVideojuego);
        this.listaVentasAdapter = listaVentasAdapter;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int posición = getLayoutPosition();
        Venta vSeleccionada = this.listaVentasAdapter.getListaVentas().get(posición);
        listaVentasAdapter.notifyDataSetChanged();
        Intent intent = new Intent(listaVentasAdapter.getContexto(), MostrarInfoDetalleVentaActivity.class);

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

    @Override
    public boolean onLongClick(View v) {
        int posición = getLayoutPosition();
        Venta vSeleccionada = this.listaVentasAdapter.getListaVentas().get(posición);
        AlertDialog.Builder alertaBorrar = new AlertDialog.Builder(v.getContext());
        alertaBorrar.setTitle("¿Desea borrar la venta que ha seleccionado?");
        alertaBorrar.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean borradoOK = VentaController.borrarVenta(vSeleccionada);
                if(borradoOK) {
                    Toast.makeText(v.getContext(), "Venta borrada correctamente, pulsa refrescar", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(v.getContext(), "Error al borrar la venta", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertaBorrar.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(), "Borrado de la venta cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        alertaBorrar.show();
        return false;
    }
}