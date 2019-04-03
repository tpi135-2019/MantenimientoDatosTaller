/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmEspecialidad extends AbstractBean<Especialidad> implements Serializable {

    @Inject
    EspecialidadFacade especialidadFacade;

    @PostConstruct
    @Override
    protected void init() {
        super.init();

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

    @Override
    protected void crearNuevo() {
        this.registro = new Especialidad();
    }

    @Override
    protected Especialidad getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel() != null) {
            try {
                for (Especialidad item : (List<Especialidad>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (item.getIdEspecialidad().compareTo(registry) == 0) {
                        return item;
                    }
                }
            } catch (Exception e) {
            }
        }

        return null;
    }

    @Override
    protected Object getKey(Especialidad entity) {
        try {
            return entity.getIdEspecialidad();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return especialidadFacade;
    }

    @Override
    protected Especialidad getEntity() {
        return this.registro;
    }

    public LazyDataModel<Especialidad> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Especialidad> lazyModel) {
        this.lazyModel = lazyModel;
    }

}
