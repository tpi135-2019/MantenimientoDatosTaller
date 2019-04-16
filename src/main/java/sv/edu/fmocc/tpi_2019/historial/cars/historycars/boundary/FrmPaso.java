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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmPaso extends AbstractBean<Paso> implements Serializable {

    @Inject
    PasoFacade pasoFacade;

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
        this.registro = new Paso();
    }

    @Override
    protected Paso getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
         
                for (Paso item : (List<Paso>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (item.getIdPaso().compareTo(registry) == 0) {
                        return item;
                    }
                }
           
        }

        return null;
    }

    @Override
    protected Object getKey(Paso entity) {
        
            return entity.getIdPaso();
        
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return pasoFacade;
    }

    @Override
    protected Paso getEntity() {
        return this.registro;
    }

    public void setLazyModel(LazyDataModel<Paso> lazyModel) {
        this.lazyModel = lazyModel;
    }

}
