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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmPasoTest extends BackingBeanTest<Paso> {

    @Mock
    PasoFacade pasoFacade;
    @Spy
    @InjectMocks
    FrmPaso cut = new FrmPaso();
    Paso paso = new Paso(1);
    List<Paso> registrosPaso = new ArrayList<>();

    @Before
    public void setup() {
        registrosPaso.add(new Paso(1, "quitar llanta"));
        registrosPaso.add(new Paso(2, "poner nueva llanta"));
    }

    @Override
    protected AbstractBean<Paso> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Paso> getFacade() {
        return pasoFacade;
    }

    @Override
    protected Paso getEntity() {
        return paso;
    }

    @Override
    protected List<Paso> getLista() {
        return registrosPaso;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }
}
