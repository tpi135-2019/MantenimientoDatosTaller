/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmParteTest extends BackingBeanTest<Parte>{

    @Mock
    ParteFacade parteFacade;
    @Spy
    @InjectMocks
    FrmParte cut = new FrmParte();
    Parte parte= new Parte(1);
    List<Parte> registrosParte = new ArrayList<>();
    
    @Before
    public void setup(){
        registrosParte.add(new Parte(1,"Motor"));
        registrosParte.add(new Parte(2, "Interiores"));
    }
            
    
    @Override
    protected AbstractBean<Parte> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Parte> getFacade() {
        return parteFacade;
    }

    @Override
    protected Parte getEntity() {
        return parte;
    }

    @Override
    protected List<Parte> getLista() {
        return registrosParte;
    }
    
    
}
