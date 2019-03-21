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

    List<T> lista = new ArrayList<>();
    LazyDataModel<T> lazyModel;
    T registro;
    protected EstadosCRUD estado;

    public enum EstadosCRUD {
        NONE, NUEVO, EDITAR, ELIMINAR, AGREGAR;
    }

    protected void inicializar() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
        modelo();
    }

//    public static boolean isValidationFailed() {
//        return FacesContext.getCurrentInstance().isValidationFailed();
//    }

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
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().create(getEntity());

                addMessage("Registro creado correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessage("Error al crear registro.");
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

                addMessage("Edicion realizada correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessage("Error al editar registro.");
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

                addMessage("Registro eliminado correctamente");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessage("Error al eliminar registro");
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

                    if (entity != null) {
                        return getKey(entity);
                    }
                    return null;

                }

                @Override
                public T getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        return getrowD(rowKey);
                    }
                    return null;

                }

                @Override
                public List<T> load(int first, int pageSize, String sortField, org.primefaces.model.SortOrder sortOrder, Map<String, Object> filters) {
                    List<T> ls = new ArrayList<>();
                    try {
                        if (getFacadeLocal() != null) {
                            this.setRowCount(getFacadeLocal().count());
                            ls = getFacadeLocal().findRange(first, pageSize);
                        }
                    } catch (Exception e) {
                        System.out.println("Excepcion" + e.getMessage());
                    }

                    return ls;
                }

            };

        } catch (Exception e) {
            System.out.println("Excepcion" + e.getMessage());
        }

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

    public void crearNuevo() {
     
    }


    protected abstract T getrowD(String rowkey);

    protected abstract Object getKey(T entity);

    protected abstract FacadeGenerico<T> getFacadeLocal();

    protected abstract T getEntity();

}
