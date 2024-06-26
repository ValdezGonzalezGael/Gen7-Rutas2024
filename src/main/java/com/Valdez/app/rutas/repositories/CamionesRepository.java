package com.Valdez.app.rutas.repositories;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.models.enums.Marcas;
import com.Valdez.app.rutas.models.enums.Tipos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamionesRepository implements IRepository<Camion>{

    //Atributos
    private Connection conn; //Importamos de java.sql

    //constructor
    public CamionesRepository(Connection conn){
        this.conn=conn;
    }

    @Override
    public List<Camion> listar() throws SQLException {
        List<Camion> camiones = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM CAMIONES")){
            while (rs.next()){
                Camion a = this.getCamion(rs);
                camiones.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return camiones;
    }

    @Override
    public Camion getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Camion camion) throws SQLException {
        String sql="";
        if (camion.getId() !=0 && camion.getId() > 0 ){
            sql = "update camion set matricula=?, tipo_camion=?, " +
                    "modelo=?, marca=?, capacidad=?, " +
                    "kilometraje=?, disponoibilidad=? " +
                    "where id_camion?";
        } else {
            sql = "insert into camion(id_camion, matricula," +
                    "tipo_camion, modelo, marca, capacidad, " +
                    "kilometraje, disponibilidad) " +
                    "values(-1,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stm = conn.prepareStatement(sql)){
            if(camion.getId() != 0 && camion.getId()>0){
                stm.setString(1, camion.getMatricula());
                stm.setString(2, String.valueOf(camion.getTipoCamion()));
                stm.setInt(3, camion.getModelo());
                stm.setString(4, String.valueOf(camion.getMarca()));
                stm.setInt(5, camion.getCapacidad());
                stm.setFloat(5,camion.getKilometraje());
                stm.setInt(7, camion.getDisponibilidad() ? 1 : 0);
                stm.setLong(8, camion.getId());
            }else {
                stm.setString(1, camion.getMatricula());
                stm.setString(2, String.valueOf(camion.getTipoCamion()));
                stm.setInt(3, camion.getModelo());
                stm.setString(4, String.valueOf(camion.getMarca()));
                stm.setInt(5, camion.getCapacidad());
                stm.setFloat(5,camion.getKilometraje());
//                operador ternario
                stm.setInt(7, camion.getDisponibilidad() ? 1 : 0);
            }
            stm.executeUpdate();
        }
    }

    @Override
    public void eliminar(Camion camion) throws SQLException {
    }

    //Mapear, transformar un renglon, fila, registro, row en un objeto de tipo camion

    private Camion getCamion (ResultSet rs) throws SQLException{
        Camion a= new Camion();

        a.setId(rs.getLong("ID_CAMION"));
        a.setMatricula(rs.getString("MATRICULA"));
        a.setTipoCamion(Tipos.valueOf(rs.getString("TIPO_CAMION")));
        a.setModelo(rs.getInt("MODELO"));
        a.setMarca(Marcas.valueOf(rs.getString("MARCA")));
        a.setCapacidad(rs.getInt("CAPACIDAD"));
        a.setKilometraje(rs.getFloat("KILOMETRAJE"));
        a.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));

        return a;
    }

}


