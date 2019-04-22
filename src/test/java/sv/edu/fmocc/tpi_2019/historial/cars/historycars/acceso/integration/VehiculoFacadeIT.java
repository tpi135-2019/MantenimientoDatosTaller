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
import org.junit.Before;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class VehiculoFacadeIT extends SessionBeanIT<Vehiculo> {

    @Inject
    VehiculoFacade cut;
    @Inject
    ModeloFacade modelo;
    @Inject
    PropietarioFacade propietario;
    List<Vehiculo> datos = new ArrayList();

    public VehiculoFacadeIT() {
        datos.add(new Vehiculo("P789654", "2789654", "2789654", "2789654"));
        datos.add(new Vehiculo("P963258", "8745965", "8745965", "8745965"));
        datos.add(new Vehiculo("P789654", "7418520", "7418520", "7418520"));
    }

    @Before
    public void before() {
        datos.forEach(vehiculo -> {
            vehiculo.setIdModelo(modelo.find(2));
            vehiculo.setIdPropietario(propietario.find(2));
        });
        this.idNuevo = "P963258";
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Vehiculo> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdVehiculo();
    }

}
