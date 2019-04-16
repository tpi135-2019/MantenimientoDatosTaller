/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmTipoVehiculoTest extends BackingBeanTest<TipoVehiculo> {

    @Mock
    TipoVehiculoFacade tipoVehiculoFacade;
    @Spy
    @InjectMocks
    FrmTipoVehiculo cut = new FrmTipoVehiculo();
    TipoVehiculo tipoVehiculo = new TipoVehiculo(1);
    List<TipoVehiculo> registrosTipoVehiculo = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosTipoVehiculo.add(new TipoVehiculo(1, "Sedan"));
        registrosTipoVehiculo.add(new TipoVehiculo(2, "Hatchback"));

    }

    @Override
    protected AbstractBean<TipoVehiculo> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<TipoVehiculo> getFacade() {
        return tipoVehiculoFacade;
    }

    @Override
    protected TipoVehiculo getEntity() {
        return tipoVehiculo;
    }

    @Override
    protected List<TipoVehiculo> getLista() {
        return registrosTipoVehiculo;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }
}
