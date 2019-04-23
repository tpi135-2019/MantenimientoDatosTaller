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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class PasoProcesoFacadeIT extends SessionBeanIT<PasoProceso> {

    @Inject
    PasoProcesoFacade cut;
    @Inject
    ProcesoFacade procesoFacade;
    List<PasoProceso> datos = new ArrayList();

    public PasoProcesoFacadeIT() {
        datos.add(new PasoProceso(1, 1));
        datos.add(new PasoProceso(5, 2));
        datos.add(new PasoProceso(1, 5));
    }

    @Before
    public void before() {
        datos.forEach(item -> {
            item.setIdProceso(procesoFacade.find(2));
            item.setIdPaso(new Paso(2, "bajar carro"));
        });
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<PasoProceso> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdPasoProceso();
    }
    
    @Test
    public void testPasosPorReparacion(){
        List<PasoProceso> resultados = cut.PasosPorProceso(1);
        Assert.assertEquals(0, resultados.size());
    }
    

}
