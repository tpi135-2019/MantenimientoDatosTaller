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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class SubParteFacadeIT extends SessionBeanIT<SubParte> {

    @Inject
    SubParteFacade cut;
    List<SubParte> datos = new ArrayList();
    Parte parte = new Parte(1, "motor");

    public SubParteFacadeIT() {
        datos.add(new SubParte(1, "crankshaft"));
        datos.add(new SubParte(5, "arbol de levas "));
        datos.add(new SubParte(1, "cadena de tiempo"));
        datos.forEach(item -> item.setIdParte(parte));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<SubParte> getResgistros() {
        return datos;
    }

    @Override
    protected Object getId() {
        return entity.getIdSubParte();
    }

}
