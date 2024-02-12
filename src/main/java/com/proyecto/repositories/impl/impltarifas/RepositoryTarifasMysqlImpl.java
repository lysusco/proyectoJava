package com.proyecto.repositories.impl.impltarifas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryTarifas;
import com.proyecto.repositories.models.Periodos;
import com.proyecto.repositories.models.Programas;
import com.proyecto.repositories.models.Tarifas;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryTarifasMysqlImpl implements RepositoryTarifas{

        private Connection getConnection() throws SQLException{
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Tarifas> listarConPeriodos() {
        List<Tarifas> listTarifa = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT t.*, p.* FROM tarifa t JOIN periodo p ON t.id_periodo = p.id_periodo")) {
            while (rs.next()) {
                Tarifas tarifa = crearTarifa(rs);
                Periodos periodo = crearPeriodo(rs); 
                tarifa.setPeriodo(periodo); 
                listTarifa.add(tarifa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTarifa;
    }

    @Override
    public List<Tarifas> listarPorPrograma() {
        List<Tarifas> listTarifa = new ArrayList<>();

    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT p.*, t.* FROM programa p LEFT JOIN tarifa t ON p.id_programa = t.id_programa")) {
        while (rs.next()) {
            Tarifas tarifa = crearTarifa(rs);
            Programas programa = crearPrograma(rs);
            tarifa.setPrograma(programa);
            listTarifa.add(tarifa);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listTarifa;
}



    private Programas crearPrograma(ResultSet rs) throws SQLException {
            Programas programa = new Programas();
            programa.setId_programa(rs.getInt("id_programa"));
            programa.setNomPrograma(rs.getString("nomPrograma"));
            return programa;
        }
        

    @Override
    public void crear(Tarifas tarifas) {
        String sql = "INSERT INTO tarifa(id_programa, id_periodo, valorCredito) VALUES(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tarifas.getPrograma().getId_programa());
            stmt.setInt(2, tarifas.getPeriodo().getId_periodo());
            stmt.setString(3, tarifas.getValorCredito());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    @Override
    public void editar(Tarifas tarifas) {
        String sql = "UPDATE tarifa SET valorCredito=?, id_programa=?, id_periodo=? WHERE id_tarifa=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarifas.getValorCredito());
            stmt.setInt(2, tarifas.getPrograma().getId_programa());
            stmt.setInt(3, tarifas.getPeriodo().getId_periodo());
            stmt.setInt(4, tarifas.getId_tarifa());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Tarifas tarifas) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM tarifa WHERE id_tarifa=?")){
            stmt.setInt(1, tarifas.getId_tarifa());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Tarifas crearTarifa(ResultSet rs) throws SQLException{
        Tarifas tarifa = new Tarifas();
        tarifa.setId_tarifa(rs.getInt("id_tarifa"));
        tarifa.setValorCredito(rs.getString("valorCredito"));
        return tarifa;
    }

    @Override
    public List<Tarifas> buscarPorPeriodo(Periodos periodo) {
        List<Tarifas> tarifas = new ArrayList<>();
        String sql = "SELECT * FROM tarifa WHERE id_periodo = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, periodo.getId_periodo());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarifas tarifa = new Tarifas();
                    tarifa.setId_tarifa(rs.getInt("id_tarifa"));
                    tarifa.setValorCredito(rs.getString("valorCredito"));
                    tarifas.add(tarifa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tarifas;
    }
    
    @Override
    public List<Tarifas> buscarPorPrograma(Programas programa) {
        List<Tarifas> tarifas = new ArrayList<>();
        String sql = "SELECT * FROM tarifa WHERE id_programa = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, programa.getId_programa());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarifas tarifa = new Tarifas();
                    tarifa.setId_tarifa(rs.getInt("id_tarifa"));
                    tarifa.setValorCredito(rs.getString("valorCredito"));
                    tarifas.add(tarifa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tarifas;
    }

    private Periodos crearPeriodo(ResultSet rs) throws SQLException {
        Periodos periodo = new Periodos();
        periodo.setId_periodo(rs.getInt("id_periodo"));
        periodo.setCodPeriodo(rs.getString("codPeriodo"));
        return periodo;
    }
    
    
}
