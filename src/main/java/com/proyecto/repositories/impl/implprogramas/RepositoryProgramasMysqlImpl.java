package com.proyecto.repositories.impl.implprogramas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryProgramas;
import com.proyecto.repositories.models.Programas;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryProgramasMysqlImpl implements RepositoryProgramas {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Programas> listar() {
        List<Programas> listProgramas = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM programa")) {
            while (rs.next()) {
                listProgramas.add(crearPrograma(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProgramas;
    }

    @Override
    public Programas buscar(String nomPrograma) {
        Programas programa = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM programa WHERE nomPrograma=?")) {
            stmt.setString(1, nomPrograma);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    programa = crearPrograma(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programa;
    }

    @Override
    public void crear(Programas programas) {
        String sql = "INSERT INTO programa(nomPrograma, nivel) VALUES(?,?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programas.getNomPrograma());
            stmt.setString(2, programas.getNivel());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Programas programas) {
        String sql = "UPDATE programa SET nomPrograma=?, nivel=? WHERE id_programa=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programas.getNomPrograma());
            stmt.setString(2, programas.getNivel());
            stmt.setInt(3, programas.getId_programa());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Programas programas) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM programa WHERE id=?")){
            stmt.setInt(1, programas.getId_programa());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

        private Programas crearPrograma(ResultSet rs) throws SQLException{
        Programas programa = new Programas();
        programa.setId_programa(rs.getInt("id_programa"));
        programa.setNomPrograma(rs.getString("nomPrograma"));
        programa.setNivel(rs.getString("nivel"));
        return programa;
    }

}
