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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class DiagnosticoFacade extends AbstractFacade<Diagnostico> implements FacadeGenerico<Diagnostico> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoFacade() {
        super(Diagnostico.class);
    }

    public List<Diagnostico> diagnosticoPorPlaca(String placa) {

        if(placa!=null && !placa.isEmpty()){
        try {
            Query query = em.createNamedQuery("DiagnosticoPorPlaca");
            query.setParameter("placa", placa);
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
        return Collections.emptyList();
    }
    
      public List<Diagnostico> findDiagnostico(String id) {
        if(id!=null){
        try {
            Query query = em.createNamedQuery("Diagnostico.findByIdDiagnosticoLike");
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
        return Collections.emptyList();
    }
    

}
