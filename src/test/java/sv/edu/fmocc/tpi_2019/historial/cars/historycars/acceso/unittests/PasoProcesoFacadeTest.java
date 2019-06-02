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
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PasoProcesoFacadeTest extends SessionBeanTest<PasoProceso> {

    public PasoProcesoFacadeTest() {
        super(PasoProceso.class);
        registrosEsperados.add(new PasoProceso(1, 1));
        registrosEsperados.add(new PasoProceso(2, 1));

    }

    @Mock
    EntityManager ema;
    @InjectMocks
    private PasoProcesoFacade cut;
    private PasoProceso pasoProceso = new PasoProceso(1, 1);
    private List<PasoProceso> registrosEsperados = new ArrayList<>();

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected PasoProceso getEntity() {
        return pasoProceso;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<PasoProceso> getLista() {
        return registrosEsperados;
    }

    @Test
    public void PasosPorProcesoTest() {
        System.out.println("pasosPorProceso");
        int proceso = 1;
        Mockito.when(ema.createNamedQuery("PasoProceso.findByProceso")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        List ls = cut.pasosPorProceso(proceso, "");
        Assert.assertEquals(registrosEsperados.size(), ls.size());

        ls = cut.pasosPorProceso(-1, "");
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());

        setEmNull();
        cut.pasosPorProceso(proceso, "");
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void PasosPorProcesoAlgoTest() {
        System.out.println("pasosPorProceso");
        int proceso = 1;
        Mockito.when(ema.createNamedQuery("PasoProceso.findPasosByProceso")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        
        List ls = cut.pasoProcesoPorProceso(proceso, "");
        Assert.assertEquals(registrosEsperados.size(), ls.size());

        ls = cut.pasoProcesoPorProceso(-1, "");
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());

        setEmNull();
        cut.pasoProcesoPorProceso(proceso, "");
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

}
