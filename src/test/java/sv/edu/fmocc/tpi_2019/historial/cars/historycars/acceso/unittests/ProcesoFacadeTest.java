/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    /*
     @Test
    public void sucursalPorProcesoTest() {
        System.out.println("sucursal por procesos  test");
        //SET UP
        Mockito.when(ema.createNamedQuery("Sucursal.Procesos")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosProcesos);
        Integer id = 1;

        //WHEN TODO GOOD
        List<Proceso> procesos = cut.procesosPorSucursal(id);
        Assert.assertEquals(registrosProcesos.size(), procesos.size());

        //WHEN MENOR A 0
        procesos = cut.procesosPorSucursal(-1);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), procesos.size());

        //WHEN EXCEPCION
        setEmNull();
        cut.procesosPorSucursal(id);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }*/
}
