package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

import android.util.Log;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.Utilidades.blobABitmap;

public class VideojuegoDB {
    public static ArrayList<Videojuego> obtenerVideojuego(){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos 'videojuego'");
            return null;
        }
        ArrayList<Videojuego> videojuegosDevueltos = new ArrayList<Videojuego>();
        try {
            Statement sentencia = conexión.createStatement();
            String ordenSQL = "SELECT * FROM videojuego";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next()) {
                int idVideojuego = resultado.getInt("id_videojuego");
                String títuloVideojuego = resultado.getString("título_videojuego");
                int pegiVideojuego = resultado.getInt("pegi_videojuego");
                String géneroVideojuego = resultado.getString("genero_videojuego");
                Blob logoVideojuego = resultado.getBlob("logo_videojuego");
                Videojuego v = new Videojuego(idVideojuego, títuloVideojuego, pegiVideojuego, géneroVideojuego, blobABitmap(logoVideojuego, ConfiguraciónImágenesDB.ANCHO_FOTO, ConfiguraciónImágenesDB.ALTO_FOTO));
                videojuegosDevueltos.add(v);
            }
            resultado.close();
            sentencia.close();
            conexión.close();
            return videojuegosDevueltos;
        } catch (SQLException e) {
            Log.i("SQL", "Error al mostrar los videojuegos de la base de datos");
            return null;
        }
    }

    public static Videojuego buscarVideojuego(String nombreV) {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos 'videojuego'");
            return null;
        }
        Videojuego videojuegoEncontrado = null;
        try {
            ResultSet resultadoSQL = BaseDB.buscarFilas(conexión, "videojuego", "título_videojuego", nombreV);
            if (resultadoSQL == null) {
                return null;
            }
            while (resultadoSQL.next()) {
                int idVideojuego = resultadoSQL.getInt("id_videojuego");
                String títuloVideojuego = resultadoSQL.getString("título_videojuego");
                int pegiVideojuego = resultadoSQL.getInt("pegi_videojuego");
                String géneroVideojuego = resultadoSQL.getString("genero_videojuego");
                Blob logoVideojuego = resultadoSQL.getBlob("logo_videojuego");
                videojuegoEncontrado = new Videojuego(idVideojuego, títuloVideojuego, pegiVideojuego, géneroVideojuego, blobABitmap(logoVideojuego, ConfiguraciónImágenesDB.ANCHO_FOTO, ConfiguraciónImágenesDB.ALTO_FOTO));
            }
            resultadoSQL.close();
            conexión.close();
            return videojuegoEncontrado;
        } catch (SQLException e) {
            Log.i("SQL", "Error al mostrar los videojuegos de la base de datos");
            return null;
        }
    }
}