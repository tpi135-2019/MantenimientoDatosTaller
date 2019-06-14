/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@Path("diagnostico")
@RequestScoped
public class DiagnosticoResource {

    @Inject
    private DiagnosticoFacade diagnosticoFacade;
    @Inject
    private ReparacionFacade reparacionFacade;

    @GET
    @Path("{id}/reparacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reparacionPorDiagnostico(@PathParam("id") Integer id) {
        if (reparacionFacade == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        if (id == null || id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<Reparacion> reparaciones = reparacionFacade.reparacionPorDiagnostico(id);
        return Response.ok(reparaciones).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarDiagnosticoLike(@QueryParam("id") @DefaultValue("") String id) {
        if (diagnosticoFacade == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        List<Diagnostico> vehiculos = diagnosticoFacade.findDiagnostico(id);
        return Response.ok(vehiculos).build();
    }

}
