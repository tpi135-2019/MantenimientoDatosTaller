/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmProceso extends AbstractBean<Proceso> implements Serializable {

    @Inject
    ProcesoFacade procesoFacade;
    @Inject
    EspecialidadFacade especialidadFacade;
    List<Especialidad> listaEspecialidad;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarEspecialidades();
    }

    @Override
    public void crear() {
        estado = EstadosCRUD.AGREGAR;
        super.crear();
    }

    @Override
    public void modificar() {
        estado = EstadosCRUD.EDITAR;
        super.modificar();
    }

    @Override
    public void eliminar() {
        estado = EstadosCRUD.ELIMINAR;
        super.eliminar();
    }

    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    public void btnNuevoHandler() {
        estado = EstadosCRUD.NUEVO;
    }

    public List listarEspecialidades() {
        try {
            return listaEspecialidad = especialidadFacade.findAll();
        } catch (Exception e) {
          return  listaEspecialidad = Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new Proceso();
    }

    @Override
    protected Proceso getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            try {
                for (Proceso item : (List<Proceso>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (item.getIdProceso().compareTo(registry) == 0) {
                        return item;
                    }
                }
            } catch (Exception e) {
            }
        }

        return null;
    }

    @Override
    protected Object getKey(Proceso entity) {
     
            return entity.getIdProceso();
     
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return procesoFacade;
    }

    @Override
    protected Proceso getEntity() {
        return this.registro;
    }

    public List<Especialidad> getListaEspecialidad() {
        return listaEspecialidad;
    }

    public void setListaEspecialidad(List<Especialidad> listaEspecialidad) {
        this.listaEspecialidad = listaEspecialidad;
    }

    public LazyDataModel<Proceso> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Proceso> lazyModel) {
        this.lazyModel = lazyModel;
    }


}
