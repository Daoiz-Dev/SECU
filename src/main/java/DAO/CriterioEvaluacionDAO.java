package DAO;

import Datos.CriterioEvaluacion;
import java.sql.SQLException;
import java.util.List;

public interface  CriterioEvaluacionDAO {
    void guardar(CriterioEvaluacion c) throws SQLException;
    CriterioEvaluacion buscarPorId(int id) throws SQLException;
    List<CriterioEvaluacion> listar() throws SQLException;
    List<CriterioEvaluacion> listarPorSeccion(int idSeccion) throws SQLException;
}
