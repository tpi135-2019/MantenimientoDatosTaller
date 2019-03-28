/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class SucursalFacade extends AbstractFacade<Sucursal> implements FacadeGenerico<Sucursal> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalFacade() {
        super(Sucursal.class);
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Sucursal lugarReparacion(int reparacion) {
        if (reparacion >= 0) {
            try {
                Query query = em.createNamedQuery("Sucursal.Reparacion");
                query.setParameter("id", reparacion);
                return (Sucursal) query.getSingleResult();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return new Sucursal();
    }

}
