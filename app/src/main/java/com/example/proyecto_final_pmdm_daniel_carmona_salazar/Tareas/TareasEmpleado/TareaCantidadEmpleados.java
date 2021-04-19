package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.EmpleadoDB;

import java.util.concurrent.Callable;

public class TareaCantidadEmpleados implements Callable<Integer> {
    private int cantidadEmpleados = 0;

    @Override
    public Integer call() throws Exception {
        cantidadEmpleados = EmpleadoDB.obtenerCantidadEmplados();
        return cantidadEmpleados;
    }
}
