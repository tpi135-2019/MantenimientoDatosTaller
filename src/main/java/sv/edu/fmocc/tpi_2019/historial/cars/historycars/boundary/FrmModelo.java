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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ModeloFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.TipoVehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Modelo;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.TipoVehiculo;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmModelo extends AbstractBean<Modelo> implements Serializable {

    @Inject
    protected ModeloFacade modeloFacade;
    @Inject
    protected MarcaFacade marcaFacade;
    private List<Marca> listaMarcas;
    @Inject
    protected TipoVehiculoFacade tipoVehiculoFacade;
    private List<TipoVehiculo> listaTipoVehiculo;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        listarMarcas();
        listarTiposdeVehiculo();
    }

    public void listarMarcas() {
        try {
            listaMarcas = marcaFacade.findAll();
        } catch (Exception e) {
            listaMarcas = Collections.emptyList();
        }
    }

    public void listarTiposdeVehiculo() {
        try {
            listaTipoVehiculo = tipoVehiculoFacade.findAll();
        } catch (Exception e) {
            listaTipoVehiculo = Collections.emptyList();

        }

    }


    /*
    sobreCarga de metodos !!
     */
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
        registro = new Modelo();
    }

    @Override
    protected Modelo getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(m -> m.getIdModelo().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    protected Object getKey(Modelo entity) {
        if (entity != null) {
            return entity.getIdModelo();

        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return modeloFacade;
    }

    public List<Marca> getListaMarcas() {
        return listaMarcas;
    }

    public void setListaMarcas(List<Marca> listaMarcas) {
        this.listaMarcas = listaMarcas;
    }

    public List<TipoVehiculo> getListaTipoVehiculo() {
        return listaTipoVehiculo;
    }

    public void setListaTipoVehiculo(List<TipoVehiculo> listaTipoVehiculo) {
        this.listaTipoVehiculo = listaTipoVehiculo;
    }

    @Override
    protected Modelo getEntity() {
        return this.registro;
    }

}
