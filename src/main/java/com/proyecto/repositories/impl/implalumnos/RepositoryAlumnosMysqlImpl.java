package com.proyecto.repositories.impl.implalumnos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryAlumnos;
import com.proyecto.repositories.models.Alumnos;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryAlumnosMysqlImpl implements RepositoryAlumnos{

    private Connection getConnection() throws SQLException{
        return ConexionBDMysql.getInstance();
    }
    @Override
    public List<Alumnos> listar() {
        List<Alumnos> listAlumnos = new ArrayList<>();

        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM alumno")){
            while(rs.next()){
                listAlumnos.add(crearAlumno(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listAlumnos;
    }

    @Override
    public Alumnos buscar(String numDocumento) {
        Alumnos alumno = null;
        
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM alumno WHERE numDocumento=?")) {
            stmt.setString(1, numDocumento);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    alumno = crearAlumno(rs);
                }
            }
                
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return alumno;
    }

    @Override
    public void crear(Alumnos alumno) {
        String sql = "INSERT INTO alumno(tipoDoc, numDocumento, pNombre, sNombre, pApellido, sApellido, ciudadResidencia, direccion, telefono, fNacimiento, sexo) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, alumno.getTipoDoc());
        stmt.setString(2, alumno.getNumDocumento());
        stmt.setString(3, alumno.getPNombre());
        stmt.setString(4, alumno.getSNombre());
        stmt.setString(5, alumno.getPApellido());
        stmt.setString(6, alumno.getSApellido());
        stmt.setString(7, alumno.getCiudadResidencia());
        stmt.setString(8, alumno.getDireccion());
        stmt.setString(9, alumno.getTelefono());
        stmt.setString(10, alumno.getFNacimiento());
        stmt.setString(11, alumno.getSexo());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Alumnos alumno) {
        String sql = "UPDATE alumno SET tipoDoc=?, pNombre=?, sNombre=?, pApellido=?, sApellido=?, ciudadResidencia=?, direccion=?, telefono=?, fNacimiento=?, sexo=? WHERE id_alumno=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, alumno.getTipoDoc());
        stmt.setString(2, alumno.getPNombre());
        stmt.setString(3, alumno.getSNombre());
        stmt.setString(4, alumno.getPApellido());
        stmt.setString(5, alumno.getSApellido());
        stmt.setString(6, alumno.getCiudadResidencia());
        stmt.setString(7, alumno.getDireccion());
        stmt.setString(8, alumno.getTelefono());
        stmt.setString(9, alumno.getFNacimiento());
        stmt.setString(10, alumno.getSexo());
        stmt.setInt(11, alumno.getId_alumno());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Alumnos alumno) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM alumno WHERE id_alumno=?")){
            stmt.setInt(1, alumno.getId_alumno());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    private Alumnos crearAlumno(ResultSet rs) throws SQLException{
        Alumnos alumno = new Alumnos();
        alumno.setId_alumno(rs.getInt("id_alumno"));
        alumno.setTipoDoc(rs.getString("tipoDoc"));
        alumno.setNumDocumento(rs.getString("numDocumento"));
        alumno.setPNombre(rs.getString("pNombre"));
        alumno.setSNombre(rs.getString("sNombre"));
        alumno.setPApellido(rs.getString("pApellido"));
        alumno.setSApellido(rs.getString("sApellido"));
        alumno.setCiudadResidencia(rs.getString("ciudadResidencia"));
        alumno.setDireccion(rs.getString("direccion"));
        alumno.setTelefono(rs.getString("telefono"));
        alumno.setFNacimiento(rs.getString("fNacimiento"));
        alumno.setSexo(rs.getString("sexo"));
        return alumno;
    }

    
}
