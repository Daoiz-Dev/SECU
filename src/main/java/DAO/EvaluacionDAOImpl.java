package DAO;

import Datos.Evaluacion;
import Persistencia.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDAOImpl implements EvaluacionDAO {

    @Override
    public void guardar(Evaluacion e) throws SQLException {
        String sql = "INSERT INTO \"PROYECTO\".Evaluaciones "
                + "(id_postulacion, id_evaluador, puntaje_total, comentario_evaluador, estado) "
                + "VALUES (?, ?, ?, ?, ?) RETURNING id_evaluacion";

        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, e.getIdPostulacion());
            ps.setInt(2, e.getIdEvaluador());

            if (e.getPuntajeTotal() > 0) {
                ps.setDouble(3, e.getPuntajeTotal());
            } else {
                ps.setNull(3, Types.NUMERIC);
            }

            ps.setString(4, e.getComentarioEvaluador());
            ps.setString(5, e.getEstado());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e.setIdEvaluacion(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Evaluacion buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"PROYECTO\".Evaluaciones WHERE id_evaluacion = ?";
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
    public List<Evaluacion> listarPorPostulacion(int idPostulacion) throws SQLException {
        List<Evaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"PROYECTO\".Evaluaciones WHERE id_postulacion = ? ORDER BY id_evaluacion";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, idPostulacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public List<Evaluacion> listarPorEvaluador(int idEvaluador) throws SQLException {
        List<Evaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"PROYECTO\".Evaluaciones WHERE id_evaluador = ? ORDER BY id_evaluacion";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, idEvaluador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Evaluacion e) throws SQLException {
        String sql = "UPDATE \"PROYECTO\".Evaluaciones SET puntaje_total = ?, comentario_evaluador = ?, estado = ? "
                + "WHERE id_evaluacion = ?";

        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            if (e.getPuntajeTotal() > 0) {
                ps.setDouble(1, e.getPuntajeTotal());
            } else {
                ps.setNull(1, Types.NUMERIC);
            }
            ps.setString(2, e.getComentarioEvaluador());
            ps.setString(3, e.getEstado());
            ps.setInt(4, e.getIdEvaluacion());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM \"PROYECTO\".Evaluaciones WHERE id_evaluacion = ?";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Evaluacion mapear(ResultSet rs) throws SQLException {
        Evaluacion e = new Evaluacion();
        e.setIdEvaluacion(rs.getInt("id_evaluacion"));
        e.setIdPostulacion(rs.getInt("id_postulacion"));
        e.setIdEvaluador(rs.getInt("id_evaluador"));
        e.setPuntajeTotal(rs.getDouble("puntaje_total"));
        e.setComentarioEvaluador(rs.getString("comentario_evaluador"));
        e.setEstado(rs.getString("estado"));
        return e;
    }
}