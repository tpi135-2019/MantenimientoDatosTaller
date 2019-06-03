/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@Path("propietario")
@RequestScoped
public class PropietarioResource extends AbstractResource<Propietario, Integer> {

    @Inject
    private PropietarioFacade propietarioFacade;

    @Override
    protected FacadeGenerico<Propietario> getSessionBean() {
        return propietarioFacade;
    }

    @GET
    @Path("{id}/vehiculos")
    public Response vehiculosPorPropietario(@PathParam("id") Integer idPropietario) {
        if (propietarioFacade != null) {
            List<Vehiculo> vehiculo = propietarioFacade.vehiculosPorPropietario(idPropietario);
            return Response.ok(vehiculo).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

@GET
@Path("search")
@Produces(MediaType.APPLICATION_JSON)
public Response findbyIdLikePropietario(@QueryParam("dui") String id){
    if(propietarioFacade!=null){
        List<Propietario> propietarios=propietarioFacade.buscarPropietarioLike(id);
        return Response.ok(propietarios).build();
    }
    
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
}

}
