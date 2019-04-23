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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class PasoFacadeIT extends SessionBeanIT<Paso> {

    @Inject
    PasoFacade cut;
    List<Paso> datos = new ArrayList();

    public PasoFacadeIT() {
        datos.add(new Paso(1, "levantar carro"));
        datos.add(new Paso(5, "bajar carro"));
        datos.add(new Paso(1, "quitar tuerca"));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Paso> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdPaso();
    }
    
    @Test
    public void testPasoReparacion(){
        List<Paso> resultados = cut.pasoReparacion(2);
        Assert.assertEquals(2, resultados.size());
    }

}
