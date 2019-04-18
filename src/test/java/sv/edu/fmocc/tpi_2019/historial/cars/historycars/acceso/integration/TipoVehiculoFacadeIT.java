/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class TipoVehiculoFacadeIT extends SessionBeanIT<TipoVehiculo> {

    @Inject
    TipoVehiculoFacade cut;
    List<TipoVehiculo> datos = new ArrayList();

    public TipoVehiculoFacadeIT() {
        datos.add(new TipoVehiculo(1, "sedan"));
        datos.add(new TipoVehiculo(2, "pickup"));
        datos.add(new TipoVehiculo(3, "trailer"));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected TipoVehiculo getEntity() {
        return new TipoVehiculo((Integer) getId(), "bus");
    }

    @Override
    protected List<TipoVehiculo> getResgistros() {
        return datos;
    }

    @Override
    protected Class<?> getEntityClass() {
        return TipoVehiculo.class;
    }

    @Override
    protected Object getId() {
        return datos.get(0).getIdTipoVehiculo();
    }

}
