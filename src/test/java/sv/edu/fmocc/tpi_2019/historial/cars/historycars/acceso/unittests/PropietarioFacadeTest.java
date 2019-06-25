/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PropietarioFacadeTest extends SessionBeanTest<Propietario> {

    @Mock
    EntityManager ema;
    @InjectMocks
    PropietarioFacade cut = new PropietarioFacade();
    Propietario propietario = new Propietario(1);
    List<Propietario> registrosEsperados;
    private List<Vehiculo> registroVehiculos = new ArrayList<>();

    public PropietarioFacadeTest() {
        super(Propietario.class);
        this.registrosEsperados = listarRegistros();
        registroVehiculos.add(new Vehiculo("P35166845", "123456", "AD12384598D", "AD12384598D"));
        registroVehiculos.add(new Vehiculo("P963258", "2156852", "ADFD15368D", "AD12384598D"));
    }

    @Override
    protected FacadeGenerico<Propietario> getSessionBean() {
        return cut;
    }

    @Override
    protected Propietario getEntity() {
        return propietario;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Propietario> getLista() {
        return registrosEsperados;
    }

    public Propietario crearRegistro(int id, String nombre, String apellido, String direccion, String cel) {
        Propietario prop = new Propietario();
        prop.setIdPropietario(id);
        prop.setNombre(nombre);
        prop.setApellido(apellido);
        prop.setDireccion(direccion);
        prop.setTelefono(cel);
        return prop;
    }

    public List<Propietario> listarRegistros() {
        List<Propietario> ls = new ArrayList<>();
        ls.add(crearRegistro(1, "Ricardo", "Milos", "calle ayuwoky", "7423-2312"));
        ls.add(crearRegistro(2, "Karla", "Saucedo", "calle ayuwoky", "7443-2312"));

        return ls;
    }

    @Test
    public void historialPropietarioTest() {
        System.out.println("HistorialPropietario");
        String placa = "P323-12";
        Mockito.when(ema.createNamedQuery("Vehiculo.HistorialPropietarios")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);
        List historial = cut.historialPropietarios(placa);
        Assert.assertEquals(registrosEsperados.size(), historial.size());
        historial = cut.historialPropietarios(null);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), historial.size());
        setEmNull();
        cut.historialPropietarios(placa);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void vehiculosPorPropietarioTest() {
        System.out.println("VehiculosPorPropietario");
        Integer dui = 1;
        
        //TODO GOOD
        Mockito.when(ema.createNamedQuery("Propietario.VehiculosPropietario")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registroVehiculos);
        List vehiculos = cut.vehiculosPorPropietario(dui);
        Assert.assertEquals(registroVehiculos.size(), vehiculos.size());
        
        //DUI NULO
        vehiculos = cut.vehiculosPorPropietario(null);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), vehiculos.size());
        
        //LANZA EXCEPCION
        setEmNull();
        cut.vehiculosPorPropietario(dui);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }
    
    

}
