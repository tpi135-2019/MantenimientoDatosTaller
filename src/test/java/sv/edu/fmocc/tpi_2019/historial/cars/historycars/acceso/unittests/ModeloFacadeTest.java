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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class ModeloFacadeTest extends SessionBeanTest<Modelo> {
    
     
    @Mock
    EntityManager ema;
    @InjectMocks
    private ModeloFacade cut;
    private Modelo modelo = new Modelo(1);
    private List<Modelo> registrosEsperados = new ArrayList<>();

    public ModeloFacadeTest() {
        super(Modelo.class);
        registrosEsperados.add(new Modelo(1, "sentra"));
        registrosEsperados.add(new Modelo(2, "corolla"));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Modelo getEntity() {
        return modelo;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Modelo> getLista() {
        return registrosEsperados;
    }

}
