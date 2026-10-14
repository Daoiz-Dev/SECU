package DAO;

import Datos.Postulante;
import Persistencia.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PostulanteDAOImpl implements PostulanteDAO {

    @Override
    public void guardar(Postulante p) throws SQLException {
        // SQL ajustado exactamente a la tabla POSTULANTES de la BDD actual
        String sql = "INSERT INTO \"PROYECTO\".postulantes "
                + "(documento, correo, nombre, apellido, id_domicilio, id_institucion, id_formacion, es_docente_utec, act_laboral_fuera) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_postulante";

        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, p.getDocumento());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getApellido());
            ps.setInt(5, p.getIdDomicilio());
            ps.setInt(6, p.getIdInstitucion());

            if (p.getIdFormacion() == null) {
                ps.setNull(7, Types.INTEGER);
            } else {
                ps.setInt(7, p.getIdFormacion());
            }

            ps.setBoolean(8, p.isEsDocenteUtec());
            ps.setBoolean(9, p.isActLaboralFuera());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.setIdPostulante(rs.getInt(1)); // El objeto obtiene su id_postulante generado
                }
            }
        }
    }

    @Override
    public Postulante buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"PROYECTO\".postulantes WHERE id_postulante = ?";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Postulante> listar() throws SQLException {
        List<Postulante> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"PROYECTO\".postulantes ORDER BY id_postulante";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // Guardar teléfono/celular en la tabla 1:N POSTULANTE_CELULARES
    @Override
    public void guardarCelular(int idPostulante, String numCelular) throws SQLException {
        String sql = "INSERT INTO \"PROYECTO\".postulante_celulares (id_postulante, num_celular) VALUES (?, ?)";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, idPostulante);
            ps.setString(2, numCelular);
            ps.executeUpdate();
        }
    }

    // Obtener la lista de celulares asociados a un postulante
    @Override
    public List<String> obtenerCelularesPorPostulante(int idPostulante) throws SQLException {
        List<String> celulares = new ArrayList<>();
        String sql = "SELECT num_celular FROM \"PROYECTO\".postulante_celulares WHERE id_postulante = ?";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, idPostulante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    celulares.add(rs.getString("num_celular"));
                }
            }
        }
        return celulares;
    }

    // Mapeo sin la columna id_telefono
    private Postulante mapear(ResultSet rs) throws SQLException {
        Postulante p = new Postulante();
        p.setIdPostulante(rs.getInt("id_postulante"));
        p.setDocumento(rs.getString("documento"));
        p.setCorreo(rs.getString("correo"));
        p.setNombre(rs.getString("nombre"));
        p.setApellido(rs.getString("apellido"));
        p.setIdDomicilio(rs.getInt("id_domicilio"));
        p.setIdInstitucion(rs.getInt("id_institucion"));

        int idFormacion = rs.getInt("id_formacion");
        p.setIdFormacion(rs.wasNull() ? null : idFormacion);

        p.setEsDocenteUtec(rs.getBoolean("es_docente_utec"));
        p.setActLaboralFuera(rs.getBoolean("act_laboral_fuera"));
        return p;
    }
}