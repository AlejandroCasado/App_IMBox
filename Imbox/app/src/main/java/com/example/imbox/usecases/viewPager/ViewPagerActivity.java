package com.example.imbox.usecases.viewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.imbox.R;
import com.example.imbox.databinding.ActivityViewPagerBinding;
import com.example.imbox.usecases.viewPager.fragments.PaginaFragmento;
import com.example.imbox.usecases.viewPager.fragments.PaginaFragmentoDos;
import com.example.imbox.usecases.viewPager.fragments.PaginaFragmentoTres;

import java.util.ArrayList;
import java.util.List;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ActivityViewPagerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPagerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //recuperamos datos
        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString("nombre");
        String im_uno = bundle.getString("im_dos");
        String im_dos = bundle.getString("im_tres");
        String im_tres = bundle.getString("im_cuatro");
        setTitle(nombre);

        List<Fragment> list = new ArrayList<>();

        //foto uno
        Bundle datos = new Bundle();
        datos.putString("im_uno", im_uno);
        PaginaFragmento paginaFragmento = new PaginaFragmento().PaginaFragmento(datos);
        list.add(paginaFragmento);


        //foto dos
        Bundle datos_dos = new Bundle();
        datos_dos.putString("im_dos", im_dos);
        PaginaFragmentoDos paginaFragmento_dos = new PaginaFragmentoDos().PaginaFragmentoDos(datos_dos);
        list.add(paginaFragmento_dos);


        //foto tres
        Bundle datos_tres = new Bundle();
        datos_tres.putString("im_tres", im_tres);
        PaginaFragmentoTres paginaFragmento_tres = new PaginaFragmentoTres().paginaFragmentoTres(datos_tres);
        list.add(paginaFragmento_tres);


        pager = findViewById(R.id.pager);
        pagerAdapter = new AdaptadorPaginas(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);

    }
}