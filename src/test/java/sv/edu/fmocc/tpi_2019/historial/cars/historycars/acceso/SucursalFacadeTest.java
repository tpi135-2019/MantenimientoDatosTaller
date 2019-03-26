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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class SucursalFacadeTest extends SessionBeanTest<Sucursal> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private SucursalFacade cut;
    private Sucursal sucursal = new Sucursal(1);
    private List<Sucursal> registrosEsperados = new ArrayList<>();

    public SucursalFacadeTest() {
        super(Sucursal.class);
        registrosEsperados.add(new Sucursal(1, "taller chuchito"));
        registrosEsperados.add(new Sucursal(2, "taller chepito"));
    }

     @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Sucursal> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Sucursal getEntity() {
        return sucursal;
    }
}
