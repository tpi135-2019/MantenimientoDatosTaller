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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;

/**
 *
 * @author kevin
 */
@Path("marca")
@RequestScoped
public class MarcaResource extends AbstractResource<Marca, Integer> {

    @Inject
    private MarcaFacade marcaFacade;

    @Override
    protected FacadeGenerico getSessionBean() {
        return marcaFacade;
    }

    @GET
    @Path("{id}/modelo")
    public Response modeloPorMarca(@PathParam("id") Integer id,
            @DefaultValue("") @QueryParam("modelo") String modelo) {
        if (marcaFacade != null) {
            List<Modelo> modelos = marcaFacade.findModeloByMarcaLike(id, modelo);
            return Response.ok(modelos).build();
        }//agregar header
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarMarcaPorNombre(@QueryParam("nombre")String nombre){
        if(marcaFacade!=null){
            List<Marca> marcas=marcaFacade.findNombreLikeMarca(nombre);
            return Response.ok(marcas).build();
        }
   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }
    
}
