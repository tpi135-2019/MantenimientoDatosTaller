/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmReparacionTest extends BackingBeanTest<Reparacion> {

    @Mock
    ReparacionFacade reparacionFacade;
    @Mock
    DiagnosticoFacade diagnosticoFacade;
    @Mock
    PersonalFacade personalFacade;
    @Mock
    PiezaFacade piezaFacade;
    @Mock
    PasoProcesoFacade pasoProcesoFacade;
    @Mock
    ProcesoFacade procesoFacade;
    @Spy
    @InjectMocks
    FrmReparacion cut = new FrmReparacion();
    Reparacion reparacion = new Reparacion(1);
    List<Diagnostico> registrosDiagnostico = new ArrayList<>();
    List<Proceso> registrosProceso = new ArrayList<>();
    List<PasoProceso> registrosPasoProceso = new ArrayList<>();
    List<Pieza> registrosPieza = new ArrayList<>();
    List<Personal> registrosPersonal = new ArrayList<>();
    List<Reparacion> registrosReparacion = new ArrayList<>();
    Proceso proceso = new Proceso(1);

    @Before
    @Override
    public void setup() {
        registrosDiagnostico.add(new Diagnostico(1, "se le pincho la llanta patron", new Date()));
        registrosDiagnostico.add(new Diagnostico(2, "necesita un cambio de acite", new Date()));

        registrosReparacion.add(new Reparacion(1));
        registrosReparacion.add(new Reparacion(2));

        registrosPasoProceso.add(new PasoProceso(1));
        registrosPersonal.add(new Personal(1));
        registrosProceso.add(new Proceso(1));
        registrosPieza.add(new Pieza(1));

    }

    @Override
    protected AbstractBean<Reparacion> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Reparacion> getFacade() {
        return reparacionFacade;
    }

    @Override
    protected Reparacion getEntity() {
        return reparacion;
    }

    @Test
    public void listarDiagnosticoTest() {
        System.out.println("listarDiagnostico");
        Mockito.when(diagnosticoFacade.findAll()).thenReturn(registrosDiagnostico);
        List lista = cut.listarDiagnosticos();
        Assert.assertEquals(registrosDiagnostico.size(), lista.size());
        Mockito.when(diagnosticoFacade.findAll()).thenThrow(Exception.class);
        lista = cut.listarDiagnosticos();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Test
    public void listarPiezasTest() {
        System.out.println("listarPiezas");
        Mockito.when(piezaFacade.findAll()).thenReturn(registrosPieza);
        List lista = cut.listarPiezas();
        Assert.assertEquals(registrosPieza.size(), lista.size());
        Mockito.when(piezaFacade.findAll()).thenThrow(Exception.class);
        lista = cut.listarPiezas();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Test
    public void listarPersonalTest() {
        System.out.println("listarPersonal");
        cut.setProceso(proceso);
        Mockito.when(personalFacade.personalPorProceso(Matchers.anyInt())).thenReturn(registrosPersonal);
        List lista = cut.listarPersonal();
        Assert.assertEquals(registrosPersonal.size(), lista.size());
        Mockito.when(personalFacade.personalPorProceso(Matchers.anyInt())).thenThrow(Exception.class);
        lista = cut.listarPersonal();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Test
    public void listarProcesoTest() {
        System.out.println("listarProceso");
        Mockito.when(procesoFacade.findAll()).thenReturn(registrosProceso);
        List lista = cut.listarProcesos();
        Assert.assertEquals(registrosProceso.size(), lista.size());
        Mockito.when(procesoFacade.findAll()).thenThrow(Exception.class);
        lista = cut.listarProcesos();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Test
    public void listarPasoProcesoTest() {
        System.out.println("listarPasoProceso");
        cut.setProceso(proceso);
        Mockito.when(pasoProcesoFacade.PasosPorProceso(Matchers.anyInt())).thenReturn(registrosPasoProceso);
        List lista = cut.listarPasoProceso();
        Assert.assertEquals(registrosPasoProceso.size(), lista.size());
        Mockito.when(pasoProcesoFacade.PasosPorProceso(Matchers.anyInt())).thenThrow(Exception.class);
        lista = cut.listarPasoProceso();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Override
    protected List<Reparacion> getLista() {
        return registrosReparacion;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }

    @Test
    public void getListaDiagnosticoTest() {
        cut.setListaDiagnostico(registrosDiagnostico);
        List ls = cut.getListaDiagnostico();
        Assert.assertEquals(registrosDiagnostico, ls);
    }
}
