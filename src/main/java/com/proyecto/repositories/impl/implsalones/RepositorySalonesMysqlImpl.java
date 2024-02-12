package com.proyecto.repositories.impl.implsalones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositorySalones;
import com.proyecto.repositories.models.Salones;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositorySalonesMysqlImpl implements RepositorySalones {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Salones> listar() {
        List<Salones> listSalones = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM salon")) {
            while (rs.next()) {
                listSalones.add(crearSalon(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSalones;
    }

    @Override
    public Salones buscar(String referenciaSalon) {
        Salones salon = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM salon WHERE referenciaSalon=?")) {
            stmt.setString(1, referenciaSalon);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    salon = crearSalon(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salon;
    }

    @Override
    public void crear(Salones salon) {
        String sql = "INSERT INTO salon(referenciaSalon, cupoSalon, ubicacion) VALUES(?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salon.getReferenciaSalon());
            stmt.setString(2, salon.getCupoSalon());
            stmt.setString(3, salon.getUbicacion());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Salones salon) {
        String sql = "UPDATE salon SET referenciaSalon=?, cupoSalon=?, ubicacion=? WHERE id_salon=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salon.getReferenciaSalon());
            stmt.setString(2, salon.getCupoSalon());
            stmt.setString(3, salon.getUbicacion());
            stmt.setInt(4, salon.getId_salon());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Salones salon) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM salon WHERE id_salon=?")){
            stmt.setInt(1, salon.getId_salon());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Salones crearSalon(ResultSet rs) throws SQLException{
        Salones salon = new Salones();
        salon.setId_salon(rs.getInt("id_salon"));
        salon.setReferenciaSalon(rs.getString("referenciaSalon"));
        salon.setCupoSalon(rs.getString("cupoSalon"));
        salon.setUbicacion(rs.getString("ubicacion"));
        return salon;
    }

}
