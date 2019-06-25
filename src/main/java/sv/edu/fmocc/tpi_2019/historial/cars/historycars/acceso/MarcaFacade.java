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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class MarcaFacade extends AbstractFacade<Marca> implements FacadeGenerico<Marca> {

    @PersistenceContext(unitName = "PU_talleres")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }

    public List<Modelo> findModeloByMarcaLike(Integer idMarca, String modelo) {
        if (idMarca >= 0) {
            try {
                Query query = em.createNamedQuery("Modelo.ModeloByMarca");
                query.setParameter("idMarca", idMarca);
                query.setParameter("modelo", modelo);
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        return Collections.emptyList();
    }

}
