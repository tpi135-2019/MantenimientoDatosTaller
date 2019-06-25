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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.util.Loggable;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Path("proceso")
@RequestScoped
@Loggable
public class ProcesoResource {

    @Inject
    private ProcesoFacade procesoFacade;
    @Inject
    private PasoProcesoFacade pasoProcesoFacade;
    @Inject
    private PersonalFacade personalFacade;
    @Inject
    private Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int first,
            @QueryParam("pagesize") @DefaultValue("10") int pagesize,
            @QueryParam("proceso") @DefaultValue("") String proceso) {

        if (procesoFacade == null) {
            return Response.status(Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        try {
            List<Proceso> resultados = procesoFacade.findRangeProcesos(first, pagesize, proceso);
            return Response.ok(resultados).header("X-Cantidad-Registros", procesoFacade.count()).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}/paso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pasosPorProceso(@PathParam("id") Integer id,
            @QueryParam("paso") @DefaultValue("") String paso) {
        if (pasoProcesoFacade == null) {
            return Response.status(Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        List<PasoProceso> pasos = pasoProcesoFacade.pasoProcesoPorProceso(id, paso);
        return Response.ok(pasos).build();
    }

    @GET
    @Path("{id}/personal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response personalPorProceso(@PathParam("id") Integer id) {
        if (personalFacade == null) {
            return Response.status(Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        List<Personal> pasos = personalFacade.personalPorProceso(id);
        return Response.ok(pasos).build();
    }

    @GET
    @Path("{id}/sucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sucursalPorProceso(@PathParam("id") Integer id) {
        if (procesoFacade == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        List<Sucursal> pasos = procesoFacade.sucursalPorProceso(id);
        return Response.ok(pasos).build();
    }

}
