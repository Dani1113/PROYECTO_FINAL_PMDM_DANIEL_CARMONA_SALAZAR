package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.VideojuegoDB;

import java.util.concurrent.Callable;

public class TareaBuscarVideojuego implements Callable<Videojuego> {
    private String nombreV = null;

    public TareaBuscarVideojuego(String nombreV){
        this.nombreV = nombreV;
    }

    @Override
    public Videojuego call() throws Exception {
        Videojuego v = VideojuegoDB.buscarVideojuego(nombreV);
        return v;
    }
}
