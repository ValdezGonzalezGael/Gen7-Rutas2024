package com.Valdez.app.rutas.services;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.repositories.CamionesRepository;
import com.Valdez.app.rutas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CamionesService implements IService<Camion>{
    private IRepository<Camion> camionesRepo;

    //Constructor
    public CamionesService(Connection conn){
        camionesRepo = new CamionesRepository(conn);
    }

    @Override
    public List<Camion> lista() {
        try{
            return camionesRepo.lista();
        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Camion> getById(Long id) {
        try{
            return Optional.ofNullable(camionesRepo.getById(id));
        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Camion camion) {
        try{
            camionesRepo.guardar(camion);
        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            camionesRepo.eliminar(id);
        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage(),e.getCause());
        }
    }
}
