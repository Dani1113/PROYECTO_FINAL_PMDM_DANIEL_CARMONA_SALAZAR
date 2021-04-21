package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

import android.util.Log;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoDB {
    public static ArrayList<Empleado> obtenerEmpleado(int página){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos 'empleado'");
            return null;
        }
        ArrayList<Empleado> empleadosDevueltos = new ArrayList<Empleado>();
        try {
            Statement sentencia = conexión.createStatement();
            int desplazamiento = página * ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA;
            String ordenSQL = "SELECT * FROM empleado LIMIT " + desplazamiento + ", " + ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA;
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

    public static ArrayList<Empleado> buscarEmpleado(String nombreE) {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos 'empleado'");
            return null;
        }
        ArrayList<Empleado> empleadosEncontrados = null;
        try {
            ResultSet resultadoSQL = BaseDB.buscarFilas(conexión, "empleado", "nombre_empleado", nombreE);
            if (resultadoSQL == null) {
                return null;
            }
            while (resultadoSQL.next()) {
                int idEmpleado = resultadoSQL.getInt("id_empleado");
                String nombreEmpleado = resultadoSQL.getString("nombre_empleado");
                String apellidosEmpleado = resultadoSQL.getString("apellidos_empleado");
                String domicilioEmpleado = resultadoSQL.getString("domicilio_empleado");
                String telefonoEmpleado = resultadoSQL.getString("telefono_empleado");
                Empleado e = new Empleado(idEmpleado, nombreEmpleado, apellidosEmpleado, domicilioEmpleado, telefonoEmpleado);
                empleadosEncontrados.add(e);
            }
            resultadoSQL.close();
            conexión.close();
            return empleadosEncontrados;
        } catch (SQLException e) {
            Log.i("SQL", "Error al mostrar los empleados de la base de datos");
            return null;
        }
    }

    public static int obtenerCantidadEmplados() {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos 'empleado'");
            return 0;
        }
        int cantidadEmpleados = 0;
        try {
            Statement sentencia = conexión.createStatement();
            String ordenSQL = "SELECT count(*) as cantidad FROM empleado";
            ResultSet resultado  = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                cantidadEmpleados = resultado.getInt("cantidad");
            }
            resultado.close();
            sentencia.close();
            conexión.close();
            return cantidadEmpleados;
        } catch (SQLException e) {
            Log.i("SQL", "Error al devolver el número de empleados de la base de datos");
            return 0;
        }
    }
}