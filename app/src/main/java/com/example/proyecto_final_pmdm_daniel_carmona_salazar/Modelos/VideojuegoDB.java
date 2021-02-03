package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VideojuegoDB {

    //----------------------------------------------------------------------------
    //Método que convierte de Blob a Byte[]
    public static byte[] blobABytes(Blob b){
        int blobLength = 0;
        byte[] blobAsBytes = new byte[0];
        try {
            blobLength = (int) b.length();
            blobAsBytes = b.getBytes(1, blobLength);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blobAsBytes;
    }

    //----------------------------------------------------------------------------
    //Método que convierte de Bytes[] a Bitmap
    public static Bitmap bytesABitmap(byte[] b){
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(200, 200,config);
        try{
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        } catch (Exception e){
        }
        return bitmap;
    }
    //------------------------------------------------------------------------------

    public static ArrayList<Videojuego> obtenerVideojuego(){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
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
                Videojuego v = new Videojuego(idVideojuego, títuloVideojuego, pegiVideojuego, géneroVideojuego, bytesABitmap(blobABytes(logoVideojuego)));
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
}