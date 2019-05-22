/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmTipoVehiculo extends AbstractBean<TipoVehiculo> implements Serializable {

    @Inject
    protected TipoVehiculoFacade tipoVehiculoFacade;

    @PostConstruct
    @Override
    public void init() {
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

    @Override
    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    @Override
    public void btnNuevoHandler() {
        estado = EstadosCRUD.NUEVO;
    }

    @Override
    protected void crearNuevo() {
        this.registro = new TipoVehiculo();
    }

    @Override
    protected TipoVehiculo getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(tp -> tp.getIdTipoVehiculo().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);

        }

        return null;
    }

    public void setLazyModel(LazyDataModel<TipoVehiculo> lazyModel) {
        this.lazyModel = lazyModel;
    }

    @Override
    protected Object getKey(TipoVehiculo entity) {
        if (entity != null) {
            return entity.getIdTipoVehiculo();
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return tipoVehiculoFacade;
    }

    @Override
    protected TipoVehiculo getEntity() {
        return this.registro;
    }

}
