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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonalResourceTest {

    @Mock
    private PersonalFacade personalFacade;
    @Mock
    private Logger logger;
    @InjectMocks
    @Spy
    private PersonalResource cut;
    private List<Personal> registrosEsperados = new ArrayList<>();

    public PersonalResourceTest() {
        registrosEsperados.add(new Personal(1));
        registrosEsperados.add(new Personal(2));
    }

    @Test
    public void testFindById() {
        Mockito.when(personalFacade.find(1)).thenReturn(registrosEsperados.get(0));

        //TODO GOOD
        Response respuesta = cut.findById(1);
        Assert.assertEquals(200, respuesta.getStatus());

        //TODO NULL
        respuesta = cut.findById(null);
        Assert.assertEquals(404, respuesta.getStatus());

        //TODO BAD
        Whitebox.setInternalState(cut, "personalFacade", null);
        respuesta = cut.findById(1);
        Assert.assertEquals(503, respuesta.getStatus());
    }

}
