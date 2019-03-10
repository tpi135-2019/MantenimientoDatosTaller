/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.EstadoPaso;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class EstadoPasoFacade extends AbstractFacade<EstadoPaso> implements FacadeGenerico<EstadoPaso>{

    @PersistenceContext(unitName = "PU_talleres")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoPasoFacade() {
        super(EstadoPaso.class);
    }
    
}
