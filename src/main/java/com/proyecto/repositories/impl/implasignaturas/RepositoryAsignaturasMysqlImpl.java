package com.proyecto.repositories.impl.implasignaturas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.repositories.RepositoryAsignaturas;
import com.proyecto.repositories.models.Asignaturas;
import com.proyecto.repositories.models.Cursos;
import com.proyecto.repositories.models.Periodos;
import com.proyecto.repositories.models.Profesores;
import com.proyecto.repositories.models.Programas;
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

    public void crear(Asignaturas asignatura) {
        Profesores profesor = asignatura.getProfesor();
    
        if (profesor != null) {
            System.out.println("Profesor asignado: " + profesor.getPNombre() + " " + profesor.getPApellido());
        } else {
            System.out.println("No se ha asignado ningún profesor.");
        }
    
        // Verifica si el nombre de la asignatura es null antes de llamar a generarCodigo()
        if (asignatura.getNomAsignatura() != null) {
            asignatura.setCodigo(asignatura.generarCodigo());
        } else {
            System.out.println("El nombre de la asignatura no está definido.");
            return; // Termina el método si el nombre de la asignatura es null
        }

        String sql = "INSERT INTO asignatura(nomAsignatura, cupoAsignatura, creditos, id_programa, id_curso, id_periodo, id_profesor) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asignatura.getNomAsignatura());
            stmt.setString(2, asignatura.getCupoAsignatura());
            stmt.setString(3, asignatura.getCreditos());
            stmt.setInt(4, asignatura.getProgramas().getId_programa());
            stmt.setInt(5, asignatura.getCursos().getId_curso());
            stmt.setInt(6, asignatura.getPeriodos().getId_periodo());

            if (profesor != null) {
                stmt.setInt(7, profesor.getId_profesor());
            } else {
                stmt.setNull(7, Types.INTEGER);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Asignaturas asignaturas) {
        String sql = "UPDATE asignatura SET nomAsignatura=?, cupoAsignatura=? creditos=? WHERE id_asignatura=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, asignaturas.getNomAsignatura());
            stmt.setString(2, asignaturas.getCupoAsignatura());
            stmt.setString(3, asignaturas.getCreditos());
            stmt.setInt(4, asignaturas.getId_asignatura());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Asignaturas asignaturas) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM asignatura WHERE id_asignatura=?")) {
            stmt.setInt(1, asignaturas.getId_asignatura());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Asignaturas crearAsignatura(ResultSet rs) throws SQLException {
        Asignaturas asignatura = new Asignaturas();
        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
        asignatura.setNomAsignatura(rs.getString("nomAsignatura"));
        asignatura.setCupoAsignatura(rs.getString("cupoAsignatura"));
        asignatura.setCreditos(rs.getString("creditos"));

        int idProfesor = rs.getInt("id_profesor");
        if (idProfesor != 0) {

            Profesores profesor = obtenerProfesorPorId(idProfesor);
            asignatura.setProfesor(profesor);
        }

        int idPrograma = rs.getInt("id_programa");
        if (!rs.wasNull()) {
            Programas programa = obtenerProgramaPorId(idPrograma);
            asignatura.setProgramas(programa);
        }

        int idCurso = rs.getInt("id_curso");
        if (!rs.wasNull()) {
            Cursos curso = obtenerCursoPorId(idCurso);
            asignatura.setCursos(curso);
        }

        int idPeriodo = rs.getInt("id_periodo");
        if (!rs.wasNull()) {
            Periodos periodo = obtenerPeriodoPorId(idPeriodo);
            asignatura.setPeriodos(periodo);
        }

        return asignatura;
    }

    private Periodos obtenerPeriodoPorId(int idPeriodo) {
        Periodos periodo = null;
        String sql = "SELECT * FROM periodo WHERE id_periodo = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPeriodo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    periodo = new Periodos();
                    periodo.setId_periodo(rs.getInt("id_periodo"));
                    periodo.setCodPeriodo(rs.getString("codPeriodo"));
                    periodo.setAño(rs.getString("año"));
                    periodo.setSemestre(rs.getString("semestre"));
                    // Aquí puedes establecer otros atributos del objeto Periodos si es necesario
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return periodo;
    }
    
    private Cursos obtenerCursoPorId(int idCurso) {
        Cursos curso = null;
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    curso = new Cursos();
                    curso.setId_curso(rs.getInt("id_curso"));
                    curso.setNomCurso(rs.getString("nomCurso"));
                    curso.setGuiaCatedra(rs.getString("guiaCatedra"));
                    // Aquí puedes establecer otros atributos del objeto Cursos si es necesario
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }
    
    private Programas obtenerProgramaPorId(int idPrograma) {
        Programas programa = null;
        String sql = "SELECT * FROM programa WHERE id_programa = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPrograma);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    programa = new Programas();
                    programa.setId_programa(rs.getInt("id_programa"));
                    programa.setNomPrograma(rs.getString("nomPrograma"));
                    programa.setNivel(rs.getString("nivel"));
                    // Aquí puedes establecer otros atributos del objeto Programas si es necesario
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programa;
    }
    

    private Profesores obtenerProfesorPorId(int idProfesor) {
        Profesores profesor = null;
        String sql = "SELECT * FROM profesor WHERE id_profesor = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfesor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profesor = new Profesores();
                    profesor.setId_profesor(rs.getInt("id_profesor"));
                    profesor.setPNombre(rs.getString("pNombre"));
                    profesor.setSNombre(rs.getString("sNombre"));
                    profesor.setPApellido(rs.getString("pApellido"));
                    profesor.setSApellido(rs.getString("sApellido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesor;
    }

}
