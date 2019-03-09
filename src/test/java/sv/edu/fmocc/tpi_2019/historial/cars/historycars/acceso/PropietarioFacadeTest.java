/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
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
    Propietario propietario;
    javax.persistence.criteria.CriteriaQuery cq;
    Query query;
    TypedQuery<Propietario> tq;
    CriteriaBuilder qb;
    PropietarioFacade cut = new PropietarioFacade();

    public PropietarioFacadeTest() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        propietario = crearRegistro(1, "juan", "penya", "calle ayuwoky", "7423-2312");
        qb = Mockito.mock(CriteriaBuilder.class);
        cq = Mockito.mock(javax.persistence.criteria.CriteriaQuery.class);
        ema = Mockito.mock(EntityManager.class);
        tq = Mockito.mock(TypedQuery.class);
        Mockito.when(ema.getCriteriaBuilder()).thenReturn(qb);
        query = Mockito.mock(Query.class);
        cut.em = ema;

    }

    // ---->  
    @Test
    public void testFindAll() {
        System.out.println("findAll");

        Mockito.when(qb.createQuery()).thenReturn(cq);
        cq.select(cq.from(Propietario.class));
        Mockito.when(ema.createQuery(cq)).thenReturn(tq);
        Mockito.when(tq.getResultList()).thenReturn(listarRegistros());
        List lista = cut.findAll();
        assertEquals(lista.size(), listarRegistros().size());
        //fail("¿fallo,donde?");
    }

    /**
     * Test of count method, of class PropietarioFacade.
     */
    @Test
    public void testFindId() {
        System.out.println("FindId");
        Propietario espero = new Propietario(1);
        Mockito.when(ema.find(Propietario.class, 1)).thenReturn(espero);
        Propietario obtenido = cut.find(1);
        Assert.assertEquals(espero, obtenido);
        //fail("¿fallo,donde?");
    }

    @Test
    public void testCreate() {
        System.out.println("create");

        doNothing().when(ema).persist(propietario);
        cut.create(propietario);
        verify(ema).persist(propietario);
        // fail("¿fallo?, ¿donde? :v");
    }

    @Test
    public void testEdit() {
        System.out.println("edit");
        cut.edit(propietario);
        verify(ema).merge(propietario);   // verifica si se esta llamando el metodo merge dentro de edit

        //   fail("¿fallo?, ¿donde? :v");
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        doNothing().when(ema).remove(propietario);
        cut.remove(propietario);
        verify(ema).remove(ema.merge(propietario));
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

    public List<Propietario> listarRegistros() {
        List<Propietario> ls = new ArrayList<>();
        ls.add(crearRegistro(1, "juan", "penya", "calle ayuwoky", "7423-2312"));
        ls.add(crearRegistro(2, "juan", "penya", "calle ayuwoky", "7423-2312"));

        return ls;
    }

}
