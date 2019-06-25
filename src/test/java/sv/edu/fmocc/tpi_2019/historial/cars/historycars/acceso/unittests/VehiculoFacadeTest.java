/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class VehiculoFacadeTest extends SessionBeanTest<Vehiculo> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private VehiculoFacade cut;
    private Vehiculo entidad = new Vehiculo("P35166845", "123456", "AD12384598D", "AD12384598D");
    private List<Vehiculo> registrosEsperados = new ArrayList<>();
    private List<Propietario> registrosPropietario = new ArrayList<>();

    public VehiculoFacadeTest() {
        super(Vehiculo.class);
        registrosEsperados.add(new Vehiculo("P35166845", "123456", "AD12384598D", "AD12384598D"));
        registrosEsperados.add(new Vehiculo("P963258", "2156852", "ADFD15368D", "AD12384598D"));
        registrosPropietario.add(new Propietario(1, "Ricardo", "Milos"));
        registrosPropietario.add(new Propietario(1, "Pew", "Diepie"));
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Vehiculo> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Vehiculo getEntity() {
        return entidad;
    }

    @Test
    public void findPropietarioTest() {
        System.out.println("sucursal por procesos  test");
        //SET UP
        Mockito.when(ema.createNamedQuery("Vehiculo.findPropietario")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosPropietario);
        String nombre = "P2547";

        //WHEN TODO GOOD
        List<Propietario> propietarios = cut.findPropietario(nombre);
        Assert.assertEquals(registrosPropietario.size(), propietarios.size());

        //WHEN MENOR A 0
        propietarios = cut.findPropietario(null);
        Assert.assertEquals(0, propietarios.size());

        //WHEN EXCEPCION
        setEmNull();
        cut.findPropietario(nombre);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }
    
     @Test
    public void findPlacaTest() {
        System.out.println("sucursal por procesos  test");
        //SET UP
        Mockito.when(ema.createNamedQuery("Vehiculo.vehiculoLikeplaca")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        String nombre = "P2547";

        //WHEN TODO GOOD
        List<Vehiculo> propietarios = cut.findPlaca(nombre);
        Assert.assertEquals(registrosPropietario.size(), propietarios.size());

        //WHEN MENOR A 0
        propietarios = cut.findPlaca(null);
        Assert.assertEquals(0, propietarios.size());

        //WHEN EXCEPCION
        setEmNull();
        cut.findPlaca(nombre);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

}
