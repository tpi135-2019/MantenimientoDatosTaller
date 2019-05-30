/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary.resources;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;

/**
 *
 * @author kevin
 */
public abstract class AbstractResource<T, P> {

    private Logger logger = Logger.getGlobal();

    protected abstract FacadeGenerico getSessionBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int first,
            @QueryParam("pagesize") @DefaultValue("10") int pagesize) {

        FacadeGenerico sessionBean = getSessionBean();
        if (sessionBean != null) {
            try {
                List<T> resultados = sessionBean.findRange(first, pagesize);
                return Response.ok(resultados).header("x-cantidad-registros", sessionBean.count()).build();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("id") P id) {
        FacadeGenerico sessionBean = getSessionBean();
        if (sessionBean != null) {
            T entity = (T) sessionBean.find(id);
            if (entity == null) {
                return Response.status(Status.NOT_FOUND).build();
            }
            try {
                return Response.ok(sessionBean.find(id)).build();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(T registro) {
        FacadeGenerico sessionBean = getSessionBean();
        if (registro == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (sessionBean != null) {
            try {
                sessionBean.create(registro);
                return Response.status(Status.CREATED).build();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(T registro) {
        if (registro == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        FacadeGenerico sessionBean = getSessionBean();
        if (sessionBean != null) {
            try {
                sessionBean.edit(registro);
                return Response.accepted().build();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") P id) {
        FacadeGenerico sessionBean = getSessionBean();
        if (sessionBean != null) {
            T entity = (T) sessionBean.find(id);
            if (entity == null) {
                return Response.status(Status.NOT_FOUND).build();
            }
            try {
                sessionBean.remove(entity);
                return Response.status(Status.ACCEPTED).build();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                return Response.serverError().build();
            }
        }
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
}
