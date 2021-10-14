package com.example.imbox.usecases.base;

import android.content.Context;
import android.content.Intent;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public interface BaseRouter {

    Intent intent(Context activity);

    default void lanzar(Context activity){
        activity.startActivity(intent(activity));
    }


}
