/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class ReparacionFacadeTest extends SessionBeanTest<Reparacion> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private ReparacionFacade cut;
    private Reparacion reparacion = new Reparacion(1);
    private List<Reparacion> registrosEsperados = new ArrayList<>();

    public ReparacionFacadeTest() {
        super(Reparacion.class);
        registrosEsperados.add(new Reparacion(1));
        registrosEsperados.add(new Reparacion(2));
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Reparacion> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Reparacion getEntity() {
        return reparacion;
    }
}
