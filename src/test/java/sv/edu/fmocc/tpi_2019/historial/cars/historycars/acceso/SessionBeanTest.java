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
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

/**
 *
 * @author kevin
 * @param <T>
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class SessionBeanTest<T> {

    @Spy
    Logger logger = Logger.getGlobal();
    @Mock
    CriteriaQuery cQueryMock;
    @Mock
    Query queryMock;
    @Mock
    TypedQuery<T> typedQueyMock;
    @Mock
    CriteriaBuilder criteriaBuilderMock;
    @Mock
    Root<T> rt;
    FacadeGenerico cutGeneric = getSessionBean();
    EntityManager em;
    Class<T> entityClass;
    T entity;
    List<T> registrosEsperados;

    protected abstract FacadeGenerico getSessionBean();

    protected abstract EntityManager getEntityManager();

    protected abstract List<T> getLista();

    protected abstract T getEntity();

    public SessionBeanTest(Class<T> entityClass) {
        this.entityClass = entityClass;

    }
//     @Rule
//    public EmNulo emnull = new EmNulo(cutGeneric);

    @Before
    public void setUp() {
        em = getEntityManager();
        Mockito.when(em.getCriteriaBuilder()).thenReturn(criteriaBuilderMock);
        cutGeneric = getSessionBean();
        entity = getEntity();
        cutGeneric.setLogger(logger);
        registrosEsperados = getLista();
    }

//***** TEST FINDALL
    @Test
    //@Ignore
    public void testFindAllGeneric() {
        System.out.println("findAll");
        mockLista(registrosEsperados);
        List lista = cutGeneric.findAll();
        Assert.assertEquals(registrosEsperados.size(), lista.size());
    }

    @Test
    // @Ignore
    public void testFinAllEmptyGeneric() {
        System.out.println("testFindAllEmpty");
        setEmNull();
        List lista = cutGeneric.findAll();
        Assert.assertEquals(lista.size(), Collections.EMPTY_LIST.size());
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

    //*******TEST COUNT
    @Test
    //@Ignore
    public void testCountGeneric() {
        System.out.println("count");
        long esperado = 10;
        Mockito.when(cutGeneric.obtenerCriteriaQueryComun(em)).thenReturn(cQueryMock);
        Mockito.when(cQueryMock.from(entityClass)).thenReturn(rt);
        Mockito.when(em.createQuery(cQueryMock)).thenReturn(typedQueyMock);
        Mockito.when(typedQueyMock.getSingleResult()).thenReturn((T) (Long) esperado);
        int count = cutGeneric.count();
        Assert.assertEquals(esperado, count);
    }

    @Test
    public void testCountEmNullGeneric() {
        System.out.println("CountException");
        setEmNull();
        int result = cutGeneric.count();
        Assert.assertEquals(0, result);

    }

    //*********TEST FIND_RANGE
    @Test
    public void testFindRangeEmNullGeneric() {
        System.out.println("findRangeException");
        setEmNull();
        List lista = cutGeneric.findRange(0, 4);
        Assert.assertEquals(lista.size(), Collections.EMPTY_LIST.size());
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    //@Ignore
    public void testFingRangeGeneric() {
        System.out.println("findRange");
        mockLista(registrosEsperados);
        List lista = cutGeneric.findRange(1, 2);
        Assert.assertEquals(registrosEsperados.size(), lista.size());
    }

    //******TEST EDIT
    @Test
    //@Ignore
    public void testEditEmNullGeneric() {
        System.out.println("editException");
        setEmNull();
        cutGeneric.edit(entity);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

    @Test
    //@Ignore
    public void testEditGeneric() {
        System.out.println("edit");
        cutGeneric.edit(entity);
        Mockito.verify(em).merge(entity);
    }

    @Test
    // @Ignore
    public void testEditExceptionGeneric() {
        System.out.println("editException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).merge(entity);
        cutGeneric.edit(entity);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));
    }

    //******+TEST REMOVE
    @Test
    public void testRemoveEmNullGeneric() {
        System.out.println("RemoveException");
        setEmNull();
        cutGeneric.remove(entity);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

    @Test
    // @Ignore
    public void testRemoveGeneric() {
        System.out.println("remove");
        Mockito.doNothing().when(em).remove(entity);
        cutGeneric.remove(entity);
        Mockito.verify(em).remove(em.merge(entity));
    }

    @Test
    // @Ignore
    public void testRemoveExceptionGeneric() {
        System.out.println("removeException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).remove(em.merge(entity));
        cutGeneric.remove(entity);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));
    }

    //*******TESTS FIND GENERICO
    @Test
    //  @Ignore
    public void testFindIdGeneric() {
        System.out.println("FindId");
        Mockito.when(em.find(entityClass, 1)).thenReturn(entity);
        Object obtenido = cutGeneric.find(1);
        Assert.assertEquals(entity, obtenido);
    }

    @Test
    //  @Ignore
    public void testFindIdExceptionGeneric() {
        System.out.println("FindIdException");
        Mockito.doThrow(new IllegalArgumentException()).when(em).find(entityClass, 1);
        Object found = cutGeneric.find(1);
        Assert.assertNull(found);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));
    }

    @Test
    public void testFindIdEmNuloGeneric() {
        System.out.println("createException");
        setEmNull();
        Object found = cutGeneric.find(null);
        Assert.assertNull(found);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

    //******** TESTS CREATE GENERICO
    @Test
    // @Ignore
    public void testCreateGeneric() {
        System.out.println("create");
        Mockito.doNothing().when(em).persist(entity);
        cutGeneric.create(entity);
        Mockito.verify(em).persist(entity);
    }

    @Test
    public void testCreateEmNuloGeneric() {
        System.out.println("createException");
        setEmNull();
        cutGeneric.create(entity);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

    @Test
    // @Ignore
    public void testCreateExceptionGeneric() {
        System.out.println("createException");
        Mockito.doThrow(new EntityExistsException()).when(em).persist(entity);
        cutGeneric.create(entity);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));
    }

    /**
     * Mockea los metodos necesarios para obtener la lista de findAll y
     * findRange
     *
     * @param registrosEsperados
     */
    private void mockLista(List<T> registrosEsperados) {
        Mockito.when(cutGeneric.obtenerCriteriaQueryComun(em)).thenReturn(cQueryMock);
        Mockito.when(em.createQuery(cQueryMock)).thenReturn(typedQueyMock);
        Mockito.when(typedQueyMock.getResultList()).thenReturn(registrosEsperados);
    }

    protected void setEmNull() {
        this.em = null;
        Whitebox.setInternalState(cutGeneric, "em", em);
    }

}
