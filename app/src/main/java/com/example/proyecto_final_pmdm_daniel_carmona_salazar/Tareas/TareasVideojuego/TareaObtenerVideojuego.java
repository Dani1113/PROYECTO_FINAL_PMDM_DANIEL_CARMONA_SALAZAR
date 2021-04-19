package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.VideojuegoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerVideojuego implements Callable<ArrayList<Videojuego>> {
    private ArrayList<Videojuego> videojuegosDevueltos = null;
    private int página;

    public TareaObtenerVideojuego(int página){
        this.página = página;
    }

    @Override
    public ArrayList<Videojuego> call() throws Exception {
        videojuegosDevueltos = VideojuegoDB.obtenerVideojuego(página);
        return videojuegosDevueltos;
    }
}
