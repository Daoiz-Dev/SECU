package Datos;

 // Clase que representa a un usuario con rol de administrador en el sistema
 // Hereda de la clase abstracta Usuario

public class Administrador extends Usuario {

    // Constructor
    public Administrador() {
    super();
}

    // Constructor completo
    // Se usa la palabra reservada Super para enviar datos al constructor

    public Administrador(Integer idUsuario, String cedula, String nombre, String apellido,
                         String correo, String contrasenia, Boolean estado) {
        // Le pasamos los datos al padre
        super(idUsuario, cedula, nombre, apellido, correo, contrasenia, estado);
    }

}
