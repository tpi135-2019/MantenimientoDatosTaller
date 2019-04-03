/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PiezaFacadeTest extends SessionBeanTest<Pieza> {
    
    @Mock
    EntityManager ema;
    @InjectMocks
    private PiezaFacade cut;
    private Pieza pieza = new Pieza(1, "piston");
    private List<Pieza> registrosEsperados = new ArrayList<>();

    public PiezaFacadeTest() {
        super(Pieza.class);
        registrosEsperados.add(new Pieza(1, "biela"));
        registrosEsperados.add(new Pieza(2, "cadena de tiempo"));

    }

       
    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Pieza getEntity() {
        return pieza;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Pieza> getLista() {
        return registrosEsperados;
    }
    
       @Test
    public void piezaReparacionTest() {
        System.out.println("piezaPorReparacion");
        int reparacion=1;
        Mockito.when(getEntityManager().createNamedQuery("Pieza.Reparacion")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        List ls = cut.piezasReparacion(reparacion);
        Assert.assertEquals(registrosEsperados.size(), ls.size());
        ls = cut.piezasReparacion(-1);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());
        setEmNull();
        cut.piezasReparacion(reparacion);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }


}
