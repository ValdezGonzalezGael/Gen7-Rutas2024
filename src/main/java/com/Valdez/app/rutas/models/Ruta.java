package com.Valdez.app.rutas.models;

import java.time.LocalDate;

public class Ruta {
    private Long id;
    private Long camionId;
    private  Long choferId;
    private Long direcionOrigen;
    private Long direccionDestino;
    private Float distancia;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegadaEstimada;
    private LocalDate fechaLlegadaReal;
    private Integer aTiempo;


//    gets y sets
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCamionId() {
        return camionId;
    }

    public void setCamionId(Long camionId) {
        this.camionId = camionId;
    }

    public Long getChoferId() {
        return choferId;
    }

    public void setChoferId(Long choferId) {
        this.choferId = choferId;
    }

    public Long getDirecionOrigen() {
        return direcionOrigen;
    }

    public void setDirecionOrigen(Long direcionOrigen) {
        this.direcionOrigen = direcionOrigen;
    }

    public Long getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(Long direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaLlegadaEstimada() {
        return fechaLlegadaEstimada;
    }

    public void setFechaLlegadaEstimada(LocalDate fechaLlegadaEstimada) {
        this.fechaLlegadaEstimada = fechaLlegadaEstimada;
    }

    public LocalDate getFechaLlegadaReal() {
        return fechaLlegadaReal;
    }

    public void setFechaLlegadaReal(LocalDate fechaLlegadaReal) {
        this.fechaLlegadaReal = fechaLlegadaReal;
    }

    public Integer getaTiempo() {
        return aTiempo;
    }

    public void setaTiempo(Integer aTiempo) {
        this.aTiempo = aTiempo;
    }
}
