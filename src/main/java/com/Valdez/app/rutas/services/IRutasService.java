package com.Valdez.app.rutas.services;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.models.Chofer;
import com.Valdez.app.rutas.models.Ruta;

import java.util.List;

public interface IRutasService extends IService<Ruta> {
    List<Camion> listarCamiones();
    List<Chofer> listarChoferes();
    Long guardarReturnId(Ruta ruta);
}
