package com.proyecto.repositories.impl.implhorarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryHorarios;
import com.proyecto.repositories.models.Horarios;
import com.proyecto.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryHorariosMysqlImpl implements RepositoryHorarios{

    private Connection getConnection() throws SQLException{
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Horarios> listar() {
        List<Horarios> listHorarios = new ArrayList<>();

        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM horario")){
            while(rs.next()){
                listHorarios.add(crearHorario(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listHorarios;
    }

    @Override
    public Horarios buscar(String dia) {
        Horarios horario = null;
        
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM horario WHERE dia=?")) {
            stmt.setString(1, dia);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    horario = crearHorario(rs);
                }
            }
                
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return horario;
    }

    @Override
    public void crear(Horarios horarios) {
        String sql = "INSERT INTO horario(dia, horaIni, horaFin) VALUES(?,?,?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, horarios.getDia());
        stmt.setString(2, horarios.getHoraIni());
        stmt.setString(3, horarios.getHoraFin());

        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Horarios horarios) {
        String sql = "UPDATE horario SET dia=?, horaIni=?, horaFin=? WHERE id=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1, horarios.getDia());
        stmt.setString(2, horarios.getHoraIni());
        stmt.setString(3, horarios.getHoraFin());
        stmt.setInt(4, horarios.getId());
        stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Horarios horarios) {
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM horario WHERE id=?")){
            stmt.setInt(1, horarios.getId());
            stmt.executeUpdate();
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }
    
    private Horarios crearHorario(ResultSet rs) throws SQLException{
        Horarios horario = new Horarios();
        horario.setId(rs.getInt("id"));
        horario.setDia(rs.getString("dia"));
        horario.setHoraIni(rs.getString("horaIni"));
        horario.setHoraFin(rs.getString("horaFin"));
        return horario;
    }
}
