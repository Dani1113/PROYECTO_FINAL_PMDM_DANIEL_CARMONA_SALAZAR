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

public class InsertarVentaActivity extends AppCompatActivity {

    private EditText edtTítulo = null;
    private EditText edtPEGI = null;
    private EditText edtGénero = null;
    private ImageView img = null;
    private EditText edtNombre = null;
    private EditText edtApellidos = null;
    private EditText edtDomicilio = null;
    private EditText edtTeléfono = null;
    private EditText edtNVenta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_venta);
        edtTítulo = (EditText) findViewById(R.id.edtTítuloV);
        edtPEGI = (EditText) findViewById(R.id.edtPEGIV);
        edtGénero = (EditText) findViewById(R.id.edtGéneroV);
        img = (ImageView) findViewById(R.id.idImagen);
        edtNombre = (EditText) findViewById(R.id.edtNombreE);
        edtApellidos = (EditText) findViewById(R.id.edtApellidoE);
        edtDomicilio = (EditText) findViewById(R.id.edtDomicilioE);
        edtTeléfono = (EditText) findViewById(R.id.edtTeléfonoE);
        edtNVenta = (EditText) findViewById(R.id.edtNúmeroV);
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
            img.setImageURI(ruta);
        }
    }

    public void insertarVenta(View view) {
        //Recupero el empleado del formulario
        String nombre = String.valueOf(edtNombre.getText());
        if(nombre.isEmpty()){
            edtNombre.setError("Debes introducir el nombre del empleado");
        }
        String apellidos = String.valueOf(edtApellidos.getText());
        if(apellidos.isEmpty()){
            edtApellidos.setError("Debes introducir los apellidos del empleado");
        }
        String domicilio = String.valueOf(edtDomicilio.getText());
        if(domicilio.isEmpty()){
            edtDomicilio.setError("Debes introducir el domicilio del empleado");
        }
        String teléfono = String.valueOf(edtTeléfono.getText());
        if(teléfono.isEmpty()){
            edtTeléfono.setError("Debes introducir el teléfono del empleado");
        }
        Empleado empleado = new Empleado(nombre, apellidos, domicilio, teléfono);

        //Recupero el videojuego del formulario
        String título = String.valueOf(edtTítulo.getText());
        if(título.isEmpty()) {
            edtTítulo.setError("Debes introducir el título del videojuego");
        }
        int pegi = Integer.valueOf(String.valueOf(edtPEGI.getText()));
        if(String.valueOf(edtPEGI.getText()).isEmpty()) {
            edtPEGI.setError("Debes introducir el PEGI del videojuego");
        }else if(pegi <= 0){
            edtPEGI.setError("El valor introducido debe ser mayor que 0");
        }
        String género = String.valueOf(edtGénero.getText());
        if(género.isEmpty()) {
            edtGénero.setError("Debes introducir el género del videojuego");
        }
        img.buildDrawingCache();
        Bitmap imagen = img.getDrawingCache();
        if(imagen == null){
            Toast.makeText(this, "Recuerda que no has seleccionado ninguna imagen", Toast.LENGTH_SHORT).show();
        }
        Videojuego videojuego = new Videojuego(título, pegi, género, imagen);

        //Recupero la venta del formulario
        int nVenta = Integer.valueOf(String.valueOf(edtNVenta.getText()));
        if(String.valueOf(edtNVenta.getText()).isEmpty()){
            edtNVenta.setError("Debes introducir el número de la venta");
        }else if(nVenta <= 0) {
            edtNVenta.setError("El valor introducido debe ser mayor que 0");
        }
        AlertDialog.Builder alertaConfirmación = new AlertDialog.Builder(this);
        alertaConfirmación.setTitle("¿Desea crear la venta?");
        alertaConfirmación.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Venta venta = new Venta(nVenta, empleado, videojuego);
                boolean inserciónOK = VentaController.insertarVenta(venta);
                if(inserciónOK) {
                    mostrarToast("Venta creada correctamente");
                }
                else {
                    mostrarToast("Error al crear la venta");
                }
            }
        });
        alertaConfirmación.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("Creación de la venta cancelada");
            }
        });
        alertaConfirmación.show();
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}