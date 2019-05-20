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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ParteFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@Path("parte")
@RequestScoped
public class ParteResource extends AbstractResource<Parte, Integer> {

    @Inject
    private ParteFacade parteFacade;
    @Inject
    private SubParteFacade subParteFacade;

    @Override
    protected FacadeGenerico getSessionBean() {
        return  parteFacade;
    }

    @Override
    protected Parte getNewEntity() {
        return null;
    }
    
    @GET
    @Path("{id}/subpartes")
    public Response piezaPorSubParte(@PathParam("id") Integer idParte,
            @QueryParam("subparte") @DefaultValue("") String subParte) {
        if (subParteFacade != null) {
            List<SubParte> subpartes = subParteFacade.subPartePorParte(idParte, subParte);
            return Response.ok(subpartes).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}