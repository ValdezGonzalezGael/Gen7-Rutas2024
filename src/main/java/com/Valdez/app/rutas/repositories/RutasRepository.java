package com.Valdez.app.rutas.repositories;

import com.Valdez.app.rutas.models.Ruta;

import java.sql.*;
import java.util.List;

public class RutasRepository implements IRutasRepository{
    private Connection conn;

    public  RutasRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public Long guardarReturnId(Ruta ruta) throws SQLException {
        String sql = "";
        Long resultado = -1L;
        sql="insertar into rutas(ID_RUTA, camion_id, chofer_id, " +
                "direccion_origen_id, direccion_destino_id, distancia, " +
                "fecha_salida," +
                "fecha_llegada_estimada, fecha_llegada_real, a_tiempo" +
                "values (SEQUENCE4.NEXTVAL,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stm = conn.prepareStatement(sql,
                new String[]{"ID_RUTA"})){
            stm.setLong(1,ruta.getCamionId());
            stm.setLong(2,ruta.getChoferId());
            stm.setLong(3,ruta.getDirecionOrigen());
            stm.setLong(4,ruta.getDireccionDestino());
            stm.setFloat(5,ruta.getDistancia());
            stm.setDate(6, Date.valueOf(ruta.getFechaSalida()));
            stm.setDate(7, Date.valueOf(ruta.getFechaLlegadaEstimada()));
            stm.setDate(8, Date.valueOf(ruta.getFechaLlegadaEstimada()));
            stm.setInt(9, ruta.getaTiempo());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()){
                resultado = rs.getLong(1);
            }
        }
        return resultado;
    }

    @Override
    public List<Ruta> lista() throws SQLException {
        return List.of();
    }

    @Override
    public Ruta getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Ruta ruta) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
