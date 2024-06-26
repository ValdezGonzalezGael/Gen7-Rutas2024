package com.Valdez.app.rutas.repositories;

import com.Valdez.app.rutas.models.Chofer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoferesRepository implements IRepository<Chofer>{
    private Connection conn; //Importamos de java.sql
    public ChoferesRepository(Connection conn){
        this.conn=conn;
    }

    @Override
    public List<Chofer> lista() throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM CHOFERES")){
            while (rs.next()){
                Chofer a = this.getChofer(rs);
                choferes.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return choferes;
    }

    @Override
    public Chofer getById(Long id) throws SQLException {
        Chofer chofer =null;
        try(PreparedStatement stmt=conn.prepareStatement("SELECT * FROM CHOFERES WHERE ID_CHOFER=?")){
            stmt.setLong(1,id);
            try(ResultSet rs= stmt.executeQuery()){
                if(rs.next()){
                    chofer=this.getChofer(rs);
                    System.out.println(this.getChofer(rs));
                }
            }
        }
        return  chofer;
    }

    @Override
    public void guardar(Chofer chofer) throws SQLException {
        String sql="";
        if (chofer.getId() !=0 && chofer.getId() > 0 ){
        sql = "update choferes set nombre=?, ap_paterno=?, " +
                "ap_materno=?, licencia=?, telefono=?, " +
                "fecha_nacimiento=?, disponoibilidad=? " +
                "where id_chofer?";
    } else {
            sql = "insert into choferes(id_chofer, nombre," +
                    "ap_paterno, ap_materno, licencia, telefono, " +
                    "fecha_nacimiento, disponibilidad) " +
                    "values(sequence1.nextval,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stm = conn.prepareStatement(sql)){
            if(chofer.getId() != 0 && chofer.getId()>0){
                stm.setString(1, chofer.getNombre());
                stm.setString(2, chofer.getApPaterno());
                stm.setString(3, chofer.getApMaterno());
                stm.setString(4, chofer.getLicencia());
                stm.setString(5, chofer.getTelefono());
                stm.setDate(6, Date.valueOf(chofer.
                        getFechaNacimiento()));
                stm.setInt(7, chofer.getDisponibilidad() ? 1 : 0);
                stm.setLong(8, chofer.getId());
            }else {
                stm.setString(1, chofer.getNombre());
                stm.setString(2, chofer.getApPaterno());
                stm.setString(3, chofer.getApMaterno());
                stm.setString(4, chofer.getLicencia());
                stm.setString(5, chofer.getTelefono());
                stm.setDate(6, Date
                        .valueOf(chofer.getFechaNacimiento()));
//                operador ternario
                stm.setInt(7, chofer.getDisponibilidad() ? 1 : 0);
            }
            stm.executeUpdate();
        }catch (SQLException e){   }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from choferes where id_chofe=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            System.out.println("ELIMINADO");
            stmt.executeUpdate();
        }catch (SQLException e){}
    }

    //Mapear, transformar un renglon, fila, registro, row en un objeto de tipo chofer


    private Chofer getChofer(ResultSet rs) throws SQLException{
        Chofer a= new Chofer();

        a.setId(rs.getLong("ID_CHOFER"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setApPaterno(rs.getString("AP_PATERNO"));
        a.setApMaterno(rs.getString("AP_MATERNO"));
        a.setLicencia(rs.getString("LICENCIA"));
        a.setTelefono(rs.getString("TELEFONO"));
        a.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
        a.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));
        return a;
    }
}
