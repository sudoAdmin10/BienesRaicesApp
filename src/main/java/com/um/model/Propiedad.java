package com.um.model;

public class Propiedad {
    private int id;
    private String precio;
    private int tipo;
    private String descripcion;
    private String dimensiones;
    private String num_wc;
    private String num_habitaciones;
    private String num_cars;
    private String direccion;
    private int estado_propiedad;
    private int propietario;
    private String imagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getNum_wc() {
        return num_wc;
    }

    public void setNum_wc(String num_wc) {
        this.num_wc = num_wc;
    }

    public String getNum_habitaciones() {
        return num_habitaciones;
    }

    public void setNum_habitaciones(String num_habitaciones) {
        this.num_habitaciones = num_habitaciones;
    }

    public String getNum_cars() {
        return num_cars;
    }

    public void setNum_cars(String num_cars) {
        this.num_cars = num_cars;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado_Propiedad() {
        return estado_propiedad;
    }

    public void setEstado_Propiedad(int estado) {
        this.estado_propiedad = estado;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
