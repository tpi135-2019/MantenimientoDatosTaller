/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.Test;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Propietario;

/**
 *
 * @author kevin
 */
public class PropietarioFacadeIT {
    
    @Test
    public void crearTest(){
        System.out.println("crear");
        long result;
        EntityManager em = Persistence.createEntityManagerFactory("Test_db").createEntityManager();
        Assert.assertNotNull(em);
        result = (long) em.createQuery("select COUNT(t) from Propietario t").getSingleResult();
        Assert.assertEquals(0, result);
        em.getTransaction().begin();
        em.persist(new Propietario(1));
        em.getTransaction().commit();
         result = (long) em.createQuery("select COUNT(t) from Propietario t").getSingleResult();
        Assert.assertEquals(1, result);
    }
    
}
