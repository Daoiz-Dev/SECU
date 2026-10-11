package DAO;

import Datos.SeccionRubrica;
import Persistencia.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeccionRubricaDAO {

    public void guardar(SeccionRubrica s) throws SQLException {
        String sql = "INSERT INTO \"PROYECTO\".seccion_rubrica (nombre_seccion, peso) "
                + "VALUES (?, ?) RETURNING id_seccion";

        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql)) {
            ps.setString(1, s.getNombreSeccion());
            ps.setDouble(2, s.getPeso());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setIdSeccion(rs.getInt(1));   // el objeto "aprende" su id
                }
            }
        }
    }

    public SeccionRubrica buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM \"PROYECTO\".seccion_rubrica WHERE id_seccion = ?";
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

    public List<SeccionRubrica> listar() throws SQLException {
        List<SeccionRubrica> lista = new ArrayList<>();
        String sql = "SELECT * FROM \"PROYECTO\".seccion_rubrica ORDER BY id_seccion";
        try (PreparedStatement ps = ConexionBD.getInstancia().getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // Convierte UNA fila de la base en UN objeto SeccionRubrica
    private SeccionRubrica mapear(ResultSet rs) throws SQLException {
        SeccionRubrica s = new SeccionRubrica();
        s.setIdSeccion(rs.getInt("id_seccion"));
        s.setNombreSeccion(rs.getString("nombre_seccion"));
        s.setPeso(rs.getDouble("peso"));
        return s;
    }
}