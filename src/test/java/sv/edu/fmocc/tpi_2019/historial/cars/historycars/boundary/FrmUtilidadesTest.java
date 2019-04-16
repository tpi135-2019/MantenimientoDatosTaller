/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacadeTest;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

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
    @Mock
    SucursalFacade sucursalFacade;
    @Mock
    PiezaFacade piezaFacade;
    @Mock
    PasoFacade pasoFacade;
    @InjectMocks
    FrmUtilidades cut;

    List<Reparacion> listaReparacion;
    List<Diagnostico> listaDiagnostico = new ArrayList<>();
    List<Propietario> listaPropietario = new ArrayList<>();
    List<Sucursal> listaSucursal = new ArrayList<>();
    List<Pieza> listaPieza = new ArrayList<>();
    List<Paso> listaPaso = new ArrayList<>();

    @Before
    public void setup() {
        listaDiagnostico.add(new Diagnostico(1));
        listaPropietario.add(new Propietario(1));
        listaReparacion = new ArrayList<>();
        listaSucursal.add(new Sucursal(1));
        listaPaso.add(new Paso(1));
        listaReparacion.add(new Reparacion(1));
        listaPieza.add(new Pieza(1));
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
        cut.placa = null;
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
        cut.placa = null;
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
        cut.placa = null;
        lista = cut.buscarHistorialDePropietarios();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Test
    //@Ignore
    public void buscarReparacionesPorDiagnosticoTest() {

        String diagnostico = "1";
        cut.select = diagnostico;
        // todo nice
        Mockito.when(reparacionFacade.reparacionPorDiagnostico(new Integer(diagnostico))).thenReturn(listaReparacion);
        List ls = cut.buscarReparacionesPorDiagnostico();
        Assert.assertEquals(listaReparacion, ls);
        //catch 
        Mockito.when(reparacionFacade.reparacionPorDiagnostico(new Integer(diagnostico))).thenThrow(NumberFormatException.class);
        cut.buscarReparacionesPorDiagnostico();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        // parametro nulo o vacio
        cut.select = "";
        ls = cut.buscarReparacionesPorDiagnostico();
        Assert.assertEquals(Collections.EMPTY_LIST, ls);

    }

    @Test
    public void buscarReparacionesPorPersonalTest() {
        String personal = "1";
        cut.person = personal;
        Mockito.when(reparacionFacade.reparacionPorPersonal(new Integer(personal))).thenReturn(listaReparacion);
        List ls = cut.buscarReparacionesPorPersonal();
        Assert.assertEquals(listaReparacion.size(), ls.size());
        //catch 
        Mockito.when(reparacionFacade.reparacionPorPersonal(new Integer(personal))).thenThrow(NumberFormatException.class);
        cut.buscarReparacionesPorPersonal();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        // parametro nulo o vacio
        cut.person = "";
        ls = cut.buscarReparacionesPorPersonal();
        Assert.assertEquals(Collections.EMPTY_LIST, ls);
    }

    @Test
    public void buscarReparacionesEntreFechasTest() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date desde = null, hasta = null;
        try {
            desde = dateFormat.parse("1995-02-03");
            hasta = dateFormat.parse("2019-11-03");
        } catch (ParseException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        cut.desde = desde;
        cut.hasta = hasta;
        Mockito.when(reparacionFacade.reparacionEntreFechas(desde, hasta)).thenReturn(listaReparacion);
        List ml = cut.buscarReparacionesEntreFechas();
        Assert.assertEquals(listaReparacion, ml);
        Mockito.when(reparacionFacade.reparacionEntreFechas(desde, hasta)).thenThrow(Exception.class);
        cut.buscarReparacionesEntreFechas();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        cut.desde = null;
        cut.hasta = null;
        ml = cut.buscarReparacionesEntreFechas();
        Assert.assertEquals(Collections.EMPTY_LIST, ml);

    }

    @Test
    public void buscarSucursalPorReparacionTest() {
        String idReparacion = "1";
        cut.rep = idReparacion;
        Mockito.when(sucursalFacade.lugarReparacion(new Integer(idReparacion))).thenReturn(listaSucursal);
        List lista = cut.buscarSucursalPorReparacion();
        Assert.assertEquals(listaSucursal, lista);
        Mockito.when(sucursalFacade.lugarReparacion(new Integer(idReparacion))).thenThrow(NumberFormatException.class);
        cut.buscarSucursalPorReparacion();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        cut.rep = null;
        lista = cut.buscarSucursalPorReparacion();
        Assert.assertEquals(Collections.EMPTY_LIST, lista);
    }

    @Test
    public void buscarPiezasPorReparacionTest() {
        String reparacion = "1";
        cut.pieza = reparacion;
        Mockito.when(piezaFacade.piezasReparacion(new Integer(reparacion))).
                thenReturn(listaPieza);
        List myList = cut.buscarPiezasPorReparacion();
        Assert.assertEquals(listaPieza, myList);
        Mockito.when(piezaFacade.piezasReparacion(new Integer(reparacion))).
                thenThrow(NumberFormatException.class);
        cut.buscarPiezasPorReparacion();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        cut.pieza = "null";
        myList = cut.buscarPiezasPorReparacion();
        Assert.assertEquals(Collections.EMPTY_LIST, myList);

    }

    @Test
    public void buscarPasoPorReparacionTest() {
        String idReparacion = "1";
        cut.paso = idReparacion;
        Mockito.when(pasoFacade.pasoReparacion(new Integer(idReparacion))).thenReturn(listaPaso);
        List myList = cut.buscarPasoPorReparacion();
        Assert.assertEquals(listaPaso, myList);
        Mockito.when(pasoFacade.pasoReparacion(new Integer(idReparacion))).thenThrow(NumberFormatException.class);
        cut.buscarPasoPorReparacion();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
        cut.paso = null;
        myList = cut.buscarPasoPorReparacion();
        Assert.assertEquals(Collections.EMPTY_LIST, myList);

    }

}
