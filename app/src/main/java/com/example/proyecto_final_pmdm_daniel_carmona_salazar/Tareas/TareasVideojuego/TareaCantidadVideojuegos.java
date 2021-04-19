package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVideojuego;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.VideojuegoDB;

import java.util.concurrent.Callable;

public class TareaCantidadVideojuegos implements Callable<Integer> {
    private int cantidadVideojuegos = 0;

    @Override
    public Integer call() throws Exception {
        cantidadVideojuegos = VideojuegoDB.obtenerCantidadVideojuegos();
        return cantidadVideojuegos;
    }
}
