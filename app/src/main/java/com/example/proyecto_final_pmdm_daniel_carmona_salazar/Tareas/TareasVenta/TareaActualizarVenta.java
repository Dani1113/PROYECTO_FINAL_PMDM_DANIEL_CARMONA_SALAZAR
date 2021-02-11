package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Tareas.TareasVenta;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.VentaDB;

import java.util.concurrent.Callable;

public class TareaActualizarVenta implements Callable<Boolean> {
    private Venta v = null;

    public TareaActualizarVenta(Venta v) {
        this.v = v;
    }

    @Override
    public Boolean call() throws Exception {
        boolean actualizadoOk = VentaDB.actualizarVenta(v);
        return actualizadoOk;
    }
}
