package Modelo;
// Generated 29-nov-2017 14:40:39 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AgendaPaciente generated by hbm2java
 */
public class AgendaPaciente  implements java.io.Serializable {


     private Integer idAgendaPaciente;
     private Date fecha;
     private Date hora;
     private String observacion;
     private Date horaLlegada;
     private Date horaEntrada;
     private Date horaSalida;
     private Integer idPaciente;
     private String tipo;

    public AgendaPaciente() {
    }

	
    public AgendaPaciente(Date fecha, Date hora) {
        this.fecha = fecha;
        this.hora = hora;
    }
    public AgendaPaciente(Date fecha, Date hora, String observacion, Date horaLlegada, Date horaEntrada, Date horaSalida, Integer idPaciente, String tipo) {
       this.fecha = fecha;
       this.hora = hora;
       this.observacion = observacion;
       this.horaLlegada = horaLlegada;
       this.horaEntrada = horaEntrada;
       this.horaSalida = horaSalida;
       this.idPaciente = idPaciente;
       this.tipo = tipo;
    }
   
    public Integer getIdAgendaPaciente() {
        return this.idAgendaPaciente;
    }
    
    public void setIdAgendaPaciente(Integer idAgendaPaciente) {
        this.idAgendaPaciente = idAgendaPaciente;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getHora() {
        return this.hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public Date getHoraLlegada() {
        return this.horaLlegada;
    }
    
    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    public Date getHoraEntrada() {
        return this.horaEntrada;
    }
    
    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    public Date getHoraSalida() {
        return this.horaSalida;
    }
    
    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }
    public Integer getIdPaciente() {
        return this.idPaciente;
    }
    
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}

