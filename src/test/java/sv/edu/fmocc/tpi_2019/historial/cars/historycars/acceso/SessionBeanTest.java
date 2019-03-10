/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

/**
 *
 * @author kevin
 * @param <T>
 */
public abstract class SessionBeanTest<T> {

    EntityManager em;
    Class<T> entityClass;
    CriteriaQuery cQueryTest;
    Query query;
    TypedQuery<T> tq;
    CriteriaBuilder cb;
    Root<T> rt;
    FacadeGenerico cutGeneric;
    T entity;

    protected abstract FacadeGenerico getSessionBean();

    protected abstract T getEntity();

    public SessionBeanTest(Class<T> entityClass) {
        this.entityClass = entityClass;

    }

    @Before
    public void setUp() {
        rt = Mockito.mock(Root.class);
        query = Mockito.mock(Query.class);
        cb = Mockito.mock(CriteriaBuilder.class);
        cQueryTest = Mockito.mock(CriteriaQuery.class);
        em = Mockito.mock(EntityManager.class);
        tq = Mockito.mock(TypedQuery.class);
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
        cutGeneric = getSessionBean();
        entity = getEntity();
    }


    public void testFindAllGeneric(List<T> registrosEsperados) {
        System.out.println("findAll");
        mockLista(registrosEsperados);
        List lista = cutGeneric.findAll();
        Assert.assertEquals(registrosEsperados.size(), lista.size());
    }

    public void testCountGeneric(long esperado) {
        System.out.println("count");
        Mockito.when(cutGeneric.obtenerCriteriaQueryComun(em)).thenReturn(cQueryTest);
        Mockito.when(cQueryTest.from(entityClass)).thenReturn(rt);
        Mockito.when(em.createQuery(cQueryTest)).thenReturn(tq);
        Mockito.when(tq.getSingleResult()).thenReturn((T)(Long)esperado);
        int count = cutGeneric.count();
        Assert.assertEquals(esperado, count);

    }

    public void testFingRangeGeneric(List<T> registrosEsperados) {
        System.out.println("findRange");
        mockLista(registrosEsperados);
        List lista = cutGeneric.findRange(1, 2);
        Assert.assertEquals(registrosEsperados.size(), lista.size());

    }

    public void testFindIdGeneric() {
        System.out.println("FindId");
        Mockito.when(em.find(entityClass, 1)).thenReturn(entity);
        Object obtenido = cutGeneric.find(1);
        Assert.assertEquals(entity, obtenido);
        //fail("¿fallo,donde?");
    }

    public void testCreateGeneric() {
        System.out.println("create");
        Mockito.doNothing().when(em).persist(entity);
        cutGeneric.create(entity);
        Mockito.verify(em).persist(entity);
    }

    public void testEditGeneric() {
        System.out.println("edit");
        cutGeneric.edit(entity);
        Mockito.verify(em).merge(entity);

    }

    public void testRemoveGeneric() {
        System.out.println("remove");
        Mockito.doNothing().when(em).remove(entity);
        cutGeneric.remove(entity);
        Mockito.verify(em).remove(em.merge(entity));
    }
    
    /**
     * Mockea los metodos necesarios para obtener la lista de findAll y findRange
     * @param registrosEsperados 
     */
     public void mockLista(List<T> registrosEsperados) {
        Mockito.when(cutGeneric.obtenerCriteriaQueryComun(em)).thenReturn(cQueryTest);
        Mockito.when(em.createQuery(cQueryTest)).thenReturn(tq);
        Mockito.when(tq.getResultList()).thenReturn(registrosEsperados);
    }

}
