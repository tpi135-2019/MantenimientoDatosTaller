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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class EspecialidadFacadeIT extends SessionBeanIT<Especialidad> {

    @Inject
    EspecialidadFacade cut;
    List<Especialidad> datos = new ArrayList();

    public EspecialidadFacadeIT() {
        datos.add(new Especialidad(1, "mecanica general"));
        datos.add(new Especialidad(2, "electricista"));
        datos.add(new Especialidad(3, "pintor"));
        
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Especialidad getEntity() {
        return new Especialidad((Integer) getId(), "aire acondicionado");
    }

    @Override
    protected List<Especialidad> getResgistros() {
        return datos;

    }

    @Override
    protected Class<?> getEntityClass() {
        return Especialidad.class;
    }

    @Override
    protected Object getId() {
        return datos.get(0).getIdEspecialidad();
    }

}
