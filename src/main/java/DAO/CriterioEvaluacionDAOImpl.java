package DAO;

import Datos.CriterioEvaluacion;
import Persistencia.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriterioEvaluacionDAOImpl implements CriterioEvaluacionDAO {

    @Override
    public void guardar(CriterioEvaluacion c) throws SQLException {
        String sql = "INSERT INTO \"PROYECTO\".criterios (nombre_criterio, id_seccion) "
                + "VALUES (?, ?) RETURNING id_criterio";

        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, c.getNombreCriterio());
            ps.setInt(2, c.getIdSeccion());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c.setIdCriterio(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public CriterioEvaluacion buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"PROYECTO\".criterios WHERE id_criterio = ?";
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
    public List<CriterioEvaluacion> listar() throws SQLException {
        List<CriterioEvaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"PROYECTO\".criterios ORDER BY id_criterio";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    @Override
    public List<CriterioEvaluacion> listarPorSeccion(int idSeccion) throws SQLException {
        List<CriterioEvaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"PROYECTO\".criterios WHERE id_seccion = ? ORDER BY id_criterio";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setInt(1, idSeccion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    private CriterioEvaluacion mapear(ResultSet rs) throws SQLException {
        CriterioEvaluacion c = new CriterioEvaluacion();
        c.setIdCriterio(rs.getInt("id_criterio"));
        c.setNombreCriterio(rs.getString("nombre_criterio"));
        c.setIdSeccion(rs.getInt("id_seccion"));
        return c;
    }
}