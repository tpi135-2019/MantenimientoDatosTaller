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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class SubParteFacade extends AbstractFacade<SubParte> implements FacadeGenerico<SubParte> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubParteFacade() {
        super(SubParte.class);
    }

    public List<SubParte> subPartePorParte(int idParte, String subParte) {
        if (idParte >= 0) {
            try {
                Query query = em.createNamedQuery("SubParte.findByidParteNombreLike");
                query.setParameter("idParte", idParte);
                query.setParameter("nombre", subParte);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        return Collections.emptyList();
    }

}
