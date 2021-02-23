package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.Utilidades.blobABitmap;

public class VentaDB {

    public static ArrayList<Venta> obtenerVenta(){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return null;
        }
        ArrayList<Venta> ventasDevueltas = new ArrayList<Venta>();
        try {
            Statement sentencia = conexión.createStatement();
            String ordenSQL = "SELECT ve.id_venta, ve.numero_venta, e.nombre_empleado, e.apellidos_empleado, e.domicilio_empleado, e.telefono_empleado, vi.título_videojuego, vi.pegi_videojuego, vi.genero_videojuego, vi.logo_videojuego FROM venta ve INNER JOIN empleado e INNER JOIN videojuego vi ON (e.id_empleado = ve.EMPLEADO_id_empleado AND vi.id_videojuego = ve.VIDEOJUEGO_id_videojuego)";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next()) {
                //Recojo el empleado
                String nombreEmpleado = resultado.getString("nombre_empleado");
                String apellidosEmpleado = resultado.getString("apellidos_empleado");
                String domicilioEmpleado = resultado.getString("domicilio_empleado");
                String teléfonoEmpleado = resultado.getString("telefono_empleado");
                Empleado e = new Empleado(nombreEmpleado, apellidosEmpleado, domicilioEmpleado, teléfonoEmpleado);

                //Recojo el videojuego
                String títuloVideojuego = resultado.getString("título_videojuego");
                int pegiVideojuego = resultado.getInt("pegi_videojuego");
                String géneroVideojuego = resultado.getString("genero_videojuego");
                Blob logoVideojuego = resultado.getBlob("logo_videojuego");
                Videojuego v = new Videojuego(títuloVideojuego, pegiVideojuego, géneroVideojuego, blobABitmap(logoVideojuego, ConfiguraciónImágenesDB.ANCHO_FOTO, ConfiguraciónImágenesDB.ALTO_FOTO));

                //Recojo la venta
                int idVenta = resultado.getInt("id_venta");
                int numeroVenta = resultado.getInt("numero_venta");
                Venta v1 = new Venta(idVenta, numeroVenta, e, v);
                ventasDevueltas.add(v1);
            }
            resultado.close();
            sentencia.close();
            conexión.close();
            return ventasDevueltas;
        } catch (SQLException e) {
            Log.i("SQL", "Error al mostrar las ventas de la base de datos");
            return null;
        }
    }

    public static boolean insertarVenta(Venta v){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            //Inserto el empleado
            String ordenSQL1 = "INSERT INTO empleado (nombre_empleado, apellidos_empleado, domicilio_empleado, telefono_empleado) VALUES (?, ?, ?, ?);";
            PreparedStatement sentenciaPreparada1 = conexión.prepareStatement(ordenSQL1);
            sentenciaPreparada1.setString(1, v.getEmpleado().getNombreEmpleado());
            sentenciaPreparada1.setString(2, v.getEmpleado().getApellidosEmpleado());
            sentenciaPreparada1.setString(3, v.getEmpleado().getDomicilioEmpleado());
            sentenciaPreparada1.setString(4, v.getEmpleado().getTelefonoEmpleado());
            int filasAfectadas1 = sentenciaPreparada1.executeUpdate();
            sentenciaPreparada1.close();

            //Inserto el videojuego
            String ordenSQL2 = "INSERT INTO videojuego (título_videojuego, pegi_videojuego, genero_videojuego, logo_videojuego) VALUES (?, ?, ?, ?);";
            PreparedStatement sentenciaPreparada2 = conexión.prepareStatement(ordenSQL2);
            sentenciaPreparada2.setString(1, v.getVideojuego().getTítuloVideojuego());
            sentenciaPreparada2.setInt(2, v.getVideojuego().getPegiVideojuego());
            sentenciaPreparada2.setString(3, v.getVideojuego().getGéneroVideojuego());
            Bitmap logoBlob = v.getVideojuego().getLogoVideojuego();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            logoBlob.compress(Bitmap.CompressFormat.PNG, 0, baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            sentenciaPreparada2.setBinaryStream(4, bais);
            int filasAfectadas2 = sentenciaPreparada2.executeUpdate();
            sentenciaPreparada2.close();

            //Recojo el ID del empleado y del videojuego
            String ordenSQL3 = "SELECT id_videojuego FROM videojuego v WHERE título_videojuego = ?;";
            PreparedStatement sentenciaPreparada3 = conexión.prepareStatement(ordenSQL3);
            sentenciaPreparada3.setString(1, v.getVideojuego().getTítuloVideojuego());
            ResultSet resultado = sentenciaPreparada3.executeQuery();
            int id_videojuego = 0;
            while(resultado.next()) {
                id_videojuego = resultado.getInt("id_videojuego");
            }
            resultado.close();
            sentenciaPreparada3.close();

            String ordenSQL4 = "SELECT id_empleado FROM empleado e WHERE nombre_empleado = ?;";
            PreparedStatement sentenciaPreparada4 = conexión.prepareStatement(ordenSQL4);
            sentenciaPreparada4.setString(1, v.getEmpleado().getNombreEmpleado());
            ResultSet resultado2 = sentenciaPreparada4.executeQuery();
            int id_empleado = 0;
            while(resultado2.next()) {
                id_empleado = resultado2.getInt("id_empleado");
            }
            resultado2.close();
            sentenciaPreparada4.close();

            //Inserto la venta
            String ordenSQL5 = "INSERT INTO venta (numero_venta, EMPLEADO_id_empleado, VIDEOJUEGO_id_videojuego) VALUES (?, ?, ?);";
            PreparedStatement sentenciaPreparada5 = conexión.prepareStatement(ordenSQL5);
            sentenciaPreparada5.setInt(1, v.getNúmeroVenta());
            sentenciaPreparada5.setInt(2, id_empleado);
            sentenciaPreparada5.setInt(3, id_videojuego);
            int filasAfectadas3 = sentenciaPreparada5.executeUpdate();
            sentenciaPreparada5.close();

            conexión.close();

            if(filasAfectadas1 > 0 && filasAfectadas2 > 0 && filasAfectadas3 > 0) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            Log.i("SQL", "Error al insertar en la base de datos");
            return false;
        }
    }

    public static boolean borrarVenta(Venta v) {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            String ordenSQL = "DELETE FROM venta WHERE numero_venta = ?;";
            PreparedStatement sentenciaPreparada = conexión.prepareStatement(ordenSQL);
            sentenciaPreparada.setInt(1, v.getNúmeroVenta());
            int filasAfectadas1 = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexión.close();
            if(filasAfectadas1 > 0) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            Log.i("SQL", "Error al borrar en la base de datos");
            return false;
        }
    }

    public static boolean actualizarVenta(Venta v) {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            //Recojo el ID del empleado y del videojuego
            String ordenSQL = "SELECT id_videojuego FROM videojuego v WHERE título_videojuego = ?;";
            PreparedStatement sentenciaPreparada = conexión.prepareStatement(ordenSQL);
            sentenciaPreparada.setString(1, v.getVideojuego().getTítuloVideojuego());
            ResultSet resultado = sentenciaPreparada.executeQuery();
            int id_videojuego = 0;
            while(resultado.next()) {
                id_videojuego = resultado.getInt("id_videojuego");
            }
            resultado.close();
            sentenciaPreparada.close();

            String ordenSQL2 = "SELECT id_empleado FROM empleado e WHERE nombre_empleado = ?;";
            PreparedStatement sentenciaPreparada2 = conexión.prepareStatement(ordenSQL2);
            sentenciaPreparada2.setString(1, v.getEmpleado().getNombreEmpleado());
            ResultSet resultado2 = sentenciaPreparada2.executeQuery();
            int id_empleado = 0;
            while(resultado2.next()) {
                id_empleado = resultado2.getInt("id_empleado");
            }
            resultado2.close();
            sentenciaPreparada2.close();

            //Actualizo el empleado
            String ordenSQL3 = "UPDATE empleado SET nombre_empleado = ?, apellidos_empleados = ?, domicilio_empleado = ?, telefono_empleado = ? WHERE id_empleado = ?";
            PreparedStatement sentenciaPreparada3 = conexión.prepareStatement(ordenSQL3);
            sentenciaPreparada3.setString(1, v.getEmpleado().getNombreEmpleado());
            sentenciaPreparada3.setString(2, v.getEmpleado().getApellidosEmpleado());
            sentenciaPreparada3.setString(3, v.getEmpleado().getDomicilioEmpleado());
            sentenciaPreparada3.setString(4, v.getEmpleado().getTelefonoEmpleado());
            sentenciaPreparada3.setInt(5, id_empleado);
            int filasAfectadas1 = sentenciaPreparada3.executeUpdate();
            sentenciaPreparada3.close();

            //Actualizo el videojuego
            String ordenSQL4 = "UPDATE videojuego SET título_videojuego = ?, pegi_videojuego = ?, genero_videojuego = ?, logo_videojuego = ? WHERE id_videojuego  = ?";
            PreparedStatement sentenciaPreparada4 = conexión.prepareStatement(ordenSQL4);
            sentenciaPreparada4.setString(1, v.getVideojuego().getTítuloVideojuego());
            sentenciaPreparada4.setInt(2, v.getVideojuego().getPegiVideojuego());
            sentenciaPreparada4.setString(3, v.getVideojuego().getGéneroVideojuego());
            Bitmap logoBlob = v.getVideojuego().getLogoVideojuego();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            logoBlob.compress(Bitmap.CompressFormat.PNG, 0, baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            sentenciaPreparada4.setBinaryStream(4, bais);
            sentenciaPreparada4.setInt(5, id_videojuego);
            int filasAfectadas2 = sentenciaPreparada4.executeUpdate();
            sentenciaPreparada4.close();

            //Recojo el ID de la venta
            String ordenSQL5 = "SELECT id_venta FROM venta v WHERE numero_venta = ?;";
            PreparedStatement sentenciaPreparada5 = conexión.prepareStatement(ordenSQL5);
            sentenciaPreparada5.setInt(1, v.getNúmeroVenta());
            ResultSet resultado3 = sentenciaPreparada5.executeQuery();
            int id_venta = 0;
            while(resultado3.next()) {
                id_venta = resultado3.getInt("id_venta");
            }
            resultado3.close();
            sentenciaPreparada5.close();

            //Actualizo la venta
            String ordenSQL6 = "UPDATE venta SET numero_venta = ?, EMPLEADO_id_empleado = ?, VIDEOJUEGO_id_videojuego = ? WHERE id_venta = ?";
            PreparedStatement sentenciaPreparada6 = conexión.prepareStatement(ordenSQL6);
            sentenciaPreparada6.setInt(1, v.getNúmeroVenta());
            sentenciaPreparada6.setInt(2, id_empleado);
            sentenciaPreparada6.setInt(3, id_videojuego);
            sentenciaPreparada6.setInt(4, id_venta);
            int filasAfectadas3 = sentenciaPreparada6.executeUpdate();
            sentenciaPreparada6.close();
            conexión.close();

            if(filasAfectadas1 > 0 && filasAfectadas2 > 0 && filasAfectadas3 > 0) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            Log.i("SQL", "Error al actualizar en la base de datos");
            return false;
        }
    }
}