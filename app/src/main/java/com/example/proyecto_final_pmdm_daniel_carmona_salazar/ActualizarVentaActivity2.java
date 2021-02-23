package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Venta;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VentaController;

import static com.example.proyecto_final_pmdm_daniel_carmona_salazar.Utilidades.Utilidades.bytesABitmap;

public class ActualizarVentaActivity2 extends AppCompatActivity {

    private Venta vSeleccionada = null;
    private byte[] byteArrayLogo = null;
    private EditText edtTítuloVA = null;
    private EditText edtPEGIVA = null;
    private EditText edtGéneroVA = null;
    private ImageView imgA = null;
    private EditText edtNombreEA = null;
    private EditText edtApellidosEA = null;
    private EditText edtDomicilioEA = null;
    private EditText edtTeléfonoEA = null;
    private EditText edtNVentaA = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_venta2);
        edtTítuloVA = (EditText) findViewById(R.id.edtTítuloVA);
        edtPEGIVA = (EditText) findViewById(R.id.edtPEGIVA);
        edtGéneroVA = (EditText) findViewById(R.id.edtGéneroVA);
        imgA = (ImageView) findViewById(R.id.idImagenA);
        edtNombreEA = (EditText) findViewById(R.id.edtNombreEA);
        edtApellidosEA = (EditText) findViewById(R.id.edtApellidosEA);
        edtDomicilioEA = (EditText) findViewById(R.id.edtDomicilioEA);
        edtTeléfonoEA = (EditText) findViewById(R.id.edtTeléfonoEA);
        edtNVentaA = (EditText) findViewById(R.id.edtNVentaA);
        Intent intent = getIntent();
        if(intent != null){
            vSeleccionada = (Venta) intent.getSerializableExtra(ActualizarVentaActivity1.EXTRA_OBJETO_VENTA);
            if(vSeleccionada != null){
                edtTítuloVA.setText(vSeleccionada.getVideojuego().getTítuloVideojuego());
                edtPEGIVA.setText(String.valueOf(vSeleccionada.getVideojuego().getPegiVideojuego()));
                edtGéneroVA.setText(vSeleccionada.getVideojuego().getGéneroVideojuego());
                edtNombreEA.setText(vSeleccionada.getEmpleado().getNombreEmpleado());
                edtApellidosEA.setText(vSeleccionada.getEmpleado().getApellidosEmpleado());
                edtDomicilioEA.setText(vSeleccionada.getEmpleado().getDomicilioEmpleado());
                edtTeléfonoEA.setText(vSeleccionada.getEmpleado().getTelefonoEmpleado());
                edtNVentaA.setText(String.valueOf(vSeleccionada.getNúmeroVenta()));
            }
            byteArrayLogo = intent.getByteArrayExtra(ActualizarVentaActivity1.EXTRA_IMAGEN_VENTA);
            if(byteArrayLogo != null){
                imgA.setImageBitmap(bytesABitmap(byteArrayLogo));
            }
        }
    }

    public void examinarImagen(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicación"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri ruta = data.getData();
            imgA.setImageURI(ruta);
        }
    }

    public void actualizarVentaActivity2(View view) {
        //Recupero el empleado del formulario
        String nombre = String.valueOf(edtNombreEA.getText());
        if(nombre.isEmpty()){
            edtNombreEA.setError("Debes introducir el nombre del empleado");
        }
        String apellidos = String.valueOf(edtApellidosEA.getText());
        if(apellidos.isEmpty()){
            edtApellidosEA.setError("Debes introducir los apellidos del empleado");
        }
        String domicilio = String.valueOf(edtDomicilioEA.getText());
        if(domicilio.isEmpty()){
            edtDomicilioEA.setError("Debes introducir el domicilio del empleado");
        }
        String teléfono = String.valueOf(edtTeléfonoEA.getText());
        if(teléfono.isEmpty()){
            edtTeléfonoEA.setError("Debes introducir el teléfono del empleado");
        }
        Empleado empleado = new Empleado(nombre, apellidos, domicilio, teléfono);

        //Recupero el videojuego del formulario
        String título = String.valueOf(edtTítuloVA.getText());
        if(título.isEmpty()) {
            edtTítuloVA.setError("Debes introducir el título del videojuego");
        }
        int pegi = Integer.valueOf(String.valueOf(edtPEGIVA.getText()));
        if(String.valueOf(edtPEGIVA.getText()).isEmpty()) {
            edtPEGIVA.setError("Debes introducir el PEGI del videojuego");
        }else if(pegi <= 0){
            edtPEGIVA.setError("El valor introducido debe ser mayor que 0");
        }
        String género = String.valueOf(edtGéneroVA.getText());
        if(género.isEmpty()) {
            edtGéneroVA.setError("Debes introducir el género del videojuego");
        }
        imgA.buildDrawingCache();
        Bitmap imagen = imgA.getDrawingCache();
        if(imagen == null){
            Toast.makeText(this, "Recuerda que no has seleccionado ninguna imagen", Toast.LENGTH_SHORT).show();
        }
        Videojuego videojuego = new Videojuego(título, pegi, género, imagen);

        //Recupero la venta del formulario
        int nVenta = Integer.valueOf(String.valueOf(edtNVentaA.getText()));
        if(String.valueOf(edtNVentaA.getText()).isEmpty()){
            edtNVentaA.setError("Debes introducir el número de la venta");
        }else if(nVenta <= 0) {
            edtNVentaA.setError("El valor introducido debe ser mayor que 0");
        }

        AlertDialog.Builder alertaConfirmación = new AlertDialog.Builder(this);
        alertaConfirmación.setTitle("¿Desea actualizar la venta?");
        alertaConfirmación.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Venta venta = new Venta(nVenta, empleado, videojuego);
                boolean actualizaciónOK = VentaController.actualizarVenta(venta);
                if(actualizaciónOK) {
                    ActualizarVentaActivity1.ventasAdapter.remove(vSeleccionada);
                    ActualizarVentaActivity1.ventasAdapter.add(venta);
                    mostrarToast("Venta actualizada correctamente");
                }
                else {
                    mostrarToast("Error al actualizar la venta");
                }
            }
        });
        alertaConfirmación.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("Actualización de la venta cancelada");
            }
        });
        alertaConfirmación.show();
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}