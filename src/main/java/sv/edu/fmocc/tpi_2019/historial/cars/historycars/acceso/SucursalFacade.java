/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
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

    public List lugarReparacion(int reparacion) {
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

    public List<Personal> personalPorSucursal(int idSucursal, String nombre) {

        if (idSucursal >= 0) {
            try {
                Query query = em.createNamedQuery("Personal.SucursalNombreLike");
                query.setParameter("idSucursal", idSucursal);
                query.setParameter("nombre", nombre);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());

            }
        }
        return Collections.emptyList();
    }

}
