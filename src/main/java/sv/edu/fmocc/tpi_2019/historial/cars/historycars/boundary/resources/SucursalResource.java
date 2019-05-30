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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Path("sucursal")
@RequestScoped
public class SucursalResource extends AbstractResource<Sucursal, Integer>{

    @Inject
    private SucursalFacade sucursalFacade;
    
    @Override
    protected FacadeGenerico getSessionBean() {
        return sucursalFacade;
    }

    @GET
    @Path("{id}/personal")
    public Response personalPorSucursal(@PathParam("id") Integer idSucursal,
            @QueryParam("nombre") @DefaultValue("") String nombre) {
        if (sucursalFacade != null) {
            List<Personal> personal = sucursalFacade.personalPorSucursal(idSucursal, nombre);
            return Response.ok(personal).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
