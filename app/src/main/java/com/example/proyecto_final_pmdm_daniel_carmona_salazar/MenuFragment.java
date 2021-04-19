package com.example.proyecto_final_pmdm_daniel_carmona_salazar;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Empleado;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.EmpleadoRecyclerView.ListaEmpleadosAdapter;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.Videojuego;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Clases.VideojuegoRecyclerView.ListaVideojuegosAdapter;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Controladores.VideojuegoController;
import com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos.ConfiguracionesGeneralesDB;

import java.util.ArrayList;
import java.util.Collections;

public class MenuFragment extends Fragment {

    public MenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}