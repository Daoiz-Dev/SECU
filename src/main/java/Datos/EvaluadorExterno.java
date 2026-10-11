package Datos;

 //Clase que representa a un usuario con rol de Evaluador Externo.
 // Hereda de la clase abstracta Usuario.

public class EvaluadorExterno extends Usuario {

    // Constructor vacío.
    public EvaluadorExterno() {
        super();
    }

    // Constructor completo.
    // Envía los datos de inicialización a la clase padre.

    public EvaluadorExterno(Integer idUsuario, String cedula, String nombre, String apellido,
                            String correo, String contrasenia, Boolean estado) {
        super(idUsuario, cedula, nombre, apellido, correo, contrasenia, estado);
    }
}
