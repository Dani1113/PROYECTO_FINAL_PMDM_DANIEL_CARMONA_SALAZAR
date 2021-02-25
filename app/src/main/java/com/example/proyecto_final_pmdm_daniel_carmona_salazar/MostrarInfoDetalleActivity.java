package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.Utilidades.bytesABitmap;

public class MostrarInfoDetalleActivity extends AppCompatActivity {

    private Empleado empleado = null;
    private Videojuego videojuego = null;
    private Venta venta = null;
    private TextView txtNVentaDetalle = null;
    private TextView txtTítuloDetalle = null;
    private TextView txtPEGIDetalle = null;
    private TextView txtGéneroDetalle = null;
    private byte[] logoDetalle = null;
    private ImageView imgLogoDetalle = null;
    private TextView txtNombreDetalle = null;
    private TextView txtApellidosDetalle = null;
    private TextView txtDomicilioDetalle = null;
    private TextView txtTeléfonoDetalle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_detalle);
        txtNVentaDetalle = (TextView) findViewById(R.id.txtNVentaDetalle);
        txtTítuloDetalle = (TextView) findViewById(R.id.txtTítuloDetalle);
        txtPEGIDetalle = (TextView) findViewById(R.id.txtPEGIDetalle);
        txtGéneroDetalle = (TextView) findViewById(R.id.txtGéneroDetalle);
        imgLogoDetalle = (ImageView) findViewById(R.id.imgLogoDetalle);
        txtNombreDetalle = (TextView) findViewById(R.id.txtNombreDetalle);
        txtApellidosDetalle = (TextView) findViewById(R.id.txtApellidosDetalle);
        txtDomicilioDetalle = (TextView) findViewById(R.id.txtDomicilioDetalle);
        txtTeléfonoDetalle = (TextView) findViewById(R.id.txtTeléfonoDetalle);

        Intent intent = getIntent();
        if(intent != null){
            venta = (Venta) intent.getSerializableExtra(ActualizarVentaActivity1.EXTRA_OBJETO_VENTA_SIN_VIDEOJUEGO_NI_EMPLEADO);
            if(venta != null){
                txtNVentaDetalle.setText(String.valueOf(venta.getNúmeroVenta()));
            }

            logoDetalle = intent.getByteArrayExtra(ActualizarVentaActivity1.EXTRA_IMAGEN_VIDEOJUEGO);
            if(logoDetalle != null){
                imgLogoDetalle.setImageBitmap(bytesABitmap(logoDetalle));
            }

            videojuego = (Videojuego) intent.getSerializableExtra(ActualizarVentaActivity1.EXTRA_OBJETO_VIDEOJUEGO_SIN_IMAGEN);
            if(videojuego != null){
                String titulo = videojuego.getTítuloVideojuego();
                if (titulo.length() > 15){
                    txtTítuloDetalle.setText(titulo.substring(0, 14) + "...");
                }else{
                    txtTítuloDetalle.setText(titulo);
                }
                txtPEGIDetalle.setText(String.valueOf(videojuego.getPegiVideojuego()));
                txtGéneroDetalle.setText(videojuego.getGéneroVideojuego());
            }

            empleado = (Empleado) intent.getSerializableExtra(ActualizarVentaActivity1.EXTRA_OBJETO_EMPLEADO);
            if(empleado != null){
                String nombre = empleado.getNombreEmpleado();
                if (nombre.length() > 15){
                    txtNombreDetalle.setText(nombre.substring(0, 14) + "...");
                }
                else {
                    txtNombreDetalle.setText(nombre);
                }

                String apellidos = empleado.getApellidosEmpleado();
                if (apellidos.length() > 15){
                    txtApellidosDetalle.setText(apellidos.substring(0, 14) + "...");
                }
                else {
                    txtApellidosDetalle.setText(apellidos);
                }

                String domicilio = empleado.getDomicilioEmpleado();
                if (domicilio.length() > 15){
                    txtDomicilioDetalle.setText(domicilio.substring(0, 14) + "...");
                }
                else {
                    txtDomicilioDetalle.setText(domicilio);
                }
                txtTeléfonoDetalle.setText(empleado.getTelefonoEmpleado());
            }
        }
    }
}