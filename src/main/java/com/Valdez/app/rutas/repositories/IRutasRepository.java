package com.Valdez.app.rutas.repositories;

import com.Valdez.app.rutas.models.Ruta;

import java.sql.SQLException;

public interface IRutasRepository extends IRepository <Ruta> {
    Long guardarReturnId(Ruta ruta) throws SQLException;
}
