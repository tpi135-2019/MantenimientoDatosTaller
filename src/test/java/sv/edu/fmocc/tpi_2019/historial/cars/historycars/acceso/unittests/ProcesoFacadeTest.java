/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcesoFacadeTest extends SessionBeanTest<Proceso> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private ProcesoFacade cut;
    private Proceso proceso = new Proceso(1);
    private List<Proceso> registrosEsperados = new ArrayList<>();
    private List<Sucursal> registrosSucursal = new ArrayList<>();

    public ProcesoFacadeTest() {
        super(Proceso.class);
        registrosEsperados.add(new Proceso(1, "cambio de aceite"));
        registrosEsperados.add(new Proceso(2, "cambio de frenos"));
        registrosSucursal.add(new Sucursal(1, "taller chuchito"));
        registrosSucursal.add(new Sucursal(2, "taller chepito"));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Proceso getEntity() {
        return proceso;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Proceso> getLista() {
        return registrosEsperados;
    }
    
    @Test
    public void sucursalPorProcesoTest() {
        System.out.println("sucursal por procesos  test");
        //SET UP
        Mockito.when(ema.createNamedQuery("Proceso.Sucursal")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosSucursal);
        Integer id = 1;

        //WHEN TODO GOOD
        List<Sucursal> sucursales = cut.sucursalPorProceso(id);
        Assert.assertEquals(registrosSucursal.size(), sucursales.size());

        //WHEN MENOR A 0
        sucursales = cut.sucursalPorProceso(-1);
        Assert.assertEquals(0, sucursales.size());

        //WHEN EXCEPCION
        setEmNull();
        cut.sucursalPorProceso(id);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }
    
     @Test
    public void findRangeProcesosTest() {
        System.out.println("sucursal por procesos  test");
        //SET UP
        Mockito.when(ema.createNamedQuery("Proceso.findByNombre")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        int first = 0;
        int page = 2;
        String nombre = "simon";

        //WHEN TODO GOOD
        List<Proceso> procesos = cut.findRangeProcesos(first, page, nombre);
        Assert.assertEquals(registrosEsperados.size(), procesos.size());

        //WHEN EXCEPCION
        setEmNull();
        cut.findRangeProcesos(first, page, nombre);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }
}
