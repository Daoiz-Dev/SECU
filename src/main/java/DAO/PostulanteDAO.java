package DAO;

import Datos.Postulante;
import java.sql.SQLException;
import java.util.List;

public interface PostulanteDAO {
    void guardar(Postulante p) throws SQLException;
    Postulante buscarPorId(int id) throws SQLException;
    List<Postulante> listar() throws SQLException;
}