package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

import android.util.Log;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoDB {

    public static ArrayList<Empleado> obtenerEmpleado(){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return null;
        }
        ArrayList<Empleado> empleadosDevueltos = new ArrayList<Empleado>();
        try {
            Statement sentencia = conexión.createStatement();
            String ordenSQL = "SELECT * FROM empleado";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next()) {
                int idEmpleado = resultado.getInt("id_empleado");
                String nombreEmpleado = resultado.getString("nombre_empleado");
                String apellidosEmpleado = resultado.getString("apellidos_empleado");
                String domicilioEmpleado = resultado.getString("domicilio_empleado");
                String teléfonoEmpleado = resultado.getString("telefono_empleado");
                Empleado e = new Empleado(idEmpleado, nombreEmpleado, apellidosEmpleado, domicilioEmpleado, teléfonoEmpleado);
                empleadosDevueltos.add(e);
            }
            resultado.close();
            sentencia.close();
            conexión.close();
            return empleadosDevueltos;
        } catch (SQLException e) {
            Log.i("SQL", "Error al mostrar los empleados de la base de datos");
            return null;
        }
    }
}