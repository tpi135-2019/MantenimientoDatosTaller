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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class TipoVehiculoFacadeTest extends SessionBeanTest<TipoVehiculo> {

    public TipoVehiculoFacadeTest() {
        super(TipoVehiculo.class);
        registrosEsperados.add(new TipoVehiculo(1, "sedan"));
        registrosEsperados.add(new TipoVehiculo(2, "pickup"));
    }

    @Mock
    EntityManager ema;
    @InjectMocks
    private TipoVehiculoFacade cut;
    private TipoVehiculo entidad = new TipoVehiculo(1);
    private List<TipoVehiculo> registrosEsperados = new ArrayList<>();

     @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<TipoVehiculo> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected TipoVehiculo getEntity() {
        return entidad;
    }

}
