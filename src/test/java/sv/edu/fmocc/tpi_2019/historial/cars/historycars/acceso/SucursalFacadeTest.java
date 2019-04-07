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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class SucursalFacadeTest extends SessionBeanTest<Sucursal> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private SucursalFacade cut;
    private Sucursal sucursal = new Sucursal(1);
    private List<Sucursal> registrosEsperados = new ArrayList<>();

    public SucursalFacadeTest() {
        super(Sucursal.class);
        registrosEsperados.add(new Sucursal(1, "taller chuchito"));
        registrosEsperados.add(new Sucursal(2, "taller chepito"));
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Sucursal> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Sucursal getEntity() {
        return sucursal;
    }

    @Test
    public void tallerReparacionTest() {
        System.out.println("LugarDeReparacion");
        Mockito.when(getEntityManager().createNamedQuery("Sucursal.Reparacion")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        int reparacion = 1;
        List sucu = cut.lugarReparacion(reparacion);
        Assert.assertEquals(registrosEsperados.size(), sucu.size());
        sucu = cut.lugarReparacion(-1);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), sucu.size());
        setEmNull();
        cut.lugarReparacion(reparacion);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }
}
