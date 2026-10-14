package DAO;

import Datos.Usuario;
import java.util.List;

public interface UsuarioDAO {
    boolean guardarUsuario(Usuario usuario, String tipoRol);
    Usuario buscarPorCedula(String cedula);
    boolean cambiarEstadoUsuario(Integer idUsuario, boolean nuevoEstado);
    boolean actualizarContrasenia(Integer idUsuario, String nuevaContrasenia);

    // Métodos para la gestión de teléfonos (tabla USUARIO_TELEFONOS 1:N)
    boolean guardarTelefono(int idUsuario, String numTelefono);
    List<String> obtenerTelefonosPorUsuario(int idUsuario);
}