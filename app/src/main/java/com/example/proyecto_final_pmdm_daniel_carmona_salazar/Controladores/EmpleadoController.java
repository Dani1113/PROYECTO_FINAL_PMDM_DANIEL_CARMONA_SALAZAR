package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado.TareaBuscarEmpleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado.TareaCantidadEmpleados;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasEmpleado.TareaObtenerEmpleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego.TareaCantidadVideojuegos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EmpleadoController {
    public static ArrayList<Empleado> obtenerEmpleado(int página) {
        ArrayList<Empleado> empleadosDevueltas = null;
        FutureTask tarea = new FutureTask(new TareaObtenerEmpleado(página));
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

    public static ArrayList<Empleado> buscarEmpleado(String nombreE){
        ArrayList<Empleado> empleadosEncontrados = null;
        FutureTask tarea = new FutureTask(new TareaBuscarEmpleado(nombreE));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            empleadosEncontrados = (ArrayList<Empleado>) tarea.get();
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
        return empleadosEncontrados;
    }

    public static int obtenerCantidadEmpleados() {
        int cantidadEmpleados = 0;
        FutureTask tarea = new FutureTask (new TareaCantidadEmpleados());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            cantidadEmpleados = (int) tarea.get();
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
        return cantidadEmpleados;
    }
}