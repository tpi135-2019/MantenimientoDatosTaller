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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class ProcesoFacade extends AbstractFacade<Proceso> implements FacadeGenerico<Proceso> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoFacade() {
        super(Proceso.class);
    }

    public List<Sucursal> sucursalPorProceso(Integer idProceso) {
        if (idProceso != null && idProceso >= 0) {
            try {
                Query query = em.createNamedQuery("Proceso.Sucursal");
                query.setParameter("idProceso", idProceso);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }
    
    public List<Proceso> findProcesoNombreLike(String proceso) {
            try {
                Query query = em.createNamedQuery("Proceso.findByNombre");
                query.setParameter("nombre", proceso);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        return Collections.emptyList();
    }

}
