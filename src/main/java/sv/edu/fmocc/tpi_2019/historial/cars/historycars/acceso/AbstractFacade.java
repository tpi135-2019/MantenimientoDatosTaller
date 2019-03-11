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
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
                throw e;
            }
        } else {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Entity Manager o Entity nulo");
            throw new NullPointerException("Entity Manager o Entity nulo");
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
            throw new NullPointerException("algo es nulo");
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
            throw new NullPointerException("EntityManager o entidad nula");
        }
    }

    public T find(Object id) {
        EntityManager em = getEntityManager();
        if (em != null && id != null) {
            try {
                return em.find(entityClass, id);
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                throw e;
            }
        } else {
            throw new NullPointerException("EntityManager o id nulo");
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

    public List<T> findRange(int desde, int hasta) {
        EntityManager em = getEntityManager();
        if (em != null) {
            CriteriaQuery cq = obtenerCriteriaQueryComun(em);
            cq.select(cq.from(entityClass));
            Query q = em.createQuery(cq);
            q.setMaxResults(hasta);
            q.setFirstResult(desde);
            return q.getResultList();
        } else {
            throw new NullPointerException("entityManager Nulo");
        }
    }

    public int count() {
        EntityManager em = getEntityManager();
        if (em == null) {
            throw new NullPointerException("entityManager Nulo");
        } else {
            CriteriaQuery cq = obtenerCriteriaQueryComun(em);
            Root<T> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
    }

    public CriteriaQuery obtenerCriteriaQueryComun(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        return cq;
    }

}
