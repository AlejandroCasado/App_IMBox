package com.example.imbox.usecases.viewPager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.imbox.R;
import com.example.imbox.databinding.PageBinding;
import com.example.imbox.databinding.PageDosBinding;
import com.squareup.picasso.Picasso;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class PaginaFragmentoDos extends Fragment {

    String url;
    private PageDosBinding binding;
    public PaginaFragmentoDos(Bundle datos) {
    }

    public PaginaFragmentoDos PaginaFragmentoDos(Bundle datos) {
        PaginaFragmentoDos paginaFragmentoDos = new PaginaFragmentoDos();
        if(datos != null){
            paginaFragmentoDos.setArguments(datos);
        }
        return paginaFragmentoDos;
    }

    public PaginaFragmentoDos() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup raizView = (ViewGroup) inflater.inflate(R.layout.page_dos,container,false);
        binding = PageDosBinding.bind(raizView);
        Bundle bundle = getArguments();
        if(bundle != null){
            url = bundle.getString("im_dos");

            if(url == ""){
                binding.tvNoMasFotos.setVisibility(View.VISIBLE);
                binding.imagen.setVisibility(View.GONE);

            }else {
                Picasso.get().load(url).into(binding.imagen);
            }
        }

        return raizView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
