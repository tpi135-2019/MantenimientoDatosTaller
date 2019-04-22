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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class ProcesoFacadeIT extends SessionBeanIT<Proceso> {

    @Inject
    ProcesoFacade cut;
    @Inject
    EspecialidadFacade especialidadFacade;
    List<Proceso> datos = new ArrayList();

    public ProcesoFacadeIT() {
        datos.add(new Proceso(1, "cambio de llantas"));
        datos.add(new Proceso(5, "cambio de aceite"));
        datos.add(new Proceso(1, "cambio de frenos"));
    }
    
    @Before
    public void before(){
        datos.forEach(item -> item.setIdEspecialidad(especialidadFacade.find(2)));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Proceso> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdProceso();
    }

}
