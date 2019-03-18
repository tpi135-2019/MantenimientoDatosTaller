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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.HistorialPropietario;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class HistorialPropietarioFacade extends AbstractFacade<HistorialPropietario> implements FacadeGenerico<HistorialPropietario>{

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialPropietarioFacade() {
        super(HistorialPropietario.class);
    }
    
}
