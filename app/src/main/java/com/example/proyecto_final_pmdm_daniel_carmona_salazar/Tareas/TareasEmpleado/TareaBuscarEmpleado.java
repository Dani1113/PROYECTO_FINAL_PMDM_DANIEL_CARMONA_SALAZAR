package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.EmpleadoDB;

import java.util.concurrent.Callable;

public class TareaBuscarEmpleado implements Callable<Empleado> {
    private String nombreE = null;

    public TareaBuscarEmpleado(String nombreE){
        this.nombreE = nombreE;
    }

    @Override
    public Empleado call() throws Exception {
        Empleado e = EmpleadoDB.buscarEmpleado(nombreE);
        return e;
    }
}
