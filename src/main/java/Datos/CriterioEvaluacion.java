package Datos;

public class CriterioEvaluacion {
    private int idCriterio;
    private String nombreCriterio;
    private int pesoPorcentaje;

    public CriterioEvaluacion(){

    }

    public CriterioEvaluacion(int idCriterio, String nombreCriterio, int pesoPorcentaje) {
        this.idCriterio = idCriterio;
        this.nombreCriterio = nombreCriterio;
        this.pesoPorcentaje = pesoPorcentaje;
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

    public int getPesoPorcentaje() {
        return pesoPorcentaje;
    }

    public void setPesoPorcentaje(int pesoPorcentaje) {
        this.pesoPorcentaje = pesoPorcentaje;
    }
}
