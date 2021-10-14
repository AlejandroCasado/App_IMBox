package com.example.imbox.usecases.main;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.imbox.domain.DBFuncion;
import com.example.imbox.model.Perro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<String> loadingList = new MutableLiveData<String>();


    public ArrayList<Perro> guardarEnBBDD(MainActivity mainActivity, String jsonUrl) {
        ArrayList<Perro> lista = new ArrayList<Perro>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //aqui ya tengo el json
                        try {
                            //guardamos en la BBDD local
                            DBFuncion dbFuncion = new DBFuncion(mainActivity);

                            JSONObject obj = new JSONObject(response);
                            JSONObject datosArray = obj.getJSONObject("message");
                            JSONArray array = datosArray.names();
                            for (int i = 0; i < array.length(); i++) {
                                String nombreOriginal = array.getString(i);
                                Object subRazas = datosArray.get(array.getString(i));
                                JSONArray arraySubrazas = (JSONArray) subRazas;
                                if (arraySubrazas.length() == 0) { // si no contiene razas

                                    //

                                    Perro p = new Perro(nombreOriginal, "");
                                    lista.add(p);
                                    //insertamos en BBDD
                                    dbFuncion.insertarPerro(p);
                                    //

                                    // Perro p = new Perro(nombreOriginal, "");
                                    // lista.add(p);
                                    //insertamos en BBDD

                                    // dbFuncion.insertarPerro(p);

                                } else { // si contiene mas de una o una raza
                                    for (int s = 0; s < arraySubrazas.length(); s++) {
                                        // recorremos las subRazas
                                        String subs = arraySubrazas.getString(s);
                                        System.out.println(subs);
                                        Perro p = new Perro(nombreOriginal, arraySubrazas.getString(s));
                                        lista.add(p);
                                        //insertamos en BBDD
                                        dbFuncion.insertarPerro(p);

                                    }
                                }
                            }

                            //setUpleerFotos(lista);
                            //AdaptadorDatos adapter = new AdaptadorDatos(lista);
                            // binding.recyclerID.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainActivity, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        requestQueue.add(stringRequest);

        return lista;
    }

    public void fotos(ArrayList<Perro> lista, MainActivity mainActivity) {
        ArrayList<Perro> listaFinal = new ArrayList<Perro>();
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getRaza() == "") { //no tiene subRaza
                String nombre = lista.get(i).getNombre();
                Integer id = lista.get(i).getId();
                int finalI = i;
                String URL = "https://dog.ceo/api/breed/" + nombre + "/images/random/4";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //aqui ya tengo el json
                        try {
                            //guardamos en la BBDD local
                            DBFuncion dbFuncion = new DBFuncion(mainActivity);
                            //JSONObject obj = new JSONObject(response);
                            JSONArray datosArray = response.names();
                            String urlImg = response.getString("message");
                            JSONArray im = response.getJSONArray("message");
                            String im_uno;
                            String im_dos;
                            String im_tres;
                            String im_cuatro ;
                            switch (im.length()) {
                                case 1:
                                     im_uno = (String) im.get(0);
                                    Perro p_uno = new Perro(im_uno, "", "", "");
                                    listaFinal.add(p_uno);
                                    dbFuncion.insertarFotos(p_uno, id);

                                    break;
                                case 2:
                                     im_uno = (String) im.get(0);
                                     im_dos = (String) im.get(1);
                                    Perro p_dos = new Perro(im_uno, im_dos, "", "");
                                    listaFinal.add(p_dos);
                                    dbFuncion.insertarFotos(p_dos, id);

                                    break;
                                case 3:
                                     im_uno = (String) im.get(0);
                                     im_dos = (String) im.get(1);
                                     im_tres = (String) im.get(2);
                                    Perro p_tres = new Perro(im_uno, im_dos, im_tres, "");
                                    listaFinal.add(p_tres);
                                    dbFuncion.insertarFotos(p_tres, id);

                                    break;
                                case 4:
                                     im_uno = (String) im.get(0);
                                     im_dos = (String) im.get(1);
                                     im_tres = (String) im.get(2);
                                     im_cuatro = (String) im.get(3);
                                    Perro p_cuatro = new Perro(im_uno, im_dos, im_tres, im_cuatro);
                                    listaFinal.add(p_cuatro);
                                    dbFuncion.insertarFotos(p_cuatro, id);
                                    break;
                            }



                            //lista.get(finalI).setUrlImagen(urlImg);

                            //AdaptadorDatos adapter = new AdaptadorDatos(lista);
                            // binding.recyclerID.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
                requestQueue.add(jsonObjectRequest);


            } else {
                String nombre = lista.get(i).getNombre();
                String raza = lista.get(i).getRaza();
                Integer id = lista.get(i).getId();
                String URL = "https://dog.ceo/api/breed/" + nombre + "/" + raza + "/images/random/4";
                ///

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //aqui ya tengo el json
                        try {
                            //guardamos en la BBDD local
                            DBFuncion dbFuncion = new DBFuncion(mainActivity);
                            //JSONObject obj = new JSONObject(response);
                            JSONArray datosArray = response.names();
                            String urlImg = response.getString("message");
                            JSONArray im = response.getJSONArray("message");
                            ///
                            String im_uno;
                            String im_dos;
                            String im_tres;
                            String im_cuatro ;
                            switch (im.length()) {
                                case 1:
                                    im_uno = (String) im.get(0);
                                    Perro p_uno = new Perro(im_uno, "", "", "");
                                    listaFinal.add(p_uno);
                                    dbFuncion.insertarFotos(p_uno, id);

                                    break;
                                case 2:
                                    im_uno = (String) im.get(0);
                                    im_dos = (String) im.get(1);
                                    Perro p_dos = new Perro(im_uno, im_dos, "", "");
                                    listaFinal.add(p_dos);
                                    dbFuncion.insertarFotos(p_dos, id);

                                    break;
                                case 3:
                                    im_uno = (String) im.get(0);
                                    im_dos = (String) im.get(1);
                                    im_tres = (String) im.get(2);
                                    Perro p_tres = new Perro(im_uno, im_dos, im_tres, "");
                                    listaFinal.add(p_tres);
                                    dbFuncion.insertarFotos(p_tres, id);

                                    break;
                                case 4:
                                    im_uno = (String) im.get(0);
                                    im_dos = (String) im.get(1);
                                    im_tres = (String) im.get(2);
                                    im_cuatro = (String) im.get(3);
                                    Perro p_cuatro = new Perro(im_uno, im_dos, im_tres, im_cuatro);
                                    listaFinal.add(p_cuatro);
                                    dbFuncion.insertarFotos(p_cuatro, id);
                                    break;
                            }

                            //lista.get(finalI).setUrlImagen(urlImg);

                            //AdaptadorDatos adapter = new AdaptadorDatos(lista);
                            // binding.recyclerID.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
                requestQueue.add(jsonObjectRequest);

                ///

            }
        }

    }

    public ArrayList<Perro> leerDatos(MainActivity mainActivity) {
        DBFuncion dbFuncion = new DBFuncion(mainActivity);
       return dbFuncion.leerDatos();
    }
}

