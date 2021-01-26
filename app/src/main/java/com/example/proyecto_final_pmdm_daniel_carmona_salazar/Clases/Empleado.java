package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Empleado {
    //ATRIBUTOS
    private int idEmpleado;
    private String nombreEmpleado;
    private String apellidosEmpleado;
    private String domicilioEmpleado;
    private String telefonoEmpleado;

    //CONSTRUCTOR
    public Empleado(int idEmpleado, String nombreEmpleado, String apellidosEmpleado, String domicilioEmpleado, String telefonoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidosEmpleado = apellidosEmpleado;
        this.domicilioEmpleado = domicilioEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public Empleado() {
        this.idEmpleado = 0;
        this.nombreEmpleado = "";
        this.apellidosEmpleado = "";
        this.domicilioEmpleado = "";
        this.telefonoEmpleado = "";
    }

    //GETTERS & SETTERS
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidosEmpleado() {
        return apellidosEmpleado;
    }

    public void setApellidosEmpleado(String apellidosEmpleado) {
        this.apellidosEmpleado = apellidosEmpleado;
    }

    public String getDomicilioEmpleado() {
        return domicilioEmpleado;
    }

    public void setDomicilioEmpleado(String domicilioEmpleado) {
        this.domicilioEmpleado = domicilioEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    //HASHCODE & EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return idEmpleado == empleado.idEmpleado;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idEmpleado);
    }

    //TO STRING
    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombreEmpleado='" + nombreEmpleado + '\'' + ", apellidosEmpleado='" + apellidosEmpleado + '\'' + ", domicilioEmpleado='" + domicilioEmpleado + '\'' + ", telefonoEmpleado='" + telefonoEmpleado + '\'' + '}';
    }
}