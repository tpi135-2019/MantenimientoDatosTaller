/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;

/**
 *
 * @author kevin
 */
@Path("especialidad")
@RequestScoped
public class EspecialidadResource extends AbstractResource<Especialidad, Integer> {

    @Inject
    private EspecialidadFacade especialidadFacade;

    @Override
    protected FacadeGenerico getSessionBean() {
        return especialidadFacade;
    }

    @Override
    protected Especialidad getNewEntity() {
        return null;
    }
}
