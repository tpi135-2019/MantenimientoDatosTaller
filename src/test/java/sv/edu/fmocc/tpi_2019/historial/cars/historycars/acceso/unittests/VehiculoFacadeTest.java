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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class VehiculoFacadeTest extends SessionBeanTest<Vehiculo> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private VehiculoFacade cut;
    private Vehiculo entidad = new Vehiculo("P35166845", "123456", "AD12384598D", "AD12384598D");
    private List<Vehiculo> registrosEsperados = new ArrayList<>();

    public VehiculoFacadeTest() {
        super(Vehiculo.class);
        registrosEsperados.add(new Vehiculo("P35166845", "123456", "AD12384598D", "AD12384598D"));
        registrosEsperados.add(new Vehiculo("P963258", "2156852", "ADFD15368D", "AD12384598D"));
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Vehiculo> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Vehiculo getEntity() {
        return entidad;
    }

}
