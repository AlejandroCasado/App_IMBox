package com.example.imbox.usecases.viewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class AdaptadorPaginas extends FragmentStatePagerAdapter {


    private List<Fragment> listaFragmentos;
    public AdaptadorPaginas(@NonNull FragmentManager fragment, List<Fragment> listaFragmentos) {
        super(fragment);
        this.listaFragmentos = listaFragmentos;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listaFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return listaFragmentos.size();

    }
}
