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

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class PasoFacade extends AbstractFacade<Paso> implements FacadeGenerico<Paso>{

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasoFacade() {
        super(Paso.class);
    }
 
    public List<Paso> pasoReparacion(int reparacion){
        if(reparacion>0){
            try {
                Query query=em.createNamedQuery("Paso.Reparacion");
                query.setParameter("id",reparacion);
                return query.getResultList();
            } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            }
        }
        return Collections.emptyList();
    }
    
}
