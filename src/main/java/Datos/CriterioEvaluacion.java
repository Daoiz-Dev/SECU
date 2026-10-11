package Datos;

public class CriterioEvaluacion {
    private int idCriterio;
    private String nombreCriterio;
    private int idSeccion;

    public CriterioEvaluacion() {

    }

    public CriterioEvaluacion(int idCriterio, String nombreCriterio, int idSeccion) {
        this.idCriterio = idCriterio;
        this.nombreCriterio = nombreCriterio;
        this.idSeccion = idSeccion;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public void setNombreCriterio(String nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }
}