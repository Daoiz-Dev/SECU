package Datos;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {

    private Integer idUsuario;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    private Boolean estado;

    // Representación en Objetos de la tabla USUARIO_TELEFONOS (1:N)
    private List<String> telefonos;

    // Constructor vacío
    public Usuario() {
        this.telefonos = new ArrayList<>();
    }

    // Constructor con todos los parámetros principales
    public Usuario(Integer idUsuario, String cedula, String nombre, String apellido,
                   String correo, String contrasenia, Boolean estado) {
        this.idUsuario = idUsuario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.telefonos = new ArrayList<>();
    }

    // Getters y Setters

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public List<String> getTelefonos() { return telefonos; }
    public void setTelefonos(List<String> telefonos) { this.telefonos = telefonos; }

    public void agregarTelefono(String telefono) {
        if (this.telefonos == null) {
            this.telefonos = new ArrayList<>();
        }
        this.telefonos.add(telefono);
    }
}