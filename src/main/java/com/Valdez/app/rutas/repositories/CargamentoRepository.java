package com.Valdez.app.rutas.repositories;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.models.Cargamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargamentoRepository implements IRepository<Cargamento> {
    private Connection conn;

    public CargamentoRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cargamento> lista() throws SQLException {
        List<Cargamento> cargamento = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM CARGAMENTOS")){
            while (rs.next()){
                Cargamento a = this.getCargamento(rs);
                cargamento.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return cargamento;
    }

    @Override
    public Cargamento getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Cargamento cargamento) throws SQLException {
        String sql = "";
        if (cargamento.getId_Cargamento() != null && cargamento.getId_Cargamento() > 0) {
            sql = "update cargamentos set id_cargamento=?, ruta_id=?, descripcion=?, " +
                    "peso=?, estatus=? " +
                    "where id_cargamento=?";
        } else {
            sql = "insert into cargamentos(id_cargamento, ruta_id, " +
                    "descripcion, peso, estatus) " +
                    "values (SEQUENCE5.NEXTVAL,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            if(cargamento.getId_Cargamento() != null && cargamento.getId_Cargamento() > 0){
                stmt.setLong(1,cargamento.getRuta_id());
                stmt.setString(2,cargamento.getDescripcion());
                stmt.setFloat(3,cargamento.getPeso());
                stmt.setInt(4,cargamento.getEstatus());
                stmt.setLong(5, cargamento.getId_Cargamento());
            } else {
                stmt.setLong(1,cargamento.getRuta_id());
                stmt.setString(2,cargamento.getDescripcion());
                stmt.setFloat(3,cargamento.getPeso());
                stmt.setInt(4,cargamento.getEstatus());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private Cargamento getCargamento (ResultSet rs) throws SQLException{
        Cargamento cargamento = new Cargamento();

        cargamento.setId_Cargamento(rs.getLong("ID_CARGAMENTO"));
        cargamento.setRuta_id(rs.getLong("RUTA_ID"));
        cargamento.setDescripcion(rs.getString("DESCRIPCION"));
        cargamento.setPeso(rs.getFloat("PESO"));
        cargamento.setEstatus(rs.getInt("ESTATUS"));

        return cargamento;
    }
}
