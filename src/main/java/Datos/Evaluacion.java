package Datos;

public class Evaluacion {
    private int idEvaluacion;
    private int idPostulacion;
    private int idEvaluador;
    private double puntajeTotal;
    private String comentarioEvaluador;
    private String estado;

    public Evaluacion(){
    this.estado = "PENDIENTE";
    }

    public Evaluacion(int idEvaluacion, int idPostulacion, int idEvaluador, double puntajeTotal, String comentarioEvaluador, String estado) {
        this.idEvaluacion = idEvaluacion;
        this.idPostulacion = idPostulacion;
        this.idEvaluador = idEvaluador;
        this.puntajeTotal = puntajeTotal;
        this.comentarioEvaluador = comentarioEvaluador;
        this.estado = estado;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(int idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public int getIdEvaluador() {
        return idEvaluador;
    }

    public void setIdEvaluador(int idEvaluador) {
        this.idEvaluador = idEvaluador;
    }

    public double getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(double puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
