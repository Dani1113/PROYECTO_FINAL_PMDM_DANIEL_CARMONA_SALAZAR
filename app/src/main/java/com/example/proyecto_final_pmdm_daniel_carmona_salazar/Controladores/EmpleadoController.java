package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado.TareaBuscarEmpleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado.TareaObtenerEmpleado;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EmpleadoController {
    public static ArrayList<Empleado> obtenerEmpleado() {
        ArrayList<Empleado> empleadosDevueltas = null;
        FutureTask tarea = new FutureTask(new TareaObtenerEmpleado());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            empleadosDevueltas = (ArrayList<Empleado>) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return empleadosDevueltas;
    }

    public static Empleado buscarEmpleado(String nombreE){
        Empleado empleado = null;
        FutureTask tarea = new FutureTask(new TareaBuscarEmpleado(nombreE));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            empleado = (Empleado) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return empleado;
    }
}