package com.Valdez.app.rutas.models;

public class Cargamento {
    private Long id_cargamento;
    private Long ruta_id;
    private String descripcion;
    private Float peso;
    private Integer estatus;

    public Long getId_Cargamento() {
        return id_cargamento;
    }

    public void setId_Cargamento(Long id_Cargamento) {
        this.id_cargamento = id_Cargamento;
    }

    public Long getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(Long ruta_id) {
        this.ruta_id = ruta_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
}
