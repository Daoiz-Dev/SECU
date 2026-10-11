package DAO;

import Datos.SeccionRubrica;
import java.sql.SQLException;
import java.util.List;

public interface SeccionRubricaDAO {
    void guardar(SeccionRubrica s) throws SQLException;
    SeccionRubrica buscarPorId(int id) throws SQLException;
    List<SeccionRubrica> listar() throws SQLException;
}
