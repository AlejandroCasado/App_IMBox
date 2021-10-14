package com.example.imbox.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imbox.R;
import com.example.imbox.model.Perro;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos> {

    ArrayList<Perro> listDatos;
    OnPerroClickListener itemCliclListener;

    public interface  OnPerroClickListener{
        void onItemClick(Perro perro);
    }
    public AdaptadorDatos(ArrayList<Perro> listaDatos,OnPerroClickListener itemClickListener){
        this.itemCliclListener = itemClickListener;
        this.listDatos = listaDatos;
    }

    @NonNull
    @Override
    public AdaptadorDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perro,null,false);
        return  new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView dato;
        ImageView image;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = itemView.findViewById(R.id.tvNombre);
            image = itemView.findViewById(R.id.imImagen);

        }

        public void asignarDatos(Perro perro) {
            Picasso.get().load(perro.getUrlImagen_uno()).into(image);
            dato.setText(perro.getNombreCompleto());

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemCliclListener.onItemClick(perro);

                }
            });

        }

    }
}
