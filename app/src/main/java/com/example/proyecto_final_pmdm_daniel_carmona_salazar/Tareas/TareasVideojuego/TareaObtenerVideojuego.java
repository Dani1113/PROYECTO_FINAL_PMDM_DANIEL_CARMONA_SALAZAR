package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.VideojuegoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerVideojuego implements Callable<ArrayList<Videojuego>> {
    @Override
    public ArrayList<Videojuego> call() throws Exception {
        ArrayList<Videojuego> videojuegosDevueltos = VideojuegoDB.obtenerVideojuego();
        return videojuegosDevueltos;
    }
}
