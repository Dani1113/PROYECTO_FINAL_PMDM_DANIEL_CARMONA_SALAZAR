package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.EmpleadoRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.R;

import java.util.ArrayList;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<EmpleadosViewHolder> {
    //ATRIBUTOS
    private LayoutInflater inflater;
    private ArrayList<Empleado> listaEmpleados;
    private Context contexto;

    //CONSTRUCTORES
    public ListaEmpleadosAdapter(Context contexto, ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
        this.contexto = contexto;
        inflater = LayoutInflater.from(contexto);
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
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
    public EmpleadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_view_empleado_item, parent, false);
        return new EmpleadosViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadosViewHolder holder, int position) {
        Empleado eActual = listaEmpleados.get(position);

        holder.txtNombreEmpleado.setText("Nombre del empleado: " + eActual.getNombreEmpleado());

        String apellidosEmpleado = "Apellidos del empleado: " + eActual.getApellidosEmpleado();
        if (apellidosEmpleado.length() > 115) {
            holder.txtApellidosEmpleado.setText(apellidosEmpleado.substring(0, 114) + "...");
        }else {
            holder.txtApellidosEmpleado.setText(apellidosEmpleado);
        }
    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }
}