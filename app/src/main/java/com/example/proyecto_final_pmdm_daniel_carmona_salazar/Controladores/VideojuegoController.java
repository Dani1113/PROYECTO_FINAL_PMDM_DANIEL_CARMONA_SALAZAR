package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego.TareaCantidadVideojuegos;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego.TareaBuscarVideojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego.TareaObtenerVideojuego;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class VideojuegoController {
    public static ArrayList<Videojuego> obtenerVideojuegos(int página) {
        ArrayList<Videojuego> videojuegosDevueltos = null;
        FutureTask tarea = new FutureTask(new TareaObtenerVideojuego(página));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            videojuegosDevueltos = (ArrayList<Videojuego>) tarea.get();
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
        return videojuegosDevueltos;
    }

    public static ArrayList<Videojuego> buscarVideojuego(String nombreV){
        ArrayList<Videojuego> videojuegosEncontrados = null;
        FutureTask tarea = new FutureTask(new TareaBuscarVideojuego(nombreV));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            videojuegosEncontrados = (ArrayList<Videojuego>) tarea.get();
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
        return videojuegosEncontrados;
    }

    public static int obtenerCantidadVideojuegos() {
        int cantidadVideojuegos = 0;
        FutureTask tarea = new FutureTask (new TareaCantidadVideojuegos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            cantidadVideojuegos = (int) tarea.get();
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
        return cantidadVideojuegos;
    }
}