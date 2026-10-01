package Datos;

import java.time.LocalDate;

public class Postulacion {
    private Integer idPostulacion;
    private String nombreProyecto;
    private String lineaInvestigacion;
    private LocalDate fechaSolicitud;
    private Integer cantidadMeses;
    private String grupoInvestigacion;
    private String otroGrupoInvestigacion;
    private String observacionesCES;
    private Integer ordenPreCalculado;
    private double puntajeConsolidado;
    private Integer ordenFinalCES;
    private String juicioGlobal;
    //private Sede idSede;
    //private Convocatoria idConvocatoria;
    //private DepartamentoAcademico idDepartamentoAcademico;
    //private Datos.Postulante idPostulante;

    public Postulacion(){

    }
    public Postulacion(Integer idPostulacion, String nombreProyecto, String lineaInvestigacion, LocalDate fechaSolicitud,
                       Integer cantidadMeses, String grupoInvestigacion, String otroGrupoInvestigacion, String observacionesCES,
                       Integer ordenPreCalculado, Integer puntajeConsolidado, Integer ordenFinalCES, String juicioGlobal) {

                       //, Sede idSede, Convocatoria idConvocatoria, DepartamentoAcademico idDepartamentoAcademico, Datos.Postulante idPostulante

        this.idPostulacion = idPostulacion;
        this.nombreProyecto = nombreProyecto;
        this.lineaInvestigacion = lineaInvestigacion;
        this.fechaSolicitud = fechaSolicitud;
        this.cantidadMeses = cantidadMeses;
        this.grupoInvestigacion = grupoInvestigacion;
        this.otroGrupoInvestigacion = otroGrupoInvestigacion;
        this.observacionesCES = observacionesCES;
        this.ordenPreCalculado = ordenPreCalculado;
        this.puntajeConsolidado = puntajeConsolidado;
        this.ordenFinalCES = ordenFinalCES;
        this.juicioGlobal = juicioGlobal;
        //this.idSede = idSede;
        //this.idConvocatoria = idConvocatoria;
        //this.idDepartamentoAcademico = idDepartamentoAcademico;
        //this.idPostulante = idPostulante;
        }


    public Integer getIdPostulacion() {
        return idPostulacion;
    }
    public String getNombreProyecto() {
        return nombreProyecto;
    }
    public String getLineaInvestigacion() {
        return lineaInvestigacion;
    }
    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }
    public Integer getCantidadMeses() {
        return cantidadMeses;
    }
    public String getGrupoInvestigacion() {
        return grupoInvestigacion;
    }
    public String getOtroGrupoInvestigacion() {
        return otroGrupoInvestigacion;
    }
    public String getObservacionesCES() {
        return observacionesCES;
    }
    public Integer getOrdenPreCalculado() {
        return ordenPreCalculado;
    }
    public Integer getPuntajeConsolidado() {
        return puntajeConsolidado;
    }
    public Integer getOrdenFinalCES() {
        return ordenFinalCES;
    }
    public String getJuicioGlobal() {
        return juicioGlobal;
    }
    /*
    public Sede getIdSede() {
        return idSede;
    }
    public Convocatoria getIdConvocatoria() {
        return idConvocatoria;
    }
    public DepartamentoAcademico getIdDepartamentoAcademico() {
        return idDepartamentoAcademico;
    }
    public Datos.Postulante getIdPostulante() {
        return idPostulante;
    }
    */

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public void setLineaInvestigacion(String lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setCantidadMeses(Integer cantidadMeses) {
        this.cantidadMeses = cantidadMeses;
    }

    public void setGrupoInvestigacion(String grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }

    public void setOtroGrupoInvestigacion(String otroGrupoInvestigacion) {
        this.otroGrupoInvestigacion = otroGrupoInvestigacion;
    }

    public void setObservacionesCES(String observacionesCES) {
        this.observacionesCES = observacionesCES;
    }

    public void setOrdenPreCalculado(Integer ordenPreCalculado) {
        this.ordenPreCalculado = ordenPreCalculado;
    }

    public void setPuntajeConsolidado(Integer puntajeConsolidado) {
        this.puntajeConsolidado = puntajeConsolidado;
    }

    public void setOrdenFinalCES(Integer ordenFinalCES) {
        this.ordenFinalCES = ordenFinalCES;
    }

    public void setJuicioGlobal(String juicioGlobal) {
        this.juicioGlobal = juicioGlobal;
    }
}
