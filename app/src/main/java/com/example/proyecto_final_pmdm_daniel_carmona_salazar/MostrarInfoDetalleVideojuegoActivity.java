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
    private byte[] arrayLogoVideojuegoDetalle = null;
    private ImageView imgLogoVideojuegoDetalle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_detalle_videojuego);
        txtTítuloVideojuegoDetalle = (TextView) findViewById(R.id.txtTítuloVideojuegoDetalle);
        txtPEGIVideojuegoDetalle = (TextView) findViewById(R.id.txtPEGIVideojuegoDetalle);
        txtGéneroVideojuegoDetalle = (TextView) findViewById(R.id.txtGéneroVideojuegoDetalle);
        imgLogoVideojuegoDetalle = (ImageView) findViewById(R.id.imgVideojuegoDetalle);

        Intent intent = getIntent();
        if(intent != null){
            videojuego = (Videojuego) intent.getSerializableExtra(EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN);
            if(videojuego != null){
                String título = videojuego.getTítuloVideojuego();
                if (título.length() > 35){
                    txtTítuloVideojuegoDetalle.setText(título.substring(0, 34) + "...");
                }
                txtPEGIVideojuegoDetalle.setText(String.valueOf(videojuego.getPegiVideojuego()));
                txtGéneroVideojuegoDetalle.setText(videojuego.getGéneroVideojuego());
            }

            arrayLogoVideojuegoDetalle = intent.getByteArrayExtra(EXTRA_IMAGEN_VIDEOJUEGO);
            if(arrayLogoVideojuegoDetalle != null){
                imgLogoVideojuegoDetalle.setImageBitmap(bytesABitmap(arrayLogoVideojuegoDetalle));
            }
        }
    }
}