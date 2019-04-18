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
import junit.framework.Assert;
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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PasoFacadeTest extends SessionBeanTest<Paso> {
    @Mock
    EntityManager ema;
    @InjectMocks
    private PasoFacade cut;
    private Paso paso = new Paso(1);
    private List<Paso> registrosEsperados = new ArrayList<>();

    public PasoFacadeTest() {
        super(Paso.class);
        registrosEsperados.add(new Paso(1, "levantar carro"));
        registrosEsperados.add(new Paso(2, "quitar llanta"));

    }

      @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Paso getEntity() {
        return paso;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Paso> getLista() {
        return registrosEsperados;
    }
    
     @Test
    public void pasoReparacionTest(){
        System.out.println("pasoPorReparacion");
        int reparacion=1;
        Mockito.when(getEntityManager().createNamedQuery("Paso.Reparacion")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        List ls = cut.pasoReparacion(reparacion);
        Assert.assertEquals(registrosEsperados.size(), ls.size());
        ls=cut.pasoReparacion(-1);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());
        setEmNull();
        cut.pasoReparacion(reparacion);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        
    }

}
