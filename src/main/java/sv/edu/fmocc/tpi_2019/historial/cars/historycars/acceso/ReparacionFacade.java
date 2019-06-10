/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.util.Loggable;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class ReparacionFacade extends AbstractFacade<Reparacion> implements FacadeGenerico<Reparacion> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReparacionFacade() {
        super(Reparacion.class);
    }

    public List<Reparacion> reparacionesPorPlaca(String placa) {
        if (placa != null && !placa.isEmpty()) {
            try {
                Query query = em.createNamedQuery("ReparacionPorPlaca");
                query.setParameter("placa", placa);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    public List<Reparacion> reparacionPorDiagnostico(int idDiagnostico) {

        if (idDiagnostico >= 0) {
            try {
                Query query = em.createNamedQuery("Reparacion.Diagnostico");
                query.setParameter("id", idDiagnostico);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    public List<Reparacion> reparacionPorPersonal(int idMecanico) {

        if (idMecanico >= 0) {
            try {
                Query query = em.createNamedQuery("Reaparacion.Personal");
                query.setParameter("id", idMecanico);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }
    
    public List<Reparacion> reparacionEntreFechas(Date desde,Date hasta){
        
        if(desde!=null && hasta!=null){
            try {
                Query query = em.createNamedQuery("Reparacion.Fechas");
                query.setParameter("desde",desde, TemporalType.DATE);
                query.setParameter("hasta", hasta, TemporalType.DATE);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        return Collections.emptyList();
        
    }
        @Loggable
       public List<Sucursal> lugarReparacion(int reparacion) {
        if (reparacion >= 0) {
            try {
                Query query = em.createNamedQuery("Sucursal.Reparacion");
                query.setParameter("id", reparacion);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }


}
