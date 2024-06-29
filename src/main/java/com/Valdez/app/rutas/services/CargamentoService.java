package com.Valdez.app.rutas.services;

import com.Valdez.app.rutas.models.Cargamento;
import com.Valdez.app.rutas.repositories.CargamentoRepository;
import com.Valdez.app.rutas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CargamentoService implements IService<Cargamento> {
    private IRepository<Cargamento> cargamentoRepo;
    public CargamentoService(Connection conn) {
        cargamentoRepo = new CargamentoRepository(conn);
    }


    @Override
    public List<Cargamento> lista() {
        try{
            return cargamentoRepo.lista();
        }catch (SQLException e){
            throw  new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Cargamento> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Cargamento cargamento) {
        try{
            cargamentoRepo.guardar(cargamento);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {

    }
}
