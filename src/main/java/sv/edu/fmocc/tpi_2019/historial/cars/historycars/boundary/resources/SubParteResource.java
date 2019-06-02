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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@Path("subparte")
@RequestScoped
public class SubParteResource extends AbstractResource<SubParte, Integer> {

    @Inject
    private SubParteFacade subParteFacade;
    @Inject
    private PiezaFacade piezaFacade;

    @Override
    protected FacadeGenerico getSessionBean() {
        return subParteFacade;
    }

    @GET
    @Path("{id}/piezas")
    public Response piezaPorSubParte(@PathParam("id") Integer idSubParte,
            @QueryParam("pieza") @DefaultValue("") String pieza) {
        if (piezaFacade != null) {
            List<Pieza> piezas = piezaFacade.piezasPorSubParte(idSubParte, pieza);
            return Response.ok(piezas).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
