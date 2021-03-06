package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static ResultSet buscarFilas(Connection conexión, String nombreTabla, String columna, String valorColumna) {
        try {
            String ordenSQL = "SELECT * FROM " + nombreTabla + " WHERE " + columna + " LIKE ? ";
            PreparedStatement sentencia = conexión.prepareStatement(ordenSQL);
            sentencia.setString(1, valorColumna);
            ResultSet resultado = sentencia.executeQuery();
            return resultado;
        } catch (SQLException e) {
            return null;
        }
    }
}
