package com.proyecto.repositories.impl.implprofesores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryProfesores;
import com.proyecto.repositories.models.Departamentos;
import com.proyecto.repositories.models.Profesores;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryProfesoresMysqlImpl implements RepositoryProfesores{

    private Connection getConnection() throws SQLException{
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Profesores> listar() {
        List<Profesores> listProfesor = new ArrayList<>();

        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM profesor")){
            while(rs.next()){
                listProfesor.add(crearProfesor(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listProfesor;
    }

    @Override
    public Profesores buscar(String numDocumento) {
        Profesores profesor = null;
        
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM profesor WHERE numDocumento=?")) {
            stmt.setString(1, numDocumento);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    profesor = crearProfesor(rs);
                }
            }
                
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return profesor;
    }

    @Override
    public void crear(Profesores profesores) {
        String sql = "INSERT INTO profesor(tipoDoc, numDocumento, pNombre, sNombre, pApellido, sApellido, ciudadResidencia, direccion, telefono, fNacimiento, sexo) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, profesores.getTipoDoc());
        stmt.setString(2, profesores.getNumDocumento());
        stmt.setString(3, profesores.getPNombre());
        stmt.setString(4, profesores.getSNombre());
        stmt.setString(5, profesores.getPApellido());
        stmt.setString(6, profesores.getSApellido());
        stmt.setString(7, profesores.getCiudadResidencia());
        stmt.setString(8, profesores.getDireccion());
        stmt.setString(9, profesores.getTelefono());
        stmt.setString(10, profesores.getFNacimiento());
        stmt.setString(11, profesores.getSexo());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Profesores profesores) {
        String sql = "UPDATE profesor SET tipoDoc=?, pNombre=?, sNombre=?, pApellido=?, sApellido=?, ciudadResidencia=?, direccion=?, telefono=?, fNacimiento=?, sexo=? WHERE id_profesor=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, profesores.getTipoDoc());
        stmt.setString(2, profesores.getPNombre());
        stmt.setString(3, profesores.getSNombre());
        stmt.setString(4, profesores.getPApellido());
        stmt.setString(5, profesores.getSApellido());
        stmt.setString(6, profesores.getCiudadResidencia());
        stmt.setString(7, profesores.getDireccion());
        stmt.setString(8, profesores.getTelefono());
        stmt.setString(9, profesores.getFNacimiento());
        stmt.setString(10, profesores.getSexo());
        stmt.setInt(11, profesores.getId_profesor());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Profesores profesores) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM profesor WHERE id=?")){
            stmt.setInt(1, profesores.getId_profesor());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Profesores crearProfesor(ResultSet rs) throws SQLException{
        Profesores profesor = new Profesores();
        profesor.setId_profesor(rs.getInt("id_profesor"));
        profesor.setTipoDoc(rs.getString("tipoDoc"));
        profesor.setNumDocumento(rs.getString("numDocumento"));
        profesor.setPNombre(rs.getString("pNombre"));
        profesor.setSNombre(rs.getString("sNombre"));
        profesor.setPApellido(rs.getString("pApellido"));
        profesor.setSApellido(rs.getString("sApellido"));
        profesor.setCiudadResidencia(rs.getString("ciudadResidencia"));
        profesor.setDireccion(rs.getString("direccion"));
        profesor.setTelefono(rs.getString("telefono"));
        profesor.setFNacimiento(rs.getString("fNacimiento"));
        profesor.setSexo(rs.getString("sexo"));
        return profesor;
    }

    @Override
    public void asignarDepartamento(Profesores profesor, Departamentos departamento) {
        if (profesor.getId_departamento() != null) {
            System.out.println("El profesor ya está asignado a un departamento.");
            return;
        }
        
        // Imprimir valores para verificar
        System.out.println("ID del departamento a asignar: " + departamento.getId_departamento());
        System.out.println("ID del profesor: " + profesor.getId_profesor());
    
        String sql = "UPDATE profesor SET id_departamento = ? WHERE id_profesor = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, departamento.getId_departamento());
            stmt.setInt(2, profesor.getId_profesor());
    
            // Imprimir SQL statement para verificar
            System.out.println("SQL Statement: " + stmt.toString());
    
            stmt.executeUpdate();
            System.out.println("Asignación de departamento realizada con éxito.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    

    
    @Override
    public List<Profesores> obtenerProfesoresPorDepartamento(Departamentos departamento) {
        List<Profesores> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesor WHERE id_departamento = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, departamento.getId_departamento());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Profesores profesor = crearProfesor(rs);
                    profesores.add(profesor);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return profesores;
    }
    
}
