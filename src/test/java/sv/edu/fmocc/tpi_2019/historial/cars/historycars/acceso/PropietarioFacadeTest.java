/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Propietario;

/**
 *
 * @author kevin
 */
public class PropietarioFacadeTest {

    EntityManager em;
    Propietario propietario;
    javax.persistence.criteria.CriteriaQuery cQueryTest;
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
        cQueryTest = Mockito.mock(javax.persistence.criteria.CriteriaQuery.class);
        em = Mockito.mock(EntityManager.class);
        tq = Mockito.mock(TypedQuery.class);
        Mockito.when(em.getCriteriaBuilder()).thenReturn(qb);
        query = Mockito.mock(Query.class);
        cut.em = em;

    }

    // ---->  
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        Mockito.when(cut.obtenerCriteriaQueryComun(em)).thenReturn(cQueryTest);
        Mockito.when(em.createQuery(cQueryTest)).thenReturn(tq);
        Mockito.when(tq.getResultList()).thenReturn(listarRegistros());
        List lista = cut.findAll();
        Assert.assertEquals(lista.size(), listarRegistros().size());
        //fail("¿fallo,donde?");
    }

    /**
     * Test of count method, of class PropietarioFacade.
     */
    @Test
    public void testFindId() {
        System.out.println("FindId");
        Propietario espero = new Propietario(1);
        Mockito.when(em.find(Propietario.class, 1)).thenReturn(espero);
        Propietario obtenido = cut.find(1);
        Assert.assertEquals(espero, obtenido);
        //fail("¿fallo,donde?");
    }

    @Test
    public void testCreate() {
        System.out.println("create");

        Mockito.doNothing().when(em).persist(propietario);
        cut.create(propietario);
        Mockito.verify(em).persist(propietario);
        // fail("¿fallo?, ¿donde? :v");
    }

    @Test
    public void testEdit() {
        System.out.println("edit");
        cut.edit(propietario);
        Mockito.verify(em).merge(propietario);   // verifica si se esta llamando el metodo merge dentro de edit

        //   fail("¿fallo?, ¿donde? :v");
    }

    @Test
    public void testRemove() {
        System.out.println("remove");
        Mockito.doNothing().when(em).remove(propietario);
        cut.remove(propietario);
        Mockito.verify(em).remove(em.merge(propietario));
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
