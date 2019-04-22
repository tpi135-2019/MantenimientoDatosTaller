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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class PropietarioFacadeIT extends SessionBeanIT<Propietario> {

    @Inject
    PropietarioFacade cut;
    List<Propietario> datos = new ArrayList();

    public PropietarioFacadeIT() {
        datos.add(new Propietario(1, "juan", "perez"));
        datos.add(new Propietario(5, "daft", "punk"));
        datos.add(new Propietario(1, "elmo", "stark"));

    }
    
    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Propietario> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdPropietario();
    }

}
