/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmModeloTest extends BackingBeanTest<Modelo> {

    @Mock
    ModeloFacade modeloFacade;
    @Mock
    MarcaFacade marcaFacade;
    @Mock
    TipoVehiculoFacade tipoVehiculoFacade;

    @Spy
    @InjectMocks
    FrmModelo cut = new FrmModelo();
    Modelo modelo = new Modelo(1);
    Marca marca = new Marca(1);
    List<Marca> registrosMarca = new ArrayList<>();
    List<TipoVehiculo> registrosTipoVehiculo = new ArrayList<>();
    List<Modelo> registrosModelo = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosMarca.add(new Marca(1, "Toyota"));
        registrosMarca.add(new Marca(2, "Nissan"));
        registrosTipoVehiculo.add(new TipoVehiculo(1, "sedan"));
        registrosTipoVehiculo.add(new TipoVehiculo(2, "deportivo"));
        registrosModelo.add(new Modelo(1, "corolla"));
        registrosModelo.add(new Modelo(2, "Prius"));

    }

    @Override
    protected AbstractBean<Modelo> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Modelo> getFacade() {
        return modeloFacade;
    }

    @Override
    protected Modelo getEntity() {
        return modelo;
    }

    @Override
    protected List<Modelo> getLista() {
        return registrosModelo;
    }

    @Test
    public void listarMarcasTest() {
        System.out.println("ListarMarcas");
        cut.listarMarcas();
        Mockito.verify(marcaFacade).findAll();
        cut.marcaFacade=null;
        cut.listarMarcas();
        Mockito.doThrow(Exception.class).when(marcaFacade).findAll();
    }

    @Test
    public void listarTipoVehiculoTest() {
        System.out.println("ListarTipoVehiculo");
        cut.listarTiposdeVehiculo();
         Mockito.verify(tipoVehiculoFacade).findAll();
        cut.tipoVehiculoFacade=null;
        cut.listarTiposdeVehiculo();
        Mockito.doThrow(Exception.class).when(tipoVehiculoFacade).findAll(); 
  
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }

    @Test
    public void getListaMarcaTest() {
        cut.setListaMarcas(registrosMarca);
        List ls = cut.getListaMarcas();
        Assert.assertEquals(registrosMarca,ls);
    }

    @Test
    public void getListaTipoVehiculoTest() {
        cut.setListaTipoVehiculo(registrosTipoVehiculo);
        List ls = cut.getListaTipoVehiculo();
        Assert.assertEquals(registrosTipoVehiculo,ls);
    }

}
