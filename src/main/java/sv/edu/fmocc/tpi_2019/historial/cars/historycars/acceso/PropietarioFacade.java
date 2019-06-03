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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class PropietarioFacade extends AbstractFacade<Propietario> implements FacadeGenerico<Propietario> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PropietarioFacade() {
        super(Propietario.class);
    }

    public List historialPropietarios(String placa) {
        if (placa != null && !placa.trim().isEmpty()) {
            try {
                Query query = em.createNamedQuery("Vehiculo.HistorialPropietarios");
                query.setParameter("placa", placa);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }

    public List<Vehiculo> vehiculosPorPropietario(Integer propietario) {
        if (propietario != null && propietario > 0 ) {
            try {
                Query query = em.createNamedQuery("Propietario.VehiculosPropietario");
                query.setParameter("idPropietario", propietario);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }
    
    public List<Propietario> buscarPropietarioLike(String id){
        if(id!=null){
            try {
                Query query= em.createNamedQuery("Propietario.findByIdLike");
                query.setParameter("id", id);
                return query.getResultList();
            } catch (Exception e) {
             logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }
    

}
