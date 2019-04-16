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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmPropietarioTest extends BackingBeanTest<Propietario> {

    @Mock
    PropietarioFacade propietarioFacade;
    @Spy
    @InjectMocks
    FrmPropietario cut = new FrmPropietario();

    Propietario propietario = new Propietario(1);
    List<Propietario> registrosPropietario = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosPropietario.add(new Propietario(1, "Pepe", "Grillo"));
        registrosPropietario.add(new Propietario(2, "Rolando", "Guirolla"));
    }

    @Override
    protected AbstractBean<Propietario> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Propietario> getFacade() {
        return propietarioFacade;
    }

    @Override
    protected Propietario getEntity() {
        return propietario;
    }

    @Override
    protected List<Propietario> getLista() {
        return registrosPropietario;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }
}
