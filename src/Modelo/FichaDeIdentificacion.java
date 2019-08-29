package Modelo;
// Generated 29-nov-2017 14:40:39 by Hibernate Tools 4.3.1



/**
 * FichaDeIdentificacion generated by hbm2java
 */
public class FichaDeIdentificacion  implements java.io.Serializable {


     private Integer idFichaDeIdentificacion;
     private String referidoPor;
     private String religion;
     private String grupoSanguineo;
     private String hablaLenguaIndigena;
     private String lenguaIndigena;
     private String migrante;
     private String derechohabiencia;
     private String numeroDerechohabiencia;
     private String motivoConsulta;
     private String escolaridad;
     private String nombreContacto;
     private String apPaternoContacto;
     private String apMaternoContacto;
     private String parentesco;
     private Integer idDomicilioContacto;
     private String telefonoContacto;
     private String celularContacto;
     private Integer idEmpleado;

    public FichaDeIdentificacion() {
    }

	
    public FichaDeIdentificacion(String referidoPor, String religion, String hablaLenguaIndigena, String migrante, String motivoConsulta, String escolaridad, String nombreContacto, String apPaternoContacto, String apMaternoContacto, String parentesco) {
        this.referidoPor = referidoPor;
        this.religion = religion;
        this.hablaLenguaIndigena = hablaLenguaIndigena;
        this.migrante = migrante;
        this.motivoConsulta = motivoConsulta;
        this.escolaridad = escolaridad;
        this.nombreContacto = nombreContacto;
        this.apPaternoContacto = apPaternoContacto;
        this.apMaternoContacto = apMaternoContacto;
        this.parentesco = parentesco;
    }
    public FichaDeIdentificacion(String referidoPor, String religion, String grupoSanguineo, String hablaLenguaIndigena, String lenguaIndigena, String migrante, String derechohabiencia, String numeroDerechohabiencia, String motivoConsulta, String escolaridad, String nombreContacto, String apPaternoContacto, String apMaternoContacto, String parentesco, Integer idDomicilioContacto, String telefonoContacto, String celularContacto, Integer idEmpleado) {
       this.referidoPor = referidoPor;
       this.religion = religion;
       this.grupoSanguineo = grupoSanguineo;
       this.hablaLenguaIndigena = hablaLenguaIndigena;
       this.lenguaIndigena = lenguaIndigena;
       this.migrante = migrante;
       this.derechohabiencia = derechohabiencia;
       this.numeroDerechohabiencia = numeroDerechohabiencia;
       this.motivoConsulta = motivoConsulta;
       this.escolaridad = escolaridad;
       this.nombreContacto = nombreContacto;
       this.apPaternoContacto = apPaternoContacto;
       this.apMaternoContacto = apMaternoContacto;
       this.parentesco = parentesco;
       this.idDomicilioContacto = idDomicilioContacto;
       this.telefonoContacto = telefonoContacto;
       this.celularContacto = celularContacto;
       this.idEmpleado = idEmpleado;
    }
   
    public Integer getIdFichaDeIdentificacion() {
        return this.idFichaDeIdentificacion;
    }
    
    public void setIdFichaDeIdentificacion(Integer idFichaDeIdentificacion) {
        this.idFichaDeIdentificacion = idFichaDeIdentificacion;
    }
    public String getReferidoPor() {
        return this.referidoPor;
    }
    
    public void setReferidoPor(String referidoPor) {
        this.referidoPor = referidoPor;
    }
    public String getReligion() {
        return this.religion;
    }
    
    public void setReligion(String religion) {
        this.religion = religion;
    }
    public String getGrupoSanguineo() {
        return this.grupoSanguineo;
    }
    
    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    public String getHablaLenguaIndigena() {
        return this.hablaLenguaIndigena;
    }
    
    public void setHablaLenguaIndigena(String hablaLenguaIndigena) {
        this.hablaLenguaIndigena = hablaLenguaIndigena;
    }
    public String getLenguaIndigena() {
        return this.lenguaIndigena;
    }
    
    public void setLenguaIndigena(String lenguaIndigena) {
        this.lenguaIndigena = lenguaIndigena;
    }
    public String getMigrante() {
        return this.migrante;
    }
    
    public void setMigrante(String migrante) {
        this.migrante = migrante;
    }
    public String getDerechohabiencia() {
        return this.derechohabiencia;
    }
    
    public void setDerechohabiencia(String derechohabiencia) {
        this.derechohabiencia = derechohabiencia;
    }
    public String getNumeroDerechohabiencia() {
        return this.numeroDerechohabiencia;
    }
    
    public void setNumeroDerechohabiencia(String numeroDerechohabiencia) {
        this.numeroDerechohabiencia = numeroDerechohabiencia;
    }
    public String getMotivoConsulta() {
        return this.motivoConsulta;
    }
    
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
    public String getEscolaridad() {
        return this.escolaridad;
    }
    
    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }
    public String getNombreContacto() {
        return this.nombreContacto;
    }
    
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
    public String getApPaternoContacto() {
        return this.apPaternoContacto;
    }
    
    public void setApPaternoContacto(String apPaternoContacto) {
        this.apPaternoContacto = apPaternoContacto;
    }
    public String getApMaternoContacto() {
        return this.apMaternoContacto;
    }
    
    public void setApMaternoContacto(String apMaternoContacto) {
        this.apMaternoContacto = apMaternoContacto;
    }
    public String getParentesco() {
        return this.parentesco;
    }
    
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    public Integer getIdDomicilioContacto() {
        return this.idDomicilioContacto;
    }
    
    public void setIdDomicilioContacto(Integer idDomicilioContacto) {
        this.idDomicilioContacto = idDomicilioContacto;
    }
    public String getTelefonoContacto() {
        return this.telefonoContacto;
    }
    
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
    public String getCelularContacto() {
        return this.celularContacto;
    }
    
    public void setCelularContacto(String celularContacto) {
        this.celularContacto = celularContacto;
    }
    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }




}

