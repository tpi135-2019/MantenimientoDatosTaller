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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmVehiculo extends AbstractBean<Vehiculo> implements Serializable {

    @Inject
    VehiculoFacade vehiculoFacade;
    @Inject
    ModeloFacade modeloFacade;
    List<Modelo> listaModelo;
    @Inject
    PropietarioFacade propietarioFacade;
    List<Propietario> listaPropietario;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarModelos();
        listarPropietarios();
    }

    public List listarModelos() {
        try {
            return listaModelo = modeloFacade.findAll();
        } catch (Exception e) {
            return listaModelo = Collections.EMPTY_LIST;
        }
    }

    public List listarPropietarios() {
        try {
            return listaPropietario = propietarioFacade.findAll();
        } catch (Exception e) {
            return listaPropietario = Collections.EMPTY_LIST;
        }
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
    protected Vehiculo getrowD(String rowkey) {

        if (rowkey != null) {
            try {

                for (Vehiculo item : (List<Vehiculo>) this.getLazyModel().getWrappedData()) {
                    String registry = (rowkey);
                    if (item.getIdVehiculo().compareTo(registry) == 0) {
                        return item;
                    }

                }

            } catch (NumberFormatException e) {
                System.out.println("Excepcion" + e.getMessage());
            }
        }

        return null;
    }

    @Override
    protected Object getKey(Vehiculo entity) {

        return entity.getIdVehiculo();

    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return vehiculoFacade;
    }

    @Override
    protected Vehiculo getEntity() {
        return this.registro;
    }

    public LazyDataModel<Vehiculo> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Vehiculo> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public List<Modelo> getListaModelo() {
        return listaModelo;
    }

    public void setListaModelo(List<Modelo> listaModelo) {
        this.listaModelo = listaModelo;
    }

    public List<Propietario> getListaPropietario() {
        return listaPropietario;
    }

    public void setListaPropietario(List<Propietario> listaPropietario) {
        this.listaPropietario = listaPropietario;
    }


    @Override
    protected void crearNuevo() {
        this.registro = new Vehiculo();
    }

}
