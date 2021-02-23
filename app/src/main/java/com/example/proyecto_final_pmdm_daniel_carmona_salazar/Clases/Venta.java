package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Venta implements Serializable {
    //ATRIBUTOS
    private int idVenta;
    private int númeroVenta;
    private Empleado empleado;
    private Videojuego videojuego;

    //CONSTRUCTOR
    public Venta(int idVenta, int númeroVenta, Empleado empleado, Videojuego videojuego) {
        this.idVenta = idVenta;
        this.númeroVenta = númeroVenta;
        this.empleado = empleado;
        this.videojuego = videojuego;
    }

    public Venta(int númeroVenta, Empleado empleado, Videojuego videojuego) {
        this.númeroVenta = númeroVenta;
        this.empleado = empleado;
        this.videojuego = videojuego;
    }

    public Venta() {
        this.idVenta = 0;
        this.númeroVenta = 0;
        this.empleado = new Empleado();
        this.videojuego = new Videojuego();
    }

    public Venta(int númeroVenta){
        this.númeroVenta = númeroVenta;
        this.empleado = null;
        this.videojuego = null;
    }

    //GETTERS & SETTERS
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getNúmeroVenta() {
        return númeroVenta;
    }

    public void setNúmeroVenta(int númeroVenta) {
        this.númeroVenta = númeroVenta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }

    //HASH CODE & EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta)) return false;
        Venta venta = (Venta) o;
        return idVenta == venta.idVenta;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idVenta);
    }

    //TO STRING
    @Override
    public String toString() {
        return "Número de venta: " + númeroVenta;
    }
}