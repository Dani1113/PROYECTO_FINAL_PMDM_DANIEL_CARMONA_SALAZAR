package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Videojuego {
    //ATRIBUTOS
    private int idVideojuego;
    private String títuloVideojuego;
    private int pegiVideojuego;
    private String géneroVideojuego;
    private String logoVideojuego;

    //CONSTRUCTOR
    public Videojuego(int idVideojuego, String títuloVideojuego, int pegiVideojuego, String géneroVideojuego, String logoVideojuego) {
        this.idVideojuego = idVideojuego;
        this.títuloVideojuego = títuloVideojuego;
        this.pegiVideojuego = pegiVideojuego;
        this.géneroVideojuego = géneroVideojuego;
        this.logoVideojuego = logoVideojuego;
    }

    public Videojuego() {
        this.idVideojuego = 0;
        this.títuloVideojuego = "";
        this.pegiVideojuego = 0;
        this.géneroVideojuego = "";
        this.logoVideojuego = "";
    }

    //GETTTERS & SETTERS
    public int getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getTítuloVideojuego() {
        return títuloVideojuego;
    }

    public void setTítuloVideojuego(String títuloVideojuego) {
        this.títuloVideojuego = títuloVideojuego;
    }

    public int getPegiVideojuego() {
        return pegiVideojuego;
    }

    public void setPegiVideojuego(int pegiVideojuego) {
        this.pegiVideojuego = pegiVideojuego;
    }

    public String getGéneroVideojuego() {
        return géneroVideojuego;
    }

    public void setGéneroVideojuego(String géneroVideojuego) {
        this.géneroVideojuego = géneroVideojuego;
    }

    public String getLogoVideojuego() {
        return logoVideojuego;
    }

    public void setLogoVideojuego(String logoVideojuego) {
        this.logoVideojuego = logoVideojuego;
    }

    //HASH CODE & EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Videojuego)) return false;
        Videojuego that = (Videojuego) o;
        return idVideojuego == that.idVideojuego;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idVideojuego);
    }

    //TO STRING
    @Override
    public String toString() {
        return "Videojuego{" + "idVideojuego=" + idVideojuego + ", títuloVideojuego='" + títuloVideojuego + '\'' + ", pegiVideojuego=" + pegiVideojuego + ", géneroVideojuego='" + géneroVideojuego + '\'' + ", logoVideojuego='" + logoVideojuego + '\'' + '}';
    }
}