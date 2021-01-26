package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDB {
    public static Connection conectarConBaseDeDatos() {
        try {
            Connection conexión = DriverManager.getConnection(ConfiguraciónDB.URLMYSQL, ConfiguraciónDB.USUARIODB, ConfiguraciónDB.CLAVEDB);
            return conexión;
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión con la base de datos");
            return null;
        }
    }
}
