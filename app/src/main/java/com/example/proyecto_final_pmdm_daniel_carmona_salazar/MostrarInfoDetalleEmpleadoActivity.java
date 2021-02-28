package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.ActualizarVentaActivity1.EXTRA_OBJETO_EMPLEADO;

public class MostrarInfoDetalleEmpleadoActivity extends AppCompatActivity {

    private Empleado empleado;
    private TextView txtNombreDetalleEmpleado = null;
    private TextView txtApellidosDetalleEmpleado = null;
    private TextView txtDomicilioDetalleEmpleado = null;
    private TextView txtTeléfonoDetalleEmpleado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info_detalle_empleado);
        txtNombreDetalleEmpleado = (TextView) findViewById(R.id.txtNombreDetalleEmpleado);
        txtApellidosDetalleEmpleado = (TextView) findViewById(R.id.txtApellidosDetalleEmpleado);
        txtDomicilioDetalleEmpleado = (TextView) findViewById(R.id.txtDomicilioDetalleEmpleado);
        txtTeléfonoDetalleEmpleado = (TextView) findViewById(R.id.txtTeléfonoDetalleEmpleado);

        Intent intent = getIntent();
        if(intent != null){
            empleado = (Empleado) intent.getSerializableExtra(EXTRA_OBJETO_EMPLEADO);
            if(empleado != null) {
                String nombre = empleado.getNombreEmpleado();
                if (nombre.length() > 35) {
                    txtNombreDetalleEmpleado.setText(nombre.substring(0, 34) + "...");
                } else {
                    txtNombreDetalleEmpleado.setText(nombre);
                }

                String apellidos = empleado.getApellidosEmpleado();
                if (apellidos.length() > 35) {
                    txtApellidosDetalleEmpleado.setText(apellidos.substring(0, 34) + "...");
                } else {
                    txtApellidosDetalleEmpleado.setText(apellidos);
                }

                String domicilio = empleado.getDomicilioEmpleado();
                if (domicilio.length() > 35) {
                    txtDomicilioDetalleEmpleado.setText(domicilio.substring(0, 34) + "...");
                } else {
                    txtDomicilioDetalleEmpleado.setText(domicilio);
                }

                txtTeléfonoDetalleEmpleado.setText(empleado.getTelefonoEmpleado());
            }
        }
    }
}