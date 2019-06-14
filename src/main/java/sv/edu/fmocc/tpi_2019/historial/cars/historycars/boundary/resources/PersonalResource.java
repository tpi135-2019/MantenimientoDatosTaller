/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;

/**
 *
 * @author kevin
 */
@Path("personal")
@RequestScoped
public class PersonalResource {

    @Inject
    private PersonalFacade personalFacade;
    private Personal mecanico;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        if (personalFacade == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        mecanico = personalFacade.find(id);
        if (mecanico == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(mecanico).build();
    }

}
