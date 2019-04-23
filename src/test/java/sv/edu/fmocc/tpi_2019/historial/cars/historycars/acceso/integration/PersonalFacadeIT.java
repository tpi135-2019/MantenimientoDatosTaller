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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class PersonalFacadeIT extends SessionBeanIT<Personal> {

    @Inject
    PersonalFacade cut;
    @Inject
    SucursalFacade sucursalFacade;
    List<Personal> datos = new ArrayList();

    public PersonalFacadeIT() {
        datos.add(new Personal(1, "juan", "perez"));
        datos.add(new Personal(5, "pedro", "el escamoso"));
        datos.add(new Personal(1, "pirulin", "pinpon"));
        
    }
    
    @Before
    public void before(){
        datos.forEach(item -> item.setIdSucursal(sucursalFacade.find(2)));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Personal> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdMecanico();
    }
    
    @Test
    public void testPersonalPorReparacion(){
        List<Personal> resultados = cut.personalPorReparacion(2);
        Assert.assertEquals(2, resultados.size());
    }
    
     @Test
    public void testPersonalPorProceso(){
        List<Personal> resultados = cut.personalPorProceso(1);
        Assert.assertEquals(2, resultados.size());
    }

}
