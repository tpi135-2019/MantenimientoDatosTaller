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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.HistorialPropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.HistorialPropietario;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class HistorialPropietarioFacadeIT extends SessionBeanIT<HistorialPropietario> {

    @Inject
    HistorialPropietarioFacade cut;
    List<HistorialPropietario> datos = new ArrayList();

    public HistorialPropietarioFacadeIT() {
        datos.add(new HistorialPropietario(1, "P365428", 2));
        datos.add(new HistorialPropietario(5, "P785825", 2));
        datos.add(new HistorialPropietario(1, "P365428", 1));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<HistorialPropietario> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getNumeroRegistro();
    }

}
