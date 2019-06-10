/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.util.Loggable;

/**
 *
 * @author kevin
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    @Inject
    protected Logger logger;
    private String msjEmNulo = "Entity Manager o Entity nulo";

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        EntityManager em = getEntityManager();
        if (em != null && entity != null) {
            try {
                em.persist(entity);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        } else {
            logger.log(Level.WARNING, msjEmNulo);
        }

    }

    public void edit(T entity) {
        EntityManager em = getEntityManager();
        if (em != null && entity != null) {
            try {
                em.merge(entity);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }

        } else {
            logger.log(Level.WARNING, msjEmNulo);
        }

    }

    public void remove(T entity) {
        EntityManager em = getEntityManager();
        if (em != null && entity != null) {
            try {
                em.remove(em.merge(entity));
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        } else {
            logger.log(Level.WARNING, msjEmNulo);
        }
    }
    
    @Loggable
    public T find(Object id) {
        EntityManager em = getEntityManager();
        if (em != null && id != null) {
            try {
                return em.find(entityClass, id);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                return null;
            }
        } else {
            logger.log(Level.WARNING, msjEmNulo);
            return null;
        }
    }

    public List<T> findAll() {
        EntityManager em = getEntityManager();
        if (em != null) {
            try {
                CriteriaQuery cq = obtenerCriteriaQueryComun(em);
                cq.select(cq.from(entityClass));
                return em.createQuery(cq).getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
                return Collections.emptyList();
            }

        } else {
            logger.log(Level.WARNING, msjEmNulo);
            return Collections.emptyList();
        }

    }

    public List<T> findRange(int inicio, int size) {
        EntityManager em = getEntityManager();
        if (em != null) {
            try {
                CriteriaQuery cq = obtenerCriteriaQueryComun(em);
                cq.select(cq.from(entityClass));
                Query q = em.createQuery(cq);
                q.setMaxResults(size);
                q.setFirstResult(inicio);
                return q.getResultList();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
                return Collections.emptyList();
            }

        } else {
            logger.log(Level.WARNING, msjEmNulo);
            return Collections.emptyList();
        }
    }

    public int count() {
        EntityManager em = getEntityManager();
        if (em != null) {
            CriteriaQuery cq = obtenerCriteriaQueryComun(em);
            Root<T> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } else {
            return 0;
        }
    }

    public CriteriaQuery obtenerCriteriaQueryComun(EntityManager em) {
        return em.getCriteriaBuilder().createQuery();
    }

    public void detach(T entity) {
        EntityManager em = getEntityManager();
        em.detach(entity);
    }

}
