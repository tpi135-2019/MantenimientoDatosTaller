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
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Before;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class ModeloFacadeIT extends SessionBeanIT<Modelo> {

    @Inject
    ModeloFacade cut;
    @Inject
    MarcaFacade marcaF;
    @Inject
    TipoVehiculoFacade tipoF;
    List<Modelo> datos = new ArrayList();

    public ModeloFacadeIT() {
        datos.add(new Modelo(1, "ram"));
        datos.add(new Modelo(5, "sentra"));
        datos.add(new Modelo(1, "corolla"));
        this.idNuevo = 5;
    }

    @Before
    public void before() {
        datos.forEach(model -> {
            model.setIdMarca(marcaF.find(2));
            model.setIdTipoVehiculo(tipoF.find(2));
        });
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Modelo> getResgistros() {
        return datos;
    }

    @Override
    protected Object getId() {
        return entity.getIdModelo();
    }

}
