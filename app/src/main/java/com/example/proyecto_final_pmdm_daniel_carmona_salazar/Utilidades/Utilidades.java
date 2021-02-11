package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.SQLException;

public class Utilidades {
    //Método que convierte de Blob a Bitmap
    public static Bitmap blobABitmap(Blob b, int width, int height){
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(width, height,config);
        try{
            int blobLength = (int) b.length();
            byte[] blobAsBytes = b.getBytes(1, blobLength);
            bitmap = BitmapFactory.decodeByteArray(blobAsBytes, 0, blobAsBytes.length);
        }catch (Exception e){
            return null;
        }
        return bitmap;
    }

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

    //Método que convierte de Bitmap a Byte[]
    public static byte[] bitmapAByte(Bitmap bitmap){
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBuffer);
        byteBuffer.rewind();
        return byteBuffer.array();
    }

    //Método que convierte de Byte[] a Bitmap
    public static Bitmap bytesABitmap(byte[] b){
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(200, 200,config);
        try{
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        } catch (Exception e){

        }
        return bitmap;
    }
}
