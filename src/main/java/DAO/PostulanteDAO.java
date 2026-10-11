package DAO;

import Datos.Postulante;
import Persistencia.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PostulanteDAO {

    public void guardar(Postulante p) throws SQLException {
        String sql = "INSERT INTO \"PROYECTO\".postulantes "
                + "(documento, correo, nombre, apellido, es_docente_utec, act_laboral_fuera, "
                + "id_domicilio, id_telefono, id_institucion, id_formacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_postulante";

        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, p.getDocumento());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getApellido());
            ps.setBoolean(5, p.isEsDocenteUtec());
            ps.setBoolean(6, p.isActLaboralFuera());
            ps.setInt(7, p.getIdDomicilio());
            ps.setInt(8, p.getIdTelefono());
            ps.setInt(9, p.getIdInstitucion());

            if (p.getIdFormacion() == null) {
                ps.setNull(10, Types.INTEGER);
            } else {
                ps.setInt(10, p.getIdFormacion());
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.setIdPostulante(rs.getInt(1));   // el objeto "aprende" su id
                }
            }
        }
    }

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
        return null;   // no se encontró
    }

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

    // Convierte UNA fila de la base en UN objeto Postulante.
    // Está aparte para no repetir este código en buscarPorId y listar.
    private Postulante mapear(ResultSet rs) throws SQLException {
        Postulante p = new Postulante();
        p.setIdPostulante(rs.getInt("id_postulante"));
        p.setDocumento(rs.getString("documento"));
        p.setCorreo(rs.getString("correo"));
        p.setNombre(rs.getString("nombre"));
        p.setApellido(rs.getString("apellido"));
        p.setEsDocenteUtec(rs.getBoolean("es_docente_utec"));
        p.setActLaboralFuera(rs.getBoolean("act_laboral_fuera"));
        p.setIdDomicilio(rs.getInt("id_domicilio"));
        p.setIdTelefono(rs.getInt("id_telefono"));
        p.setIdInstitucion(rs.getInt("id_institucion"));

        int idFormacion = rs.getInt("id_formacion");
        p.setIdFormacion(rs.wasNull() ? null : idFormacion);
        return p;
    }
}