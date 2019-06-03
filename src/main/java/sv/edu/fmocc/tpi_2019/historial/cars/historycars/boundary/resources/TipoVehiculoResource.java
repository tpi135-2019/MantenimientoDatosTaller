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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@Path("tipovehiculo")
@RequestScoped
public class TipoVehiculoResource extends AbstractResource<TipoVehiculo, Integer>{

    @Inject
    private TipoVehiculoFacade tipoVehiculoFacade;
    
    @Override
    protected FacadeGenerico getSessionBean() {
        return tipoVehiculoFacade;
    }
 
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarnombrePropietarioLike(@QueryParam("nombre")String nombre){
        if(tipoVehiculoFacade!=null){
            List<TipoVehiculo> tipovehiculos=tipoVehiculoFacade.findNombreLikePropietario(nombre);
            return Response.ok(tipovehiculos).build();
        }
   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }

}
