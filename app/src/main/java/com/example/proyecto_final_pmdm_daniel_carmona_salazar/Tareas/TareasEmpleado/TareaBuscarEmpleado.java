package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.EmpleadoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaBuscarEmpleado implements Callable<ArrayList<Empleado>> {
    private String nombreE = null;

    public TareaBuscarEmpleado(String nombreE){
        this.nombreE = nombreE;
    }

    @Override
    public ArrayList<Empleado> call() throws Exception {
        ArrayList<Empleado> empleadosEncontrados = EmpleadoDB.buscarEmpleado(nombreE);
        return empleadosEncontrados;
    }
}
