/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonalFacadeTest extends SessionBeanTest<Personal> {

    public PersonalFacadeTest() {
        super(Personal.class);
        registrosEsperados.add(new Personal(1, "milo", "reyes"));
        registrosEsperados.add(new Personal(2, "chele", "papaya"));
    }

    @Mock
    EntityManager ema;
    @InjectMocks
    private PersonalFacade cut;
    private Personal personal = new Personal(1);
    private List<Personal> registrosEsperados = new ArrayList<>();

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Personal getEntity() {
        return personal;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Personal> getLista() {
        return registrosEsperados;
    }

    @Test
    public void personalReparacionTest() {
        System.out.println("personalPorReparacion");
        int reparacion = 1;
        Mockito.when(getEntityManager().createNamedQuery("Personal.Reparacion")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        List lista = cut.personalPorReparacion(reparacion);
        Assert.assertEquals(registrosEsperados.size(), lista.size());
        lista = cut.personalPorReparacion(-1);
        junit.framework.Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
        setEmNull();
        cut.personalPorReparacion(reparacion);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    
      @Test
    public void personalPorProcesoTest() {
        System.out.println("personalPorProceso");
        int pro = 1;
        Mockito.when(getEntityManager().createNamedQuery("Personal.Proceso")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        List lista = cut.personalPorProceso(pro);
        Assert.assertEquals(registrosEsperados.size(), lista.size());
        lista = cut.personalPorProceso(-1);
        junit.framework.Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
        setEmNull();
        cut.personalPorProceso(pro);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }
}
