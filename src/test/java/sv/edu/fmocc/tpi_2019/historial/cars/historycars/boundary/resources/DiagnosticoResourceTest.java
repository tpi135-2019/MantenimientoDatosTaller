/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
    
    @Test
    public void buscarDiagnosticoLikeTest() {
        Mockito.when(sessionBean.findDiagnostico("1")).thenReturn(registrosEsperados);

        //TODO GOOD
        Response respuesta = cut.buscarDiagnosticoLike("1");
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO BAD
        Whitebox.setInternalState(cut, "diagnosticoFacade", null);
        respuesta = cut.buscarDiagnosticoLike("1");
        Assert.assertEquals(503, respuesta.getStatus());

    }

}
