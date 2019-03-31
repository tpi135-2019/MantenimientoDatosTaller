/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class PersonalFacade extends AbstractFacade<Personal> implements FacadeGenerico<Personal> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalFacade() {
        super(Personal.class);
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public List personalPorReparacion(int idReparacion) {

        if (idReparacion >= 0) {

            try {
                Query query = em.createNamedQuery("Personal.Reparacion");
                query.setParameter("id", idReparacion);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List personalPorProceso(int idProceso) {

        if (idProceso >= 0) {
            try {
                Query query = em.createNamedQuery("Personal.Proceso");
                query.setParameter("id", idProceso);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());

            }
        }
        return Collections.EMPTY_LIST;
    }

}
