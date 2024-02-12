package com.proyecto.repositories.impl.impldepartamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryDepartamentos;
import com.proyecto.repositories.models.Departamentos;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryDepartamentosMysqlImpl implements RepositoryDepartamentos{

     private Connection getConnection() throws SQLException{
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Departamentos> listar() {
        List<Departamentos> listDepartamentos = new ArrayList<>();

        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM departamento")){
            while(rs.next()){
                listDepartamentos.add(crearDepartamento(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listDepartamentos;
    }

    @Override
    public Departamentos buscar(String nomDepartamento) {
        Departamentos departamento = null;
        
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departamento WHERE nomDepartamento=?")) {
            stmt.setString(1, nomDepartamento);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    departamento = crearDepartamento(rs);
                }
            }
                
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return departamento;
    }


    @Override
    public void crear(Departamentos departamentos) {
        String sql = "INSERT INTO departamento(nomDepartamento) VALUES(?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, departamentos.getNomDepartamento());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Departamentos departamentos) {
        String sql = "UPDATE departamento SET nomDepartamento=? WHERE id=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, departamentos.getNomDepartamento());
        stmt.setInt(2, departamentos.getId());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Departamentos departamentos) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM departamento WHERE id=?")){
            stmt.setInt(1, departamentos.getId());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Departamentos crearDepartamento(ResultSet rs) throws SQLException{
        Departamentos departamento = new Departamentos();
        departamento.setId(rs.getInt("id"));
        departamento.setNomDepartamento(rs.getString("nomDepartamento"));
        return departamento;
    }
    
}
