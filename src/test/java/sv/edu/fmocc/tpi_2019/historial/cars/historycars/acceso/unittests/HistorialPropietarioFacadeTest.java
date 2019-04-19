/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.HistorialPropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.HistorialPropietario;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class HistorialPropietarioFacadeTest extends SessionBeanTest<HistorialPropietario> {
    @Mock
    EntityManager ema;
    @InjectMocks
    private HistorialPropietarioFacade cut;
    private HistorialPropietario historialPropietario = new HistorialPropietario(1);
    private List<HistorialPropietario> registrosEsperados = new ArrayList<>();

    public HistorialPropietarioFacadeTest() {
        super(HistorialPropietario.class);
        registrosEsperados.add(new HistorialPropietario(1, "P232-123", 1));
        registrosEsperados.add(new HistorialPropietario(2, "P432-187", 2));

    }

      @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected HistorialPropietario getEntity() {
        return historialPropietario;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<HistorialPropietario> getLista() {
        return registrosEsperados;
    }
    
    
    @Test
    public void testEdit() {
        testEditGeneric();
    }

    @Test
    public void testRemove() {
        testRemoveGeneric();
    }

}
