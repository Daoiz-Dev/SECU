package Datos;

// Clase que implementa el patrón de diseño Factory Method.
// Centraliza la lógica de creación de los distintos tipos de Usuarios.

public class UsuarioFactory {
    public static Usuario crearUsuario(String tipoRol, Integer idUsuario, String cedula,
                                       String nombre, String apellido, String correo,
                                       String contrasenia, Boolean estado) {

        // Buena práctica: Validar que el rol no venga vacío
        if (tipoRol == null || tipoRol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede ser nulo o vacío");
        }

        // Según el string del rol, instanciamos la clase hija correspondiente
        if (tipoRol.equalsIgnoreCase("ADMINISTRADOR")) {
            return new Administrador(idUsuario, cedula, nombre, apellido, correo, contrasenia, estado);
        }
        else if (tipoRol.equalsIgnoreCase("EVALUADOR_EXTERNO")) {
            return new EvaluadorExterno(idUsuario, cedula, nombre, apellido, correo, contrasenia, estado);
        }
        else {
            // Si mandan un rol que no existe, el programa frena y avisa el error
            throw new IllegalArgumentException("Rol de usuario desconocido: " + tipoRol);
        }
    }
}
