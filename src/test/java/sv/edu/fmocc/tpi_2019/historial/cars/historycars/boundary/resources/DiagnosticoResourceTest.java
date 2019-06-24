/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class DiagnosticoResourceTest {

    @Mock
    private DiagnosticoFacade sessionBean;
    @Mock
    private ReparacionFacade reparacionFacade;
    @Mock
    private Logger logger;
    @InjectMocks
    @Spy
    private DiagnosticoResource cut;
    private List<Diagnostico> registrosEsperados = new ArrayList<>();
    private List<Reparacion> reparaciones = new ArrayList<>();

    private Diagnostico diagnostico;

    public DiagnosticoResourceTest() {
        registrosEsperados.add(new Diagnostico(1, "ah esta jodido ese bolado", new Date()));
        registrosEsperados.add(new Diagnostico(2, "ah esta jodido ese bolado", new Date()));
        reparaciones.add(new Reparacion(1));
        reparaciones.add(new Reparacion(2));
        diagnostico = registrosEsperados.get(0);
    }

    /*@Test
    public void findRangeTest() {
        //SETUP
        Mockito.when(sessionBean.findRange(1, 2)).thenReturn(registrosEsperados);
        Mockito.when(sessionBean.count()).thenReturn(registrosEsperados.size());

        //WHEN TODO GOOD
        Response respuesta = cut.findRange(1, 2);
        Assert.assertEquals(200, respuesta.getStatus());
        Assert.assertEquals("2", respuesta.getHeaderString("X-Cantidad-Registros"));

        //WHEN TODO GOOD
        Mockito.doThrow(new IllegalArgumentException()).when(sessionBean).findRange(1, 2);
        respuesta = cut.findRange(1, 2);
        Assert.assertEquals(500, respuesta.getStatus());
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));

        //WHEN SESSION BEAN NULL
        Mockito.doReturn(null).when(cut).getSessionBean();
        respuesta = cut.findRange(1, 2);
        Assert.assertEquals(500, respuesta.getStatus());
    }

    @Test
    public void findByIdTest() {
        //SETUP
        Mockito.when(sessionBean.find(1)).thenReturn(diagnostico);

        //WHEN TODO GOOD
        Response respuesta = cut.findById(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //WHEN BAD
        Mockito.doThrow(new IllegalArgumentException()).when(sessionBean).find(1);
        respuesta = cut.findById(1);
        Assert.assertEquals(500, respuesta.getStatus());
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));

        //ENTITY NULL
        Mockito.doReturn(null).when(sessionBean).find(1);
        respuesta = cut.findById(1);
        Assert.assertEquals(404, respuesta.getStatus());

        //WHEN SESSION BEAN NULL
        Mockito.doReturn(null).when(cut).getSessionBean();
        respuesta = cut.findById(1);
        Assert.assertEquals(500, respuesta.getStatus());
    }

    @Test
    public void crearTest() {

        //WHEN TODO GOOD
        Response respuesta = cut.crear(diagnostico);
        Assert.assertEquals(201, respuesta.getStatus());

        //WHEN BAD
        Mockito.doThrow(new IllegalArgumentException()).when(sessionBean).create(diagnostico);
        respuesta = cut.crear(diagnostico);
        Assert.assertEquals(500, respuesta.getStatus());
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));

        //ENTITY NULL
        respuesta = cut.crear(null);
        Assert.assertEquals(400, respuesta.getStatus());

        //WHEN SESSION BEAN NULL
        Mockito.doReturn(null).when(cut).getSessionBean();
        respuesta = cut.crear(diagnostico);
        Assert.assertEquals(500, respuesta.getStatus());
    }

    @Test
    public void editarTest() {

        //WHEN TODO GOOD
        Response respuesta = cut.editar(diagnostico);
        Assert.assertEquals(204, respuesta.getStatus());

        //WHEN BAD
        Mockito.doThrow(new IllegalArgumentException()).when(sessionBean).edit(diagnostico);
        respuesta = cut.editar(diagnostico);
        Assert.assertEquals(500, respuesta.getStatus());
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));

        //ENTITY NULL
        respuesta = cut.editar(null);
        Assert.assertEquals(400, respuesta.getStatus());

        //WHEN SESSION BEAN NULL
        Mockito.doReturn(null).when(cut).getSessionBean();
        respuesta = cut.editar(diagnostico);
        Assert.assertEquals(500, respuesta.getStatus());
    }

    @Test
    public void eliminarTest() {

        //WHEN TODO GOOD
        Mockito.when(sessionBean.find(1)).thenReturn(diagnostico);
        Response respuesta = cut.eliminar(1);
        Assert.assertEquals(204, respuesta.getStatus());

        //WHEN BAD
        Mockito.doThrow(new IllegalArgumentException()).when(sessionBean).remove(diagnostico);
        respuesta = cut.eliminar(1);
        Assert.assertEquals(500, respuesta.getStatus());
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString(), Matchers.any(Exception.class));

        //ENTITY NULL
        Mockito.doReturn(null).when(sessionBean).find(1);
        respuesta = cut.eliminar(1);
        Assert.assertEquals(404, respuesta.getStatus());

        //WHEN SESSION BEAN NULL
        Mockito.doReturn(null).when(cut).getSessionBean();
        respuesta = cut.eliminar(1);
        Assert.assertEquals(500, respuesta.getStatus());
    }
    */
    @Test
    public void reparacionPorDiagnosticoTest() {
        Mockito.when(reparacionFacade.reparacionPorDiagnostico(1)).thenReturn(reparaciones);

        //TODO GOOD
        Response respuesta = cut.reparacionPorDiagnostico(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO NULL
        respuesta = cut.reparacionPorDiagnostico(null);
        Assert.assertEquals(400, respuesta.getStatus());
        
        //TODO BAD
        Whitebox.setInternalState(cut, "reparacionFacade", null);
        respuesta = cut.reparacionPorDiagnostico(1);
        Assert.assertEquals(503, respuesta.getStatus());

        

    }

}
