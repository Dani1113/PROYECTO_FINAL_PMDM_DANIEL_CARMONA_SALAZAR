package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVenta;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.VentaDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerVenta implements Callable<ArrayList<Venta>> {
    @Override
    public ArrayList<Venta> call() throws Exception {
        ArrayList<Venta> ventasDevueltas = VentaDB.obtenerVenta();
        return ventasDevueltas;
    }
}
