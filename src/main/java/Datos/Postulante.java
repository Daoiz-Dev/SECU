package Datos;

public class Postulante {
private int idPostulante;
private String documento;
private String nombre;
private String apellido;
private String correo;
private int idDomicilio;
private int idTelefono;
private int idInstitucion;
private Integer idFormacion;
private boolean esDocenteUtec;
private boolean actLaboralFuera;

public Postulante(){
}

    public Postulante(int idPostulante, String documento, String apellido, String nombre, String correo, int idDomicilio, int idTelefono, int idInstitucion, Integer idFormacion, boolean esDocenteUtec, boolean actLaboralFuera) {
        this.idPostulante = idPostulante;
        this.documento = documento;
        this.apellido = apellido;
        this.nombre = nombre;
        this.correo = correo;
        this.idDomicilio = idDomicilio;
        this.idTelefono = idTelefono;
        this.idInstitucion = idInstitucion;
        this.idFormacion = idFormacion;
        this.esDocenteUtec = esDocenteUtec;
        this.actLaboralFuera = actLaboralFuera;
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

    public int getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(int idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Integer getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Integer idFormacion) {
        this.idFormacion = idFormacion;
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



