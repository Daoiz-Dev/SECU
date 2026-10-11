package Logica;

import Datos.*;
import DAO.UsuarioDAO;

public class GestorUsuario {

    // 1. Instancia estática privada
    // (Patrón Singleton)
    private static GestorUsuario instancia;

    // Atributo DAO para interactuar con la base de datos

    private final UsuarioDAO usuarioDAO;

    // 2. Constructor privado

    private GestorUsuario() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // 3. Método público estático para obtener la instancia única (Singleton)
    public static synchronized GestorUsuario getInstance() {
        if (instancia == null) {
            instancia = new GestorUsuario();
        }
        return instancia;
    }

    //  MÉTODOS DE LÓGICA DE NEGOCIO

    // Registra un nuevo usuario aplicando las validaciones previas.

    public boolean registrarUsuario(String tipoRol, Integer idUsuario, String cedula,
                                    String nombre, String apellido, String correo,
                                    String contrasenia, Boolean estado) {

        // Validaciones básicas de reglas de negocio
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía.");
        }
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }

        // Crear el objeto Usuario utilizando el Factory Method que programaste
        Usuario nuevoUsuario = UsuarioFactory.crearUsuario(
                tipoRol, idUsuario, cedula, nombre, apellido, correo, contrasenia, estado
        );

        // Guardar a través del DAO
        return usuarioDAO.guardarUsuario(nuevoUsuario, tipoRol);
    }

    // Busca un usuario por cédula.

    public Usuario obtenerUsuarioPorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return null;
        }
        return usuarioDAO.buscarPorCedula(cedula);
    }

    // Activa la cuenta de un usuario.

    public boolean activarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getIdUsuario() == null) {
            return false;
        }
        usuario.setEstado(true);
        return usuarioDAO.cambiarEstadoUsuario(usuario.getIdUsuario(), true);
    }

    // Desactiva la cuenta de un usuario.

    public boolean desactivarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getIdUsuario() == null) {
            return false;
        }
        usuario.setEstado(false);
        return usuarioDAO.cambiarEstadoUsuario(usuario.getIdUsuario(), false);
    }

    // Cambia la contraseña del usuario.

    public boolean cambiarContrasenia(Usuario usuario, String nuevaContrasenia) {
        if (usuario == null || nuevaContrasenia == null || nuevaContrasenia.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

        // En la Etapa 3 se agregará la llamada a la clase de cifrado
        usuario.setContrasenia(nuevaContrasenia);
        return usuarioDAO.actualizarContrasenia(usuario.getIdUsuario(), nuevaContrasenia);
    }
}