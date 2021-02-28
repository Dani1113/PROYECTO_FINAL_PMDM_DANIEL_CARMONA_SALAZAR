package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.EmpleadoRecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.MostrarInfoDetalleEmpleadoActivity;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.R;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_EMPLEADO;

public class EmpleadosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ListaEmpleadosAdapter listaEmpleadosAdapter;
    public TextView txtNombreEmpleado = null;
    public TextView txtApellidosEmpleado = null;

    public EmpleadosViewHolder(@NonNull View itemView, ListaEmpleadosAdapter listaEmpleadosAdapter) {
        super(itemView);
        txtNombreEmpleado = (TextView) itemView.findViewById(R.id.txtNombreEmpleado);
        txtApellidosEmpleado = (TextView) itemView.findViewById(R.id.txtApellidosEmpleado);
        this.listaEmpleadosAdapter = listaEmpleadosAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int posición = getLayoutPosition();
        Empleado eSeleccionado = this.listaEmpleadosAdapter.getListaEmpleados().get(posición);
        listaEmpleadosAdapter.notifyDataSetChanged();
        Intent intent = new Intent(listaEmpleadosAdapter.getContexto(), MostrarInfoDetalleEmpleadoActivity.class);

        //Empleado
        String nombreEmpleado = eSeleccionado.getNombreEmpleado();
        String apellidosEmpleado = eSeleccionado.getApellidosEmpleado();
        String domicilioEmpleado = eSeleccionado.getDomicilioEmpleado();
        String teléfonoEmpleado = eSeleccionado.getTelefonoEmpleado();
        Empleado empleado = new Empleado(nombreEmpleado, apellidosEmpleado, domicilioEmpleado, teléfonoEmpleado);
        intent.putExtra(EXTRA_OBJETO_EMPLEADO, empleado);

        listaEmpleadosAdapter.getContexto().startActivity(intent);
    }
}
