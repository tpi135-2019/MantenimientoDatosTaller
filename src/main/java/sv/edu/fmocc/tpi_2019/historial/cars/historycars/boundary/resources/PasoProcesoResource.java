/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;

/**
 *
 * @author kevin
 */
@Path("pasoproceso")
@RequestScoped
public class PasoProcesoResource extends AbstractResource<PasoProceso, Integer> {

    @Inject
    private PasoProcesoFacade pasoProcesoFacade;

    @Override
    protected FacadeGenerico getSessionBean() {
        return  pasoProcesoFacade;
    }

}
