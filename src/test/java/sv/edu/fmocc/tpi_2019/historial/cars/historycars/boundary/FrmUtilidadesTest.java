/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class FrmUtilidadesTest {

    @Spy
    Logger logger = Logger.getGlobal();
    @Mock
    ReparacionFacade reparacionFacade;
    @Mock
    DiagnosticoFacade diagnosticoFacade;
    @Mock
    PropietarioFacade propietarioFacade;
    @Spy
    @InjectMocks
    FrmUtilidades cut;

    List<Reparacion> listaReparacion;
    List<Diagnostico> listaDiagnostico;
    List<Propietario> listaPropietario = new ArrayList<>();

    @Before
    public void setup() {
        listaDiagnostico = new ArrayList<>();
        listaDiagnostico.add(new Diagnostico(1));
        listaDiagnostico.add(new Diagnostico(2));
        listaPropietario.add(new Propietario(1));
        listaReparacion = new ArrayList<>();
        listaReparacion.add(new Reparacion(1));
        listaReparacion.add(new Reparacion(2));
        cut.setLogger(logger);
    }

    @Test
    public void buscraPorPlacaTest() {
        String placa = Matchers.anyString();
        Mockito.when(reparacionFacade.reparacionesPorPlaca(placa)).thenReturn(listaReparacion);
        List ls = cut.buscarPorPlaca();
        Assert.assertEquals(listaReparacion.size(), ls.size());
        Mockito.when(reparacionFacade.reparacionesPorPlaca(placa)).thenThrow(Exception.class);
        cut.buscarPorPlaca();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        Mockito.when(reparacionFacade.reparacionesPorPlaca(null)).thenReturn(Collections.EMPTY_LIST);
        ls = cut.buscarPorPlaca();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());

    }

    @Test
    public void buscarDiagnosticoPorPlacaTest() {
        String placa = Matchers.anyString();
        Mockito.when(diagnosticoFacade.diagnosticoPorPlaca(placa)).thenReturn(listaDiagnostico);
        List lista = cut.buscarDiagnosticoPorPlaca();
        Assert.assertEquals(listaDiagnostico.size(), lista.size());
        Mockito.when(diagnosticoFacade.diagnosticoPorPlaca(placa)).thenThrow(Exception.class);
        cut.buscarDiagnosticoPorPlaca();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        Mockito.when(diagnosticoFacade.diagnosticoPorPlaca(null)).thenReturn(Collections.EMPTY_LIST);
        lista = cut.buscarDiagnosticoPorPlaca();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());

    }

    @Test
    public void buscarHistorialPropietarioTest() {
        String placa = Matchers.anyString();
        Mockito.when(propietarioFacade.historialPropietarios(placa)).thenReturn(listaPropietario);
        List lista = cut.buscarHistorialDePropietarios();
        Assert.assertEquals(listaPropietario.size(), lista.size());
        Mockito.when(propietarioFacade.historialPropietarios(placa)).thenThrow(Exception.class);
        cut.buscarHistorialDePropietarios();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        Mockito.when(propietarioFacade.historialPropietarios(null)).thenReturn(Collections.EMPTY_LIST);
        lista = cut.buscarHistorialDePropietarios();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Test
    @Ignore
    public void buscarReparacionesPorDiagnosticoTest() {
        String diagnostico = "1";
        int num = 0;
        try {
            num = Integer.parseInt(diagnostico);

        } catch (NumberFormatException e) {
            System.out.println("rayos :c");
        }

        Mockito.when(reparacionFacade.reparacionPorDiagnostico(num)).thenReturn(listaReparacion);
        List ls = cut.buscarReparacionesPorDiagnostico();
        Assert.assertEquals(listaReparacion.size(), ls.size());
    }

    @Test
    public void buscarReparacionesEntreFechasTest(){
        
    }
    
}
