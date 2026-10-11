package DAO;

import Datos.Usuario;

public interface UsuarioDAO {
    boolean guardarUsuario(Usuario usuario, String tipoRol);
    Usuario buscarPorCedula(String cedula);
    boolean cambiarEstadoUsuario(Integer idUsuario, boolean nuevoEstado);
    boolean actualizarContrasenia(Integer idUsuario, String nuevaContrasenia);
}