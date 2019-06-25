/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class ReparacionResourceTest {

    @Mock
    private ReparacionFacade reparacionFacade;
    @Mock
    private PiezaFacade piezaFacade;
    @Mock
    private PersonalFacade personalService;
    @Mock
    private PasoFacade pasoService;
    @Mock
    private Logger logger;
    @InjectMocks
    @Spy
    private ReparacionResource cut;
    private List<Reparacion> registrosEsperados = new ArrayList<>();
    private List<Pieza> registrosPiezas = new ArrayList<>();
    private List<Personal> registrosPersonal = new ArrayList<>();
    private List<Paso> registrosPaso = new ArrayList<>();
    private List<Sucursal> registrosSucursal = new ArrayList<>();

    public ReparacionResourceTest() {
        registrosEsperados.add(new Reparacion(1));
        registrosEsperados.add(new Reparacion(2));
        registrosPiezas.add(new Pieza(1, "biela"));
        registrosPiezas.add(new Pieza(2, "cadena de tiempo"));
        registrosPersonal.add(new Personal(1, "milo", "reyes"));
        registrosPersonal.add(new Personal(2, "chele", "papaya"));
        registrosPaso.add(new Paso(1, "levantar carro"));
        registrosPaso.add(new Paso(2, "quitar llanta"));
        registrosSucursal.add(new Sucursal(1, "taller chuchito"));
        registrosSucursal.add(new Sucursal(2, "taller chepito"));
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Mockito.when(reparacionFacade.find(1)).thenReturn(registrosEsperados.get(0));

        //TODO GOOD
        Response respuesta = cut.findById(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO NULL
        respuesta = cut.findById(null);
        Assert.assertEquals(404, respuesta.getStatus());

        //TODO BAD
        Whitebox.setInternalState(cut, "reparacionService", null);
        respuesta = cut.findById(1);
        Assert.assertEquals(503, respuesta.getStatus());
    }

    @Test
    public void testPiezasPorReparacion() {
        System.out.println("piezasPorReparacion");
        Mockito.when(piezaFacade.piezasReparacion(1)).thenReturn(registrosPiezas);

        //TODO GOOD
        Response respuesta = cut.piezasPorReparacion(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO BAD
        Whitebox.setInternalState(cut, "piezaService", null);
        respuesta = cut.piezasPorReparacion(1);
        Assert.assertEquals(503, respuesta.getStatus());
    }

    @Test
    public void testPasosPorReparacion() {
        System.out.println("pasosPorReparacion");
        Mockito.when(pasoService.pasoReparacion(1)).thenReturn(registrosPaso);

        //TODO GOOD
        Response respuesta = cut.pasosPorReparacion(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO BAD
        Whitebox.setInternalState(cut, "pasoService", null);
        respuesta = cut.pasosPorReparacion(1);
        Assert.assertEquals(503, respuesta.getStatus());
    }

    @Test
    public void testPersonalPorReparacion() {
        System.out.println("personalPorReparacion");
        Mockito.when(personalService.personalPorReparacion(1)).thenReturn(registrosPersonal);

        //TODO GOOD
        Response respuesta = cut.personalPorReparacion(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO BAD
        Whitebox.setInternalState(cut, "personalService", null);
        respuesta = cut.personalPorReparacion(1);
        Assert.assertEquals(503, respuesta.getStatus());
    }

    @Test
    public void testLugarReparacion() {
        System.out.println("lugarReparacion");
        Mockito.when(reparacionFacade.lugarReparacion(1)).thenReturn(registrosSucursal);

        //TODO GOOD
        Response respuesta = cut.lugarReparacion(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO BAD
        Whitebox.setInternalState(cut, "reparacionService", null);
        respuesta = cut.lugarReparacion(1);
        Assert.assertEquals(503, respuesta.getStatus());
    }

}
