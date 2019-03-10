/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;



/**
 *
 * @author kevin
 */
public interface FacadeGenerico<T> {
    
    
    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Object id);

    public List<T> findAll();

    public List<T> findRange(int[] range);
    
    public int count();
    
    public CriteriaQuery obtenerCriteriaQueryComun(EntityManager em);
    
}
