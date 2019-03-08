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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Diagnostico;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class DiagnosticoFacade extends AbstractFacade<Diagnostico> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoFacade() {
        super(Diagnostico.class);
    }
    
}
