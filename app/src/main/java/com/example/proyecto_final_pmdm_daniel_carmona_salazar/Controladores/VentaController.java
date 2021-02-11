package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVenta.TareaActualizarVenta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVenta.TareaBorrarVenta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVenta.TareaInsertarVenta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVenta.TareaObtenerVenta;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class VentaController {
    public static ArrayList<Venta> obtenerVenta(){
        ArrayList<Venta> ventasDevueltas = null;
        FutureTask tarea= new FutureTask (new TareaObtenerVenta());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            ventasDevueltas= (ArrayList<Venta>)tarea.get();
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
        return ventasDevueltas;
    }

    public static boolean insertarVenta(Venta v) {
        FutureTask tarea = new FutureTask(new TareaInsertarVenta(v));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean insertadoOk = false;
        try {
            insertadoOk = (boolean) tarea.get();
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
        finally {
            return insertadoOk;
        }
    }

    public static boolean borrarVenta(Venta v) {
        FutureTask tarea = new FutureTask(new TareaBorrarVenta(v));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean borradoOk = false;
        try {
            borradoOk = (boolean) tarea.get();
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
        finally {
            return borradoOk;
        }
    }

    public static boolean actualizarVenta(Venta v) {
        FutureTask tarea = new FutureTask(new TareaActualizarVenta(v));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean actualizadoOk = false;
        try {
            actualizadoOk = (boolean) tarea.get();
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
        finally {
            return actualizadoOk;
        }
    }
}
