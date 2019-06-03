/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@Path("proceso")
@RequestScoped
public class ProcesoResource extends AbstractResource<Proceso, Integer> {

    @Inject
    private ProcesoFacade procesoFacade;
    @Inject
    private PasoProcesoFacade pasoProcesoFacade;

    @Override
    protected FacadeGenerico getSessionBean() {
        return procesoFacade;
    }

    @GET
    @Path("{id}/pasos")
    public Response pasosPorProceso(@PathParam("id") Integer id,
            @QueryParam("paso") @DefaultValue("") String paso) {
        if (pasoProcesoFacade != null) {
            List<PasoProceso> pasos = pasoProcesoFacade.pasoProcesoPorProceso(id, paso);
            return Response.ok(pasos).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
