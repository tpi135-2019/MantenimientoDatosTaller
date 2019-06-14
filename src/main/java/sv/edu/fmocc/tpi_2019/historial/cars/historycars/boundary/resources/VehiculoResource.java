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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@Path("vehiculo")
@RequestScoped
public class VehiculoResource  {

    @Inject
    private VehiculoFacade vehiculoFacade;
    @Inject
    private DiagnosticoFacade diagnosticoFacade;
    @Inject
    private ReparacionFacade reparacionService;

    @GET
    @Path("{placa}/diagnosticos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response diagnosticoPorPlaca(@PathParam("placa") String placa) {
        if (diagnosticoFacade == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }

        List<Diagnostico> diagnosticos = diagnosticoFacade.diagnosticoPorPlaca(placa);
        return Response.ok(diagnosticos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPlacaLike(@QueryParam("placa") String placa) {
        if (vehiculoFacade == null) {
            List<Vehiculo> vehiculos = vehiculoFacade.findPlaca(placa);
            return Response.ok(vehiculos).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("{placa}/paso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reparcionPorVehiculo(@PathParam("placa") String placa) {
        if (reparacionService == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        List<Reparacion> pasos = reparacionService.reparacionesPorPlaca(placa);
        return Response.ok(pasos).build();
    }

}
