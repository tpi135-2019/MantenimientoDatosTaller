/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
public class PropietarioFacadeIT {

    PropietarioFacade cut = new PropietarioFacade();
    @Rule
    public EntityManagerProvider emp = EntityManagerProvider.getInstance("Test_db");
    EntityManager em;
    EntityTransaction tx;
    Propietario propietario1 = new Propietario(1, "random", "lazo");
    Propietario propietario2 = new Propietario(2, "daft", "punk");
    Propietario propietario3 = new Propietario(3, "roundabout", "yes");
    Propietario encontrado;
    int result;
    List encontrados;

    @Before
    public void before() {
        em = emp.getEm();
        tx = emp.getTransaction();
        Assert.assertNotNull(em);
        Whitebox.setInternalState(cut, "em", em);
    }

    @After
    public void after() {
        
        tx.begin();
        cut.remove(propietario1);
        cut.remove(propietario2);
        cut.remove(propietario3);
        tx.commit();
        result = 0;

    }

    @Test
    public void crearTest() {
        System.out.println("CrearIT");
        result = cut.count();
        Assert.assertEquals(0, result);

        tx.begin();
        cut.create(propietario1);
        tx.commit();
        em.detach(propietario1);
        result = cut.count();
        encontrado = cut.find(1);
        Assert.assertNotNull(encontrado);
        Assert.assertEquals(1, result);

    }

    @Test
    public void testDelete() {
        System.out.println("DeleteIT");
        tx.begin();
        cut.create(propietario1);
        cut.create(propietario2);
        tx.commit();

        tx.begin();
        cut.remove(propietario1);
        tx.commit();
        result = cut.count();
        encontrado = cut.find(1);
        Assert.assertNull(encontrado);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testEdit() {
        System.out.println("EditIT");

        tx.begin();
        cut.create(propietario1);
        tx.commit();
        em.detach(propietario1);

        encontrado = cut.find(1);
        em.detach(encontrado);

        propietario1.setNombre("El");
        propietario1.setApellido("Lechero");

        tx.begin();
        cut.edit(propietario1);
        tx.commit();
        Assert.assertNotEquals(encontrado.getNombre(), propietario1.getNombre());
    }

    @Test
    public void testFind() {
        System.out.println("testFindIT");
        tx.begin();
        cut.create(propietario1);
        tx.commit();
        em.detach(propietario1);
        encontrado = cut.find(1);
        Assert.assertEquals(propietario1.getNombre(), encontrado.getNombre());

    }

    @Test
    public void testFindAll() {
        System.out.println("testFindAllIT");
        result = cut.count();
        Assert.assertEquals(0, result);
        tx.begin();
        cut.create(propietario1);
        cut.create(propietario2);
        cut.create(propietario3);
        tx.commit();
        encontrados = cut.findAll();
        Assert.assertEquals(3, encontrados.size());

    }

    @Test
    public void testFindRange() {
        System.out.println("testFindRAngeIT");
        result = cut.count();
        Assert.assertEquals(0, result);
        tx.begin();
        cut.create(propietario1);
        cut.create(propietario2);
        cut.create(propietario3);
        tx.commit();
        encontrados = cut.findRange(1, 2);
        Assert.assertEquals(2, encontrados.size());
    }
    @Test
    public void testCount() {
        System.out.println("testCountIT");
        result = cut.count();
        Assert.assertEquals(0, result);
        tx.begin();
        cut.create(propietario1);
        cut.create(propietario2);
        tx.commit();
        result = cut.count();
        Assert.assertEquals(2, result);

    }

}
