package com.example.imbox.usecases.viewPager;

import android.content.Context;
import android.content.Intent;

import com.example.imbox.model.Perro;
import com.example.imbox.usecases.base.BaseRouter;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class ViewPagerRouter implements BaseRouter {
    Perro perro;
    public ViewPagerRouter(Perro perro) {
        this.perro = perro;
    }

    @Override
    public Intent intent(Context activity) {
        return new Intent(activity, ViewPagerActivity.class).
                putExtra("nombre",perro.getNombreCompleto()).
                putExtra("im_dos", perro.getUrlImagen_dos()).
                putExtra("im_tres", perro.getUrlImagen_tres()).
                putExtra("im_cuatro", perro.getUrlImagen_cuatro());


    }
}
