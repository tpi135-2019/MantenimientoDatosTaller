/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.internal.util.reflection.Whitebox;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
public class PropietarioFacadeIT {

    PropietarioFacade cut = new PropietarioFacade();
    EntityManager em = Persistence.createEntityManagerFactory("Test_db").createEntityManager();

    @Before
    public void init() {
        Whitebox.setInternalState(cut, "em", em);
    }

   @Test
    public void crearTest() {
        System.out.println("crear");
        long result;
        Propietario p = new Propietario(1, "juan", "perez");
        Assert.assertNotNull(em);
        result = cut.count();
        Assert.assertEquals(0, result);
        
        //em.getTransaction().begin();
        cut.create(p);
        //em.getTransaction().commit();
        result = cut.count();
        Propietario test = (Propietario) em.createNamedQuery("SELECT p FROM Propietario p WHERE p.idPropietario = 1").getSingleResult();
        Assert.assertNotNull(test);
       // Assert.assertEquals(1, result);
       
    }

    //@Test
    public void testDelete() {
        Propietario propietario1 = new Propietario(2, "random", "lazo");
        Propietario propietario2 = new Propietario(3, "daft", "punk");
        em.getTransaction().begin();
        cut.create(propietario1);
        cut.create(propietario2);
        em.getTransaction().commit();
        em.getTransaction().begin();
        cut.remove(propietario1);
        em.getTransaction().commit();
        int result = cut.count();
        Assert.assertNotNull(cut.find(3));
        Assert.assertEquals(1, result);
       
    }

    @Test
    public void testEdit() {
        Propietario propietario = new Propietario(4, "random", "lazo");
        Propietario propietario2;
        em.getTransaction().begin();
        cut.create(propietario);
        em.getTransaction().commit();
        propietario2 = cut.find(4);
        em.getTransaction().begin();
        Propietario p1 = new Propietario(4, "juan", "alv");
        cut.edit(p1);
        em.getTransaction().commit();
        Assert.assertSame(p1, propietario2);
        Assert.assertTrue(propietario2.equals(cut.find(4)));
    }

}
