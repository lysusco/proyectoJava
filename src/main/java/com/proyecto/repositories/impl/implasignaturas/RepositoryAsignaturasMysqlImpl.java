package com.proyecto.repositories.impl.implasignaturas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryAsignaturas;
import com.proyecto.repositories.models.Asignaturas;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryAsignaturasMysqlImpl implements RepositoryAsignaturas {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Asignaturas> listar() {
        List<Asignaturas> listAsignaturas = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM asignatura")) {
            while (rs.next()) {
                listAsignaturas.add(crearAsignatura(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAsignaturas;
    }

    @Override
    public Asignaturas buscar(String nomAsignatura) {
        Asignaturas asignatura = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM asignatura WHERE nomAsignatura=?")) {
            stmt.setString(1, nomAsignatura);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    asignatura = crearAsignatura(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignatura;
    }

    @Override
    public void crear(Asignaturas asignaturas) {
        String sql = "INSERT INTO asignatura(nomAsignatura, cupoAsignatura, creditos) VALUES(?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asignaturas.getNomAsignatura());
            stmt.setString(2, asignaturas.getCupoAsignatura());
            stmt.setString(3, asignaturas.getCreditos());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Asignaturas asignaturas) {
        String sql = "UPDATE asignatura SET nomAsignatura=?, cupoAsignatura=? creditos=? WHERE id=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asignaturas.getNomAsignatura());
            stmt.setString(2, asignaturas.getCupoAsignatura());
            stmt.setString(3, asignaturas.getCreditos());
            stmt.setInt(4, asignaturas.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Asignaturas asignaturas) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM asignatura WHERE id=?")){
            stmt.setInt(1, asignaturas.getId());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Asignaturas crearAsignatura(ResultSet rs) throws SQLException{
        Asignaturas asignatura = new Asignaturas();
        asignatura.setId(rs.getInt("id"));
        asignatura.setNomAsignatura(rs.getString("nomAsignatura"));
        asignatura.setCupoAsignatura(rs.getString("cupoAsignatura"));
        asignatura.setCreditos(rs.getString("creditos"));
        return asignatura;
    }

}
