/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;

/**
 *
 * @author kevin
 * @param <T>
 */
public abstract class AbstractBean<T> implements Serializable{
 
    List<T> lista = new ArrayList<>();
    LazyDataModel <T> lazyModel;
   

    public enum EstadosCRUD {
        NONE, NUEVO, EDITAR, ELIMINAR, AGREGAR;
    }

    protected void inicializar() {
        llenar();
        modelo();
    }

     public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    /**
     * todos los registros existentes
     *
     * @return
     */
    public List<T> llenar() {
        if (getFacadeLocal().findAll() != null) {
            return this.lista = getFacadeLocal().findAll();
        } else {
            return this.lista = Collections.EMPTY_LIST;
        }
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

    public void addMessageError(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * metodo generico para persistir un registro
     */
    public void crear() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().create(getEntity());
                llenar();
                addMessage("Registro creado correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessageError("Error al crear registro.");
            }
        }
      
        
    }

    /**
     * modificar(editar) un registro de cualquier entidad
     */
    public void modificar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().edit(getEntity());
                llenar();
                addMessage("Edicion realizada correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessageError("Error al editar registro.");
            }
        }
    }

    /**
     * elimar un registro de cualquier entidad
     */
    public void eliminar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().remove(getEntity());
                llenar();
                addMessage("Registro eliminado correctamente");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessageError("Error al eliminar registro");
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
    
    protected abstract T getrowD(String rowkey);

    protected abstract Object getKey(T entity);

    protected abstract FacadeGenerico<T> getFacadeLocal();

    protected abstract T getEntity();

    
}
