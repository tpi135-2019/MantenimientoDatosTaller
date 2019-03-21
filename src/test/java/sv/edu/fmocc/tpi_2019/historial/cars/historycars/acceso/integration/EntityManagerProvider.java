/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @author kevin
 */
public class EntityManagerProvider implements TestRule{

    
    private final EntityTransaction transaction;
    private final EntityManager em;

    private EntityManagerProvider(String puName) {
        this.em = Persistence.createEntityManagerFactory(puName).createEntityManager();
        this.transaction = this.em.getTransaction();
    }

    public static EntityManagerProvider getInstance(String puName) {
        return new EntityManagerProvider(puName);
    }
    
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                em.clear();
                em.close();
            }
        };
    }

    public EntityTransaction getTransaction() {
        return transaction;
    }

    public EntityManager getEm() {
        return em;
    }
    
    
    
}
