package com.darthleonard.miautomovil;

/**
 * Created by Darth on 18/12/2016.
 */
public class Registro {
    private int id;
    private String fecha;
    private String precio;
    private String litros;
    private String kilometros;

    public Registro(String fecha, String precio, String litros, String kilometros) {
        this.fecha = fecha;
        this.precio = precio;
        this.litros = litros;
        this.kilometros = kilometros;
    }

    public Registro(int id, String fecha, String precio, String litros, String kilometros) {
        this.id = id;
        this.fecha = fecha;
        this.precio = precio;
        this.litros = litros;
        this.kilometros = kilometros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getLitros() {
        return litros;
    }

    public void setLitros(String litros) {
        this.litros = litros;
    }

    public String getKilometros() {
        return kilometros;
    }

    public void setKilometros(String kilometros) {
        this.kilometros = kilometros;
    }
}
