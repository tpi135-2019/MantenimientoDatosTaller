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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
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
    @Spy
    @InjectMocks
    FrmReparacion cut = new FrmReparacion();
    Reparacion reparacion = new Reparacion(1);
    List<Diagnostico> registrosDiagnostico = new ArrayList<>();
    List<Reparacion> registrosReparacion = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosDiagnostico.add(new Diagnostico(1, "se le pincho la llanta patron", new Date()));
        registrosDiagnostico.add(new Diagnostico(2, "necesita un cambio de acite", new Date()));

        registrosReparacion.add(new Reparacion(1));
        registrosReparacion.add(new Reparacion(2));

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
