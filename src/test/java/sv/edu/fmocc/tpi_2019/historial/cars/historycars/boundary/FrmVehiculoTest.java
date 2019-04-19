/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.Collections;
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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmVehiculoTest extends BackingBeanTest<Vehiculo> {

    @Mock
    VehiculoFacade vehiculoFacade;
    @Mock
    PropietarioFacade propietarioFacade;
    @Mock
    ModeloFacade modeloFacade;
    @Spy
    @InjectMocks
    FrmVehiculo cut = new FrmVehiculo();
    Vehiculo vehiculo = new Vehiculo("P323-12");
    List<Propietario> registrosPropietario = new ArrayList<>();
    List<Modelo> registrosModelo = new ArrayList<>();
    List<Vehiculo> registrosVehiculo = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosModelo.add(new Modelo(1, "corolla"));
        registrosModelo.add(new Modelo(1, "prius"));
        registrosPropietario.add(new Propietario(1, "Beto", "Pepeto"));
        registrosPropietario.add(new Propietario(2, "Daisy", "Lopez"));

        registrosVehiculo.add(new Vehiculo("P232-22", "BBGE740", "T57DDD", "DFFF"));
        registrosVehiculo.add(new Vehiculo("P882-11", "FDTE740", "KIEDD", "CNDIRE"));

    }

    @Override
    protected AbstractBean<Vehiculo> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Vehiculo> getFacade() {
        return vehiculoFacade;
    }

    @Override
    protected Vehiculo getEntity() {
        return vehiculo;
    }

    @Test
    public void listarModelosTest() {
        System.out.println("listarModelos");
        Mockito.when(modeloFacade.findAll()).thenReturn(registrosModelo);
        List modelo = cut.listarModelos();
        Assert.assertEquals(registrosModelo.size(), modelo.size());
        Mockito.when(modeloFacade.findAll()).thenThrow(Exception.class);
        modelo = cut.listarModelos();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), modelo.size());
    }

    @Test
    public void listarPropietariosTest() {
        System.out.println("listarPropietarios");
        Mockito.when(propietarioFacade.findAll()).thenReturn(registrosPropietario);
        List lista = cut.listarPropietarios();
        Assert.assertEquals(registrosPropietario.size(), lista.size());
        Mockito.when(propietarioFacade.findAll()).thenThrow(Exception.class);
        lista = cut.listarPropietarios();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Override
    protected List<Vehiculo> getLista() {
        return registrosVehiculo;
    }

    @Test
   public void getRowDTest(){
       String key="P232-22";
       getRowDataTest(key);
   }  
   
   @Test
   public void getListaModeloTest(){
       cut.setListaModelo(registrosModelo);
       List ls = cut.getListaModelo();
       Assert.assertEquals(registrosModelo,ls);
   }
   
   @Test
   public void getListaPropietarioTest(){
       cut.setListaPropietario(registrosPropietario);
       List ls = cut.getListaPropietario();
       Assert.assertEquals(registrosPropietario,ls);
   }
   
}
