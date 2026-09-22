import java.time.LocalDate;

public class Postulaciones {
    private Integer idPostulacion;
    private String nombreProyecto;
    private String lineaInvestigacion;
    private LocalDate fechaSolicitud;
    private Integer cantidadMeses;
    private String grupoInvestigacion;
    private String otroGrupoInvestigacion;
    private String observacionesCES;
    private Integer ordenPreCalculado;
    private Integer puntajeConsolidado;
    private Integer ordenFinalCES;
    private String juicioGlobal;
    private Sede idSede;
    private Convocatoria idConvocatoria;
    private DepartamentoAcademico idDepartamentoAcademico;
    private Postulante idPostulante;

    public Postulaciones(){

    }
    public Postulaciones(Integer idPostulacion, String nombreProyecto, String lineaInvestigacion, LocalDate fechaSolicitud,
                         Integer cantidadMeses, String grupoInvestigacion, String otroGrupoInvestigacion, String observacionesCES,
                         Integer ordenPreCalculado, Integer puntajeConsolidado, Integer ordenFinalCES, String juicioGlobal,
                         Sede idSede, Convocatoria idConvocatoria, DepartamentoAcademico idDepartamentoAcademico, Postulante idPostulante) {
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
        this.idSede = idSede;
        this.idConvocatoria = idConvocatoria;
        this.idDepartamentoAcademico = idDepartamentoAcademico;
        this.idPostulante = idPostulante; }


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
    public Sede getIdSede() {
        return idSede;
    }
    public Convocatoria getIdConvocatoria() {
        return idConvocatoria;
    }
    public DepartamentoAcademico getIdDepartamentoAcademico() {
        return idDepartamentoAcademico;
    }
    public Postulante getIdPostulante() {
        return idPostulante;
    }

}
