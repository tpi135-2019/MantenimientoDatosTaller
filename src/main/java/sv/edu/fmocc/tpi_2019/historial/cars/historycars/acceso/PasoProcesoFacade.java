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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class PasoProcesoFacade extends AbstractFacade<PasoProceso> implements FacadeGenerico<PasoProceso> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasoProcesoFacade() {
        super(PasoProceso.class);
    }

    public List<Paso> pasosPorProceso(int proceso, String paso) {
        if (proceso > 0) {
            try {
                Query query = em.createNamedQuery("PasoProceso.findByProceso");
                query.setParameter("idProceso", proceso);
                query.setParameter("paso", paso);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        return Collections.emptyList();
    }

}
