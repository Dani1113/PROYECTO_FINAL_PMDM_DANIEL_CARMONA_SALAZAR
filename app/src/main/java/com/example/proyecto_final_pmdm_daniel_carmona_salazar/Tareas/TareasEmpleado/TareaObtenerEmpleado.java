package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.EmpleadoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerEmpleado implements Callable<ArrayList<Empleado>> {
    private ArrayList<Empleado> empleadosDevueltos = null;
    private int página;

    public TareaObtenerEmpleado(int página){
        this.página = página;
    }

    @Override
    public ArrayList<Empleado> call() throws Exception {
        ArrayList<Empleado> empleadosDevueltos = EmpleadoDB.obtenerEmpleado(página);
        return empleadosDevueltos;
    }
}
