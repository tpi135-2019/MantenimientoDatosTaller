/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.Response.Status;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Path("sucursal")
@RequestScoped
public class SucursalResource {

    @Inject
    private SucursalFacade sucursalFacade;
    @Inject
    private Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int first,
            @QueryParam("pagesize") @DefaultValue("10") int pagesize) {

        if (sucursalFacade == null) {
            return Response.status(Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        try {
            List<Sucursal> resultados = sucursalFacade.findRange(first, pagesize);
            return Response.ok(resultados).header("X-Cantidad-Registros", sucursalFacade.count()).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}/personal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response personalPorSucursal(@PathParam("id") Integer idSucursal,
            @QueryParam("nombre") @DefaultValue("") String nombre) {
        if (sucursalFacade == null) {
            return Response.status(Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        List<Personal> personal = sucursalFacade.personalPorSucursal(idSucursal, nombre);
        return Response.ok(personal).build();
    }

    @GET
    @Path("{id}/proceso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response procesosPorSucursal(@PathParam("id") Integer idSucursal) {
        if (sucursalFacade == null) {
            return Response.status(Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        List<Proceso> personal = sucursalFacade.procesosPorSucursal(idSucursal);
        return Response.ok(personal).build();
    }
}
