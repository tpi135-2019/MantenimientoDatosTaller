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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.util.Loggable;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@Path("reparacion")
@RequestScoped
@Loggable
public class ReparacionResource {

    @Inject
    private ReparacionFacade reparacionService;
    @Inject
    private PiezaFacade piezaService;
    @Inject
    private PersonalFacade personalService;
    @Inject
    private PasoFacade pasoService;
    @Inject
    private Logger logger;
    private Reparacion reparacion;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        if (reparacionService == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        try {
            reparacion = reparacionService.find(id);
            if (reparacion == null) {
                return Response.status(Response.Status.NOT_FOUND).header("Date", new Date()).build();
            }
            return Response.ok(reparacion).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @GET
    @Path("{id}/pieza")
    @Produces(MediaType.APPLICATION_JSON)
    public Response piezasPorReparacion(@PathParam("id") Integer id) {
        if (piezaService == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        try {
            List<Pieza> piezas = piezaService.piezasReparacion(id);
            return Response.ok(piezas).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @GET
    @Path("{id}/paso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pasosPorReparacion(@PathParam("id") Integer id) {
        if (pasoService == null) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).header("Date", new Date()).build();
        }
        try {
            List<Paso> pasos = pasoService.pasoReparacion(id);
            return Response.ok(pasos).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @GET
    @Path("{id}/personal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response personalPorReparacion(@PathParam("id") Integer id) {
        if (personalService == null) {
            throw new ServiceUnavailableException();
        }
        try {
            List<Personal> personal = personalService.personalPorReparacion(id);
            return Response.ok(personal).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }
    
    @GET
    @Path("{id}/sucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lugarReparacion(@PathParam("id") Integer id) {
        if (reparacionService == null) {
            throw new ServiceUnavailableException();
        }
        try {
            return Response.ok(reparacionService.lugarReparacion(id)).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }
    
/*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(T registro) {
        FacadeGenerico sessionBean = getSessionBean();
        if (registro == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (sessionBean == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        try {
            sessionBean.create(registro);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(T registro) {
        if (registro == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        FacadeGenerico sessionBean = getSessionBean();
        if (sessionBean == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        try {
            sessionBean.edit(registro);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") P id) {
        FacadeGenerico sessionBean = getSessionBean();
        if (sessionBean == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        entity = (T) sessionBean.find(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            sessionBean.remove(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.serverError().build();
        }

    }
*/
}
