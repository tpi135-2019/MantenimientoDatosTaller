/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.junit.Assert;
import org.junit.Before;
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
    @Override
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
     @Override
    protected List<PasoProceso> getLista() {
        return registrosPasoProceso;
    }

    @Test
    public void listarPasosTest() {
        System.out.println("listarPasos");
        cut.listarPasos();
        Mockito.verify(pasoFacade).findAll();
        cut.pasofacade = null;
        cut.listarPasos();
        Mockito.doThrow(Exception.class).when(pasoFacade).findAll();
    }

    @Test
    public void listarProcesosTest() {
        System.out.println("listarProcesos");
        cut.listarProcesos();
        Mockito.verify(procesoFacade).findAll();
        cut.procesoFacade=null;
        cut.listarProcesos();
        Mockito.doThrow(Exception.class).when(procesoFacade).findAll();
    }


    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }
    
    @Test
    public void getListaProcesoTest(){
        cut.setListaProceso(registrosProceso);
        List ls = cut.getListaProceso();
        Assert.assertEquals(registrosProceso,ls);
    }
    
    @Test
    public void getListaPasoTest(){
        cut.setListaPaso(registrosPaso);
        List ls = cut.getListaPaso();
        Assert.assertEquals(registrosPaso,ls);
    }
}
