package DAO;

import Datos.Evaluacion;
import java.sql.SQLException;
import java.util.List;

public interface EvaluacionDAO {
    void guardar(Evaluacion e) throws SQLException;
    Evaluacion buscarPorId(int id) throws SQLException;
    List<Evaluacion> listarPorPostulacion(int idPostulacion) throws SQLException;
    List<Evaluacion> listarPorEvaluador(int idEvaluador) throws SQLException;
    void actualizar(Evaluacion e) throws SQLException;
    void eliminar(int id) throws SQLException;
}
