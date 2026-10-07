package Datos;

public class Postulante {
private int idPostulante;
private String documento;
private String nombre;
private String apellido;
private String correo;
private String telefono;
private int id_domicilio;
private int id_institucion;
private Integer id_formacion;
private boolean esDocenteUtec;
private boolean actLaboralFuera;

public Postulante(){
}

    public int getIdPostulante() {
        return idPostulante;
    }

    public void setIdPostulante(int idPostulante) {
        this.idPostulante = idPostulante;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(int id_domicilio) {
        this.id_domicilio = id_domicilio;
    }

    public Integer getId_formacion() {
        return id_formacion;
    }

    public void setId_formacion(Integer id_formacion) {
        this.id_formacion = id_formacion;
    }

    public int getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(int id_institucion) {
        this.id_institucion = id_institucion;
    }

    public boolean isEsDocenteUtec() {
        return esDocenteUtec;
    }

    public void setEsDocenteUtec(boolean esDocenteUtec) {
        this.esDocenteUtec = esDocenteUtec;
    }

    public boolean isActLaboralFuera() {
        return actLaboralFuera;
    }

    public void setActLaboralFuera(boolean actLaboralFuera) {
        this.actLaboralFuera = actLaboralFuera;
    }
}



