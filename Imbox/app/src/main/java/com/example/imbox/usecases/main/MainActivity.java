package com.example.imbox.usecases.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imbox.model.Perro;
import com.example.imbox.databinding.ActivityMainBinding;
import com.example.imbox.recycler.AdaptadorDatos;
import com.example.imbox.usecases.viewPager.ViewPagerRouter;

import java.util.ArrayList;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class MainActivity extends AppCompatActivity implements AdaptadorDatos.OnPerroClickListener{

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private static final String JSON_URL = "https://dog.ceo/api/breeds/list/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Lista de Razas");
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //solo una vez,
        //cargarDatosJson()
        ArrayList<Perro> lista = leerBBDD();
        setUpRecycler(lista);

    }

    //Esto solo se llama al principio para rellenar la BBDD en local
    private void cargarDatosJson() {
        //guardamos los perros
        setUp();
        //guardamos las fotos
        setUpFotos();
    }

    private void setUpRecycler(ArrayList<Perro> lista) {
        binding.recyclerID.setLayoutManager(new GridLayoutManager(this,2));
        AdaptadorDatos adapter = new AdaptadorDatos(lista,this);
        binding.recyclerID.setAdapter(adapter);
    }

    private void setUp() {
        viewModel.guardarEnBBDD(this, JSON_URL);
    }
    private void setUpFotos() {

        ArrayList<Perro> perros = viewModel.leerDatos(this);
        viewModel.fotos(perros,this);
    }



    private ArrayList<Perro> leerBBDD() {
        return viewModel.leerDatos(this);

    }


    @Override
    public void onItemClick(Perro perro) {
        new ViewPagerRouter(perro).lanzar(this);
        Toast.makeText(this,perro.getNombreCompleto(),Toast.LENGTH_LONG).show();
    }
}