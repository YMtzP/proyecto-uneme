/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.Expediente;
import Modelo.NewHibernateUtil;
import Modelo.Pregunta;
import Modelo.RespuestasEncuesta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author YareMtz
 */
public class ManejadorEncuestas {
    
    public List obtenerPreguntas(int encuesta) {
        List<Pregunta> preguntas = null;
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            preguntas = s.createQuery("from Pregunta p where p.encuesta=?")
            .setParameter(0, encuesta).list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println("Error :cc ->" + e.getMessage());
        }
        return preguntas;
    }
    
    public boolean crearEncuesta(Empleado empleado, Expediente expediente, int encuesta) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        RespuestasEncuesta rp = new RespuestasEncuesta();
        List<Pregunta> listaPreguntas = obtenerPreguntas(encuesta);
        try {
            rp.setIdEmpleado(empleado.getIdEmpleado());
            rp.setIdExpediente(expediente.getIdExpediente());
            s = sf.openSession();
            Transaction t;
            for (int i = 0; i < listaPreguntas.size(); i++) {
                t = s.beginTransaction();
                rp.setIdPregunta(listaPreguntas.get(i).getIdPregunta());
                rp.setValorRespuesta("");
                s.save(rp);
                s.getTransaction().commit();
                s.flush();
                s.clear();
            }
            s.close();
            return true;
        } catch (Exception e) {
            System.out.println(":ccc aqui eh eh eh " + e.getMessage());
            return false;
        }

    }
    
    public Boolean existeEncuesta(Expediente expediente, int encuesta) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        List<RespuestasEncuesta> rp = null;
        try {
            s = sf.openSession();
            rp = s.createSQLQuery("select respuestasencuesta.valor_respuesta, encuesta.id_encuesta from respuestasencuesta join "
                    + "pregunta on respuestasencuesta.id_pregunta = pregunta.id_pregunta join encuesta on pregunta.encuesta=encuesta.id_encuesta "
                    + "where respuestasencuesta.id_expediente=? and encuesta.id_encuesta=?")
                    .setParameter(0, expediente.getIdExpediente())
                    .setParameter(1, encuesta)
                    .list();
            //rp = s.createQuery("from RespuestasEncuesta rp where rp.idExpediente=?").setParameter(0, expediente.getIdExpediente()).list();
            s.flush();
            s.clear();
            s.close();
            if (rp.size() != 0) {
                return true;
            }
            if (rp.size() == 0) {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Aqui es el error, vale chetos:c " + e.getMessage());
        }
        return false;
    }
    
    
    public boolean guardarRespuestas(List<RespuestasEncuesta> listaRespuestas, Expediente expediente) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            for (int i = 0; i < listaRespuestas.size(); i++) {
                s.beginTransaction();
                Query query = s.createQuery("update RespuestasEncuesta set valorRespuesta=? where idPregunta=? and idExpediente=?")
                        .setParameter(0, listaRespuestas.get(i).getValorRespuesta())
                        .setParameter(1, listaRespuestas.get(i).getIdPregunta())
                        .setParameter(2, expediente.getIdExpediente());
                query.executeUpdate();
                s.getTransaction().commit();
                s.flush();
                s.clear();
            }

            s.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error aqui :cc lo shentooo -> " + e.getMessage());
            return false;
        }
    }
    
    public List obtenerRespuestasEncuestaEstudioSocialSocioeconomico(Expediente expediente) {
        List<RespuestasEncuesta> respuestas = null;
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            respuestas = s.createQuery("from RespuestasEncuesta rp where rp.idPregunta>=143 and rp.idPregunta<=268 and rp.idExpediente=?")
                    .setParameter(0, expediente.getIdExpediente()).list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println("Error :cc -> 1.- " + e.getMessage());
        }
        return respuestas;
    }
    
    public List obtenerRespuestasEntrevistaExploratoria(Expediente expediente) {
        List<RespuestasEncuesta> respuestas = null;
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            respuestas = s.createQuery("from RespuestasEncuesta rp where rp.idPregunta>=269 and rp.idPregunta<=492 and rp.idExpediente=?")
                    .setParameter(0, expediente.getIdExpediente()).list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println("Error :cc -> 1.- " + e.getMessage());
        }
        return respuestas;
    }
    
    public List obtenerRespuestasFormatoEgreso(Expediente expediente) {
        List<RespuestasEncuesta> respuestas = null;
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            respuestas = s.createQuery("from RespuestasEncuesta rp where rp.idPregunta>=493 and rp.idPregunta<=507 and rp.idExpediente=?")
                    .setParameter(0, expediente.getIdExpediente()).list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println("Error :cc -> 1.- " + e.getMessage());
        }
        return respuestas;
    }
    
    public List obtieneRespuestasEncuestaHistoriaClinica(Expediente expediente) {
        List<RespuestasEncuesta> respuestas = null;
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            respuestas = s.createQuery("from RespuestasEncuesta rp where rp.idPregunta>=1 and rp.idPregunta<=142 and rp.idExpediente=?")
                    .setParameter(0, expediente.getIdExpediente())
                    .list();
            s.flush();
            s.clear();
            s.close();
        } catch (Exception e) {
            System.out.println("Error :cc -> 1.- " + e.getMessage());
        }
        return respuestas;
    }
    
    public Map<String, Object> cargarRespuestas(List<RespuestasEncuesta> lista){
        Map<String, Object> respuestas = new HashMap<>();
        for(int i=0; i<lista.size(); i++){
            respuestas.put("r"+i, lista.get(i).getValorRespuesta());
        }
        return respuestas;
    }
}
