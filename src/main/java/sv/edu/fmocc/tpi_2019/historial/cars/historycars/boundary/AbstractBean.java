/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;

/**
 *
 * @author kevin
 * @param <T>
 */
public abstract class AbstractBean<T> implements Serializable {

    LazyDataModel<T> lazyModel;
    T registro;
    protected EstadosCRUD estado;
    protected Logger logger = Logger.getGlobal();

    public enum EstadosCRUD {
        NONE, NUEVO, EDITAR, ELIMINAR, AGREGAR;
    }

    protected void init() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
        modelo();
    }

    /**
     * mensaje para mostrar informacion de procesos
     *
     * @param summary mensaje a mostar dependiendo donde se implemente
     */
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * metodo generico para persistir un registro
     */
    public void crear() {
        estado = EstadosCRUD.AGREGAR;
        FacadeGenerico facade = getFacadeLocal();
        registro = getEntity();

        if (facade != null) {

            try {
                facade.create(registro);
                estado = EstadosCRUD.NONE;
                addMessage("Registro creado correctamente.");
            } catch (Exception ex) {
                addMessage("Error al crear registro.");
                estado = EstadosCRUD.NONE;
                logger.log(Level.SEVERE, ex.getMessage());
            }
        }

    }

    /**
     * modificar(editar) un registro de cualquier entidad
     */
    public void modificar() {
        estado = EstadosCRUD.EDITAR;
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().edit(getEntity());
                estado = EstadosCRUD.NONE;
                addMessage("Edicion realizada correctamente.");
            } catch (Exception ex) {
                addMessage("Error al editar registro.");
                estado = EstadosCRUD.NONE;
                logger.log(Level.SEVERE, ex.getMessage());
            } finally {
                crearNuevo();
            }
        }
    }

    /**
     * elimar un registro de cualquier entidad
     */
    public void eliminar() {
        estado = EstadosCRUD.ELIMINAR;
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().remove(getEntity());
                estado = EstadosCRUD.NONE;
                addMessage("Registro eliminado correctamente");
            } catch (Exception ex) {
                addMessage("Error al eliminar registro");
                estado = EstadosCRUD.NONE;
                logger.log(Level.SEVERE, ex.getMessage());

            }
        }
    }

    /**
     * Modelo para el lazyDataModel para ser implementantado por cualquier
     * entidad
     */
    public void modelo() {
        try {
            this.lazyModel = new LazyDataModel<T>() {

                @Override
                public Object getRowKey(T entity) {
                    return getKey(entity);
                }

                @Override
                public T getRowData(String rowKey) {
                    return getrowD(rowKey);

                }

                @Override
                public List<T> load(int first, int pageSize, String sortField, org.primefaces.model.SortOrder sortOrder, Map<String, Object> filters) {
                    this.setRowCount(getFacadeLocal().count());
                    return myLoad(first, pageSize);
                }
            };

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    }

    public List myLoad(int first, int pageSize) {
        List<T> ls = new ArrayList();
        try {
            if (getFacadeLocal() != null) {
                ls = getFacadeLocal().findRange(first, pageSize);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return ls;
    }

    public EstadosCRUD getEstado() {
        return estado;
    }

    public void onRowSelect(SelectEvent event) {
        estado = EstadosCRUD.EDITAR;
        registro = (T) event.getObject();
    }

    public T getRegistro() {
        if (registro == null) {
            crearNuevo();
        }
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public LazyDataModel<T> getLazyModel() {
        return lazyModel;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    protected abstract void crearNuevo();

    protected abstract void btncancelarHandler();

    protected abstract void btnNuevoHandler();

    protected abstract T getrowD(String rowkey);

    protected abstract Object getKey(T entity);

    protected abstract FacadeGenerico getFacadeLocal();

    protected abstract T getEntity();

}
