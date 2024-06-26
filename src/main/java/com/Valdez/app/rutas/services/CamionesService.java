package com.Valdez.app.rutas.services;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.repositories.CamionesRepository;
import com.Valdez.app.rutas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CamionesService implements IService<Camion>{
    private IRepository<Camion> camionRepo;

    //Constructor
    public CamionesService(Connection conn){
        camionRepo = new CamionesRepository(conn);
    }

    @Override
    public List<Camion> lista() {
        try{
            return camionRepo.lista();
        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Camion> getById(Long id) {return Optional.empty();}

    @Override
    public void guardar(Camion camion) {
        try{
            camionRepo.guardar(camion);
        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {

    }
}
