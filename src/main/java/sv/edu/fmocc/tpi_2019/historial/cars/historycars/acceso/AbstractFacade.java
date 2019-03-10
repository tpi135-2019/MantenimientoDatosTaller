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
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author kevin
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

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
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "EntityManager o Entity nulo");
        }

    }

    public void edit(T entity) {
        EntityManager em = getEntityManager();
        if (em != null && entity != null) {
            try {
                em.merge(entity);
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }

        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "EntityManager o Entity nulo");
        }

    }

    public void remove(T entity) {
        EntityManager em = getEntityManager();
        if (em != null && entity != null) {
            try {
                em.remove(getEntityManager().merge(entity));
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "EntityManager o Entity nulo");
        }
    }

    public T find(Object id) {
        EntityManager em = getEntityManager();
        if (em != null && id != null) {
            try {
                return em.find(entityClass, id);
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            return em.find(entityClass, id);
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "EntityManager o ID");
            return null;
        }
    }

    public List<T> findAll() {
        EntityManager em = getEntityManager();
        if (em != null) {
            CriteriaQuery cq = obtenerCriteriaQueryComun(em);
            cq.select(cq.from(entityClass));
            return em.createQuery(cq).getResultList();
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = obtenerCriteriaQueryComun(getEntityManager());
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = obtenerCriteriaQueryComun(getEntityManager());
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public CriteriaQuery obtenerCriteriaQueryComun(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        return cq;
    }

}
