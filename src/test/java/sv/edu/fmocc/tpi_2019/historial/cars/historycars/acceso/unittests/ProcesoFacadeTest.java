/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcesoFacadeTest extends SessionBeanTest<Proceso> {
    
    @Mock
    EntityManager ema;
    @InjectMocks
    private ProcesoFacade cut;
    private Proceso proceso = new Proceso(1);
    private List<Proceso> registrosEsperados = new ArrayList<>();

    public ProcesoFacadeTest() {
        super(Proceso.class);
        registrosEsperados.add(new Proceso(1, "cambio de aceite"));
        registrosEsperados.add(new Proceso(2, "cambio de frenos"));
    }

       
    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Proceso getEntity() {
        return proceso;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Proceso> getLista() {
        return registrosEsperados;
    }
}
