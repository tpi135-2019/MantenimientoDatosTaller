/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Propietario;

/**
 *
 * @author kevin
 */
public class PropietarioFacadeTest {

    EntityManager ema;
    Propietario p;
    javax.persistence.criteria.CriteriaQuery cq;
    Query q;

    public PropietarioFacadeTest() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        p = crearRegistro(1, "juan", "penya", "calle ayuwoky", "7423-2312");
        cq= Mockito.mock(CriteriaQuery.class);
        ema = Mockito.mock(EntityManager.class);
        q= Mockito.mock(Query.class);
        
        
     //   Mockito.when(ema.createQuery(cq)).thenReturn(q); /// revisa crack
       // Mockito.when(q.getSingleResult()).thenReturn(Matchers.anyInt());
    // falta crear una lista   Mockito.when(q.getResultList()).thenReturn(lista);
    }

 

    public void testCount(){
        
    }
    
    
    /**
     * Test of count method, of class PropietarioFacade.
     */
    @Test
    public void testFindId() {
        System.out.println("FindId");
        Propietario espero = new Propietario(1);
        Mockito.when(ema.find(Propietario.class, 1)).thenReturn(espero);
        PropietarioFacade cut = new PropietarioFacade();
        cut.em = ema;
        Propietario obtenido = cut.find(1);
        Assert.assertEquals(espero, obtenido);
        //fail("¿fallo,donde?");
    }

    @Test
    public void testCreate() {
        System.out.println("create");

        PropietarioFacade cut = new PropietarioFacade();
        cut.em = ema;
        doNothing().when(ema).persist(p);
        cut.create(p);
        verify(ema).persist(p);
        // fail("¿fallo?, ¿donde? :v");
    }

    @Test
    public void testEdit() {
        System.out.println("edit");
        PropietarioFacade cut = new PropietarioFacade();
        cut.em = ema; 
        cut.edit(p);
        verify(ema).merge(p);   // verifica si se esta llamando el metodo merge dentro de edit

        //   fail("¿fallo?, ¿donde? :v");
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        PropietarioFacade cut = new PropietarioFacade();
        cut.em = ema;
        doNothing().when(ema).remove(p);
        cut.remove(p);
        verify(ema).remove(ema.merge(p));
       // fail("¿fallo?, ¿donde? :v");
    }

    public Propietario crearRegistro(int id, String nombre, String apellido, String direccion, String cel) {
        Propietario prop = new Propietario();
        prop.setIdPropietario(id);
        prop.setNombre(nombre);
        prop.setApellido(apellido);
        prop.setDireccion(direccion);
        prop.setTelefono(cel);
        return prop;
    }

}
