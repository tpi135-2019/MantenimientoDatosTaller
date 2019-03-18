/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author kevin
 * @param <T>
 */
public abstract class SessionBeanTest<T> {

    @Mock
    EntityManager em;
    @Mock
    CriteriaQuery cQueryTest;
    @Mock
    Query query;
    @Mock
    TypedQuery<T> tq;
    @Mock
    CriteriaBuilder cb;
    @Mock
    Root<T> rt;
    FacadeGenerico cutGeneric;
    Class<T> entityClass;
    T entity;

    protected abstract FacadeGenerico getSessionBean();

    protected abstract T getEntity();

    public SessionBeanTest(Class<T> entityClass) {
        this.entityClass = entityClass;

    }

    @Before
    public void setUp() {
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
        cutGeneric = getSessionBean();
        entity = getEntity();

    }
//***** TEST FINDALL

    public void testFindAllGeneric(List<T> registrosEsperados) {
        System.out.println("findAll");
        mockLista(registrosEsperados);
        List lista = cutGeneric.findAll();
        Assert.assertEquals(registrosEsperados.size(), lista.size());
    }

    public void testFinAllEmptyGeneric() {
        System.out.println("testFindAllEmpty");
        Mockito.when(cutGeneric.obtenerCriteriaQueryComun(em)).thenReturn(cQueryTest);
        Mockito.when(em.createQuery(cQueryTest)).thenReturn(tq);
        Mockito.when(tq.getResultList()).thenReturn(Collections.EMPTY_LIST);
        List lista = cutGeneric.findAll();
        Assert.assertEquals(lista.size(), Collections.EMPTY_LIST.size());
    }
    //*******TEST COUNT

    public void testCountGeneric(long esperado) {
        System.out.println("count");
        Mockito.when(cutGeneric.obtenerCriteriaQueryComun(em)).thenReturn(cQueryTest);
        Mockito.when(cQueryTest.from(entityClass)).thenReturn(rt);
        Mockito.when(em.createQuery(cQueryTest)).thenReturn(tq);
        Mockito.when(tq.getSingleResult()).thenReturn((T) (Long) esperado);
        int count = cutGeneric.count();
        Assert.assertEquals(esperado, count);
    }

    public void testCountEmNullGeneric() {
        System.out.println("CountException");
        Mockito.when(cutGeneric.count())
                .thenThrow(NullPointerException.class);
    }

    //*********TEST FIND_RANGE
    public void testFindRangeEmNullGeneric(int desde, int hasta) {
        System.out.println("findRangeException");
        Mockito.when(cutGeneric.findRange(desde, hasta))
                .thenThrow(NullPointerException.class);
    }

    public void testFingRangeGeneric(List<T> registrosEsperados) {
        System.out.println("findRange");
        mockLista(registrosEsperados);
        List lista = cutGeneric.findRange(1, 2);
        Assert.assertEquals(registrosEsperados.size(), lista.size());
    }

    //******TEST EDIT
    public void testEditEmNullGeneric() {
        System.out.println("editException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).merge(entity);
        cutGeneric.edit(entity);
    }

    public void testEditGeneric() {
        System.out.println("edit");
        cutGeneric.edit(entity);
        Mockito.verify(em).merge(entity);
    }

    public void testEditExceptionGeneric() {
        System.out.println("editException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).merge(entity);
        cutGeneric.edit(entity);
    }

    //******+TEST REMOVE
    public void testRemoveEmNullGeneric() {
        System.out.println("RemoveException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).remove(em.merge(entity));
        cutGeneric.remove(entity);
    }

    public void testRemoveGeneric() {
        System.out.println("remove");
        Mockito.doNothing().when(em).remove(entity);
        cutGeneric.remove(entity);
        Mockito.verify(em).remove(em.merge(entity));
    }

    public void testRemoveExceptionGeneric() {
        System.out.println("removeException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).remove(em.merge(entity));
        cutGeneric.remove(entity);
    }

    //*******TESTS FIND GENERICO
    public void testFindIdGeneric() {
        System.out.println("FindId");
        Mockito.when(em.find(entityClass, 1)).thenReturn(entity);
        Object obtenido = cutGeneric.find(1);
        Assert.assertEquals(entity, obtenido);
    }

    public void testFindIdExceptionGeneric(Object id) {
        System.out.println("FindIdException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).find(entityClass, id);
        cutGeneric.find(id);
    }

    public void testFindIdEmNuloGeneric() throws NullPointerException {
        System.out.println("createException");
        cutGeneric.find(null);
    }

    //******** TESTS CREATE GENERICO
    public void testCreateGeneric() {
        System.out.println("create");
        Mockito.doNothing().when(em).persist(entity);
        cutGeneric.create(entity);
        Mockito.verify(em).persist(entity);
    }

    public void testCreateEmNuloGeneric() throws NullPointerException {
        System.out.println("createException");
        cutGeneric.create(entity);
    }

    public void testCreateExceptionGeneric() {
        System.out.println("createException");
        Mockito.doThrow(new EntityExistsException()).when(em).persist(entity);
        cutGeneric.create(entity);
    }

    /**
     * Mockea los metodos necesarios para obtener la lista de findAll y
     * findRange
     *
     * @param registrosEsperados
     */
    public void mockLista(List<T> registrosEsperados) {
        Mockito.when(cutGeneric.obtenerCriteriaQueryComun(em)).thenReturn(cQueryTest);
        Mockito.when(em.createQuery(cQueryTest)).thenReturn(tq);
        Mockito.when(tq.getResultList()).thenReturn(registrosEsperados);
    }

}
