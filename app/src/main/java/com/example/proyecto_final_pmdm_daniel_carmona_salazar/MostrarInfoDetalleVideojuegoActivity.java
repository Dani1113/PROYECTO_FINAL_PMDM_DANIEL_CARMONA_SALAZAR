package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_IMAGEN_VIDEOJUEGO;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN;
import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.Utilidades.bytesABitmap;

public class MostrarInfoDetalleVideojuegoActivity extends AppCompatActivity {

    private Videojuego videojuego = null;
    private TextView txtTítuloVideojuegoDetalle = null;
    private TextView txtPEGIVideojuegoDetalle = null;
    private TextView txtGéneroVideojuegoDetalle = null;
    private ImageView imgVideojuegoDetalle = null;
    private byte[] arrayImgVideojuegoDetalle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_detalle_videojuego);
        txtTítuloVideojuegoDetalle = (TextView) findViewById(R.id.txtTítuloVideojuegoDetalle);
        txtPEGIVideojuegoDetalle = (TextView) findViewById(R.id.txtPEGIVideojuegoDetalle);
        txtGéneroVideojuegoDetalle = (TextView) findViewById(R.id.txtGéneroVideojuegoDetalle);
        imgVideojuegoDetalle = (ImageView) findViewById(R.id.imgVideojuegoDetalle);

        Intent intent = getIntent();
        if(intent != null){
            videojuego = (Videojuego) intent.getSerializableExtra(EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN);
            if(videojuego != null){
                String título = String.valueOf(videojuego.getTítuloVideojuego());
                if (título.length() > 50) {
                    txtTítuloVideojuegoDetalle.setText(título.substring(0, 49) + "...");
                }else {
                    txtTítuloVideojuegoDetalle.setText(título);
                }
                txtPEGIVideojuegoDetalle.setText(String.valueOf(videojuego.getPegiVideojuego()));
                txtGéneroVideojuegoDetalle.setText(videojuego.getGéneroVideojuego());
            }

            arrayImgVideojuegoDetalle = intent.getByteArrayExtra(EXTRA_IMAGEN_VIDEOJUEGO);
            if(arrayImgVideojuegoDetalle != null){
                imgVideojuegoDetalle.setImageBitmap(bytesABitmap(arrayImgVideojuegoDetalle));
            }
        }
    }
}