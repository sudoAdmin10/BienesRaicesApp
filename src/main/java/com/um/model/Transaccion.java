package com.um.model;

public class Transaccion {
    private int ind_usuario;
    private int ind_agencia;
    private int ind_propiedad;
    private String precio;
    private String fecha;
    private String tipo_transaccion;

    public int getInd_usuario() {
        return ind_usuario;
    }

    public void setInd_usuario(int ind_usuario) {
        this.ind_usuario = ind_usuario;
    }

    public int getInd_agencia() {
        return ind_agencia;
    }

    public void setInd_agencia(int ind_agencia) {
        this.ind_agencia = ind_agencia;
    }

    public int getInd_propiedad() {
        return ind_propiedad;
    }

    public void setInd_propiedad(int ind_propiedad) {
        this.ind_propiedad = ind_propiedad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_transaccion() {
        return tipo_transaccion;
    }

    public void setTipo_transaccion(String tipo_transaccion) {
        this.tipo_transaccion = tipo_transaccion;
    }

}
