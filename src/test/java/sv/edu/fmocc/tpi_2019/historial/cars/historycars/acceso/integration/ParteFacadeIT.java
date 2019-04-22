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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class ParteFacadeIT extends SessionBeanIT<Parte> {

    @Inject
    ParteFacade cut;
    List<Parte> datos = new ArrayList();

    public ParteFacadeIT() {
        datos.add(new Parte(1, "motor"));
        datos.add(new Parte(5, "radiador"));
        datos.add(new Parte(1, "calefaccion"));

    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Parte> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdParte();
    }

}
