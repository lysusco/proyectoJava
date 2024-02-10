package com.proyecto.repositories.impl.implcursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryCursos;
import com.proyecto.repositories.models.Cursos;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryCursosMysqlImpl implements RepositoryCursos {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Cursos> listar() {
        List<Cursos> listCursos = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM curso")) {
            while (rs.next()) {
                listCursos.add(crearCurso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCursos;
    }


    @Override
    public void crear(Cursos cursos) {
        String sql = "INSERT INTO curso(nomCurso, guiaCatedra) VALUES(?,?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cursos.getNomCurso());
            stmt.setString(2, cursos.getGuiaCatedra());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Cursos cursos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public void eliminar(Cursos cursos) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM curso WHERE id=?")){
            stmt.setInt(1, cursos.getId());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public Cursos buscar(String nomCurso) {
        Cursos curso = null;
        
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM curso WHERE nomCurso=?")) {
            stmt.setString(1, nomCurso);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    curso = crearCurso(rs);
                }
            }
                
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return curso;
    }

    private Cursos crearCurso(ResultSet rs) throws SQLException{
        Cursos curso = new Cursos();
        curso.setId(rs.getInt("id"));
        curso.setNomCurso(rs.getString("nomCurso"));
        curso.setGuiaCatedra(rs.getString("guiaCatedra"));;
        return curso;
    }

}
