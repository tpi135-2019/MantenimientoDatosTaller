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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class PiezaFacadeIT extends SessionBeanIT<Pieza> {

    @Inject
    PiezaFacade cut;
    @Inject
    SubParteFacade subParteFacade;
    List<Pieza> datos = new ArrayList();

    public PiezaFacadeIT() {
        datos.add(new Pieza(1, "piston"));
        datos.add(new Pieza(5, "inyector"));
        datos.add(new Pieza(1, "bujia"));
    }
    
    @Before
    public void before(){
        datos.forEach(item -> item.setIdSubParte(subParteFacade.find(2)));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Pieza> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdPieza();
    }

}
