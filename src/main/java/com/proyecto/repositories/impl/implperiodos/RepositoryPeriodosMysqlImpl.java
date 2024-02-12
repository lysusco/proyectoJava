package com.proyecto.repositories.impl.implperiodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryPeriodos;
import com.proyecto.repositories.models.Periodos;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryPeriodosMysqlImpl implements RepositoryPeriodos {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Periodos> listar() {
        List<Periodos> listPeriodos = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM periodo")) {
            while (rs.next()) {
                listPeriodos.add(crearPeriodo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPeriodos;
    }

    @Override
    public Periodos buscar(String codPeriodo) {
        Periodos periodo = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM periodo WHERE codPeriodo=?")) {
            stmt.setString(1, codPeriodo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    periodo = crearPeriodo(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return periodo;
    }

    @Override
    public void crear(Periodos periodos) {
        String sql = "INSERT INTO periodo(codPeriodo, año, semestre) VALUES(?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, periodos.getCodPeriodo());
            stmt.setString(2, periodos.getAño());
            stmt.setString(3, periodos.getSemestre());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Periodos periodos) {
        String sql = "UPDATE periodo SET codPeriodo=?, año=?, semestre=? WHERE id_periodo=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, periodos.getCodPeriodo());
            stmt.setString(2, periodos.getAño());
            stmt.setString(3, periodos.getSemestre());
            stmt.setInt(4, periodos.getId_periodo());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Periodos periodos) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM periodo WHERE id=?")){
            stmt.setInt(1, periodos.getId_periodo());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Periodos crearPeriodo(ResultSet rs) throws SQLException{
        Periodos perido = new Periodos();
        perido.setId_periodo(rs.getInt("id_periodo"));
        perido.setCodPeriodo(rs.getString("codPeriodo"));
        perido.setAño(rs.getString("año"));
        perido.setSemestre(rs.getString("semestre"));
        return perido;
    }

}
