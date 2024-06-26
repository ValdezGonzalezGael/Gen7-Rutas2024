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
    public List<Camion> lista() throws SQLException {
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
        Camion camiones = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CAMIONES WHERE ID_CAMION=?")){
            stmt.setLong(1,id);
            try(ResultSet rs= stmt.executeQuery()){
                if(rs.next()){
                    camiones=this.getCamion(rs);
                }
            }
        }
        return  camiones;
    }

    @Override
    public void guardar(Camion camion) throws SQLException {
        String sql="";
        if (camion.getId() != null && camion.getId() >0) {
            sql = "update camiones set matricula =?, tipo_camion=?, " +
                    "modelo=?, marca=?, capacidad=?, " +
                    "kilometraje=?, disponibilidad=? " +
                    " where id_camion=?";
        }else{
            sql = "insert into camiones(id_camion, matricula, "+
                    "tipo_camion, modelo, marca, capacidad, "+
                    "kilometraje, disponibilidad) "+
                    "values (-1,?,?,?,?,?,?,?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            if (camion.getId() != null && camion.getId() >0){
                stmt.setString(1, camion.getMatricula());
                stmt.setString(2, camion.getTipoCamion().toString().toUpperCase());
                stmt.setInt(3, camion.getModelo());
                stmt.setString(4,camion.getMarca().toString().toUpperCase());
                stmt.setInt(5,camion.getCapacidad());
                stmt.setFloat(6,camion.getKilometraje().floatValue());
                stmt.setInt(7, camion.getDisponibilidad() ? 1 : 0);
                stmt.setLong(8, camion.getId());

            }else{
                stmt.setString(1, camion.getMatricula());
                stmt.setString(2, camion.getTipoCamion().toString().toUpperCase());
                stmt.setInt(3, camion.getModelo());
                stmt.setString(4,camion.getMarca().toString().toUpperCase());
                stmt.setInt(5,camion.getCapacidad());
                stmt.setFloat(6,camion.getKilometraje().floatValue());
                stmt.setInt(7, camion.getDisponibilidad() ? 1 : 0);
            }
            stmt.executeUpdate();
        }catch (SQLException e){ System.out.println("ERROR:"+e);  }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql ="delete from camiones where id_camion=?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();

        }catch (SQLException e){

        }
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


