package com.example.imbox.model;
/*
 * Creada por: Alejandro Casado Benito, 2021
 */
public class Perro {

    private String nombre;
    private String raza;
    private String urlImagen_uno,urlImagen_dos,urlImagen_tres,urlImagen_cuatro;
    private Integer id;
    public Perro(String nombre, String raza){
        this.nombre = nombre;
        this.raza = raza;
    }
    public Perro(Integer id,String nombre, String raza){
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
    }
    public Perro(Integer id,String nombre, String raza,String urlImagen_uno, String urlImagen_dos, String urlImagen_tres, String urlImagen_cuatro){
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.urlImagen_uno = urlImagen_uno;
        this.urlImagen_dos = urlImagen_dos;
        this.urlImagen_tres = urlImagen_tres;
        this.urlImagen_cuatro= urlImagen_cuatro;

    }

    public Perro(String im_uno, String im_dos, String im_tres, String im_cuatro) {
        this.urlImagen_uno = im_uno;
        this.urlImagen_dos = im_dos;
        this.urlImagen_tres = im_tres;
        this.urlImagen_cuatro= im_cuatro;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombreCompleto(){
        return this.nombre + " " + this.raza;
    }

    public String getUrlImagen_uno() {
        return urlImagen_uno;
    }

    public void setUrlImagen_uno(String urlImagen_uno) {
        this.urlImagen_uno = urlImagen_uno;
    }

    public String getUrlImagen_dos() {
        return urlImagen_dos;
    }

    public void setUrlImagen_dos(String urlImagen_dos) {
        this.urlImagen_dos = urlImagen_dos;
    }

    public String getUrlImagen_tres() {
        return urlImagen_tres;
    }

    public void setUrlImagen_tres(String urlImagen_tres) {
        this.urlImagen_tres = urlImagen_tres;
    }

    public String getUrlImagen_cuatro() {
        return urlImagen_cuatro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUrlImagen_cuatro(String urlImagen_cuatro) {
        this.urlImagen_cuatro = urlImagen_cuatro;
    }
}
