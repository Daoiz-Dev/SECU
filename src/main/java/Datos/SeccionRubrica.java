package Datos;

public class SeccionRubrica {
    private int idSeccion;
    private String nombreSeccion;
    private double peso;

    public SeccionRubrica() {

    }

    public SeccionRubrica(int idSeccion, String nombreSeccion, double peso) {
        this.idSeccion = idSeccion;
        this.nombreSeccion = nombreSeccion;
        this.peso = peso;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}