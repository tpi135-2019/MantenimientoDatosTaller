/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmSucursalTest extends BackingBeanTest<Sucursal> {

    @Mock
    SucursalFacade sucursalFacade;
    @Spy
    @InjectMocks
    FrmSucursal cut = new FrmSucursal();
    Sucursal sucursal = new Sucursal(1);
    List<Sucursal> registrosSucursal = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosSucursal.add(new Sucursal(1, "Taller los 3 Chepes"));
        registrosSucursal.add(new Sucursal(2, "Taller el vecino"));
    }

    @Override
    protected AbstractBean<Sucursal> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Sucursal> getFacade() {
        return sucursalFacade;
    }

    @Override
    protected Sucursal getEntity() {
        return sucursal;
    }

    @Override
    protected List<Sucursal> getLista() {
        return registrosSucursal;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }
}
