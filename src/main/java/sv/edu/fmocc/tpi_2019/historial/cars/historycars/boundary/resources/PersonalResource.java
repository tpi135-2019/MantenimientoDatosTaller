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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;

/**
 *
 * @author kevin
 */
@Path("personal")
@RequestScoped
public class PersonalResource extends AbstractResource<Personal, Integer> {

    @Inject
    private PersonalFacade personalFacade;

    @Override
    protected FacadeGenerico getSessionBean() {
        return  personalFacade;
    }

    @Override
    protected Personal getNewEntity() {
        return null;
    }
}