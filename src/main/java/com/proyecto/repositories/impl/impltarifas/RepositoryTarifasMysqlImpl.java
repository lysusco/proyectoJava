package com.proyecto.repositories.impl.impltarifas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryTarifas;
import com.proyecto.repositories.models.Tarifas;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryTarifasMysqlImpl implements RepositoryTarifas{

        private Connection getConnection() throws SQLException{
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Tarifas> listar() {
    List<Tarifas> listTarifa = new ArrayList<>();

        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tarifa")){
            while(rs.next()){
                listTarifa.add(crearTarifa(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listTarifa;
    }

    @Override
    public void crear(Tarifas tarifas) {
        String sql = "INSERT INTO tarifa(valorCredito) VALUES(?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, tarifas.getValorCredito());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Tarifas tarifas) {
        String sql = "UPDATE tarifa SET valorCredito=? WHERE id=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, tarifas.getValorCredito());
        stmt.setInt(2, tarifas.getId());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Tarifas tarifas) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM tarifa WHERE id=?")){
            stmt.setInt(1, tarifas.getId());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Tarifas crearTarifa(ResultSet rs) throws SQLException{
        Tarifas tarifa = new Tarifas();
        tarifa.setId(rs.getInt("id"));
        tarifa.setValorCredito(rs.getString("valorCredito"));
        return tarifa;
    }
    
}
