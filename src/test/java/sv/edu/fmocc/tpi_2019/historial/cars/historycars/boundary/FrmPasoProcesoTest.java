/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmPasoProcesoTest extends BackingBeanTest<PasoProceso> {

    @Mock
    PasoProcesoFacade pasoProcesoFacade;
    @Mock
    PasoFacade pasoFacade;
    @Mock
    ProcesoFacade procesoFacade;
    @Spy
    @InjectMocks
    FrmPasoProceso cut = new FrmPasoProceso();
    PasoProceso pasoProceso = new PasoProceso(1);
    List<Paso> registrosPaso = new ArrayList<>();
    List<Proceso> registrosProceso = new ArrayList<>();
    List<PasoProceso> registrosPasoProceso = new ArrayList<>();

    @Before
    public void setup() {
        registrosPaso.add(new Paso(1, "colocar el gato hidraulico"));
        registrosPaso.add(new Paso(2, "quitar tuecas"));
        registrosPaso.add(new Paso(3, "quitar la llanta"));

        registrosProceso.add(new Proceso(1, "Cambio de llanta"));
        registrosProceso.add(new Proceso(2, "Cambio de aceite"));

        registrosPasoProceso.add(new PasoProceso(1, 1));
        registrosPasoProceso.add(new PasoProceso(2, 1));

    }

    @Override
    protected AbstractBean<PasoProceso> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<PasoProceso> getFacade() {
        return pasoProcesoFacade;
    }

    @Override
    protected PasoProceso getEntity() {
        return pasoProceso;
    }

    @Test
    public void listarPasosTest() {
        System.out.println("listarPasos");
        Mockito.when(pasoFacade.findAll()).thenReturn(registrosPaso);
        List lista = cut.listarPasos();
        Assert.assertEquals(registrosPaso.size(), lista.size());
        Mockito.when(pasoFacade.findAll()).thenThrow(Exception.class);
        lista = cut.listarPasos();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Test
    public void listarProcesosTest() {
        System.out.println("listarProcesos");
        Mockito.when(procesoFacade.findAll()).thenReturn(registrosProceso);
        List myList = cut.listarProcesos();
        Assert.assertEquals(registrosProceso.size(), myList.size());
        Mockito.when(procesoFacade.findAll()).thenThrow(Exception.class);
        myList = cut.listarProcesos();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), myList.size());

    }

    @Override
    protected List<PasoProceso> getLista() {
        return registrosPasoProceso;
    }

}
