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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmSucursal extends AbstractBean<Sucursal> implements Serializable{

    @Inject
    SucursalFacade sucursalFacade;
    
    
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
        this.registro= new Sucursal();
    }

    @Override
    protected Sucursal getrowD(String rowkey) {
        if(rowkey!=null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData()!=null){
            try {
                 for (Sucursal item :(List<Sucursal>) this.getLazyModel().getWrappedData()) {
                Integer resgistry= new Integer(rowkey);
                if(item.getIdSucursal().compareTo(resgistry)==0){
                    return item;
                }
            }
            } catch (NumberFormatException e) {
                
            }
           
    }
        return null;
    }

    @Override
    protected Object getKey(Sucursal entity) {
        try {
            return entity.getIdSucursal();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return sucursalFacade;
    }

    @Override
    protected Sucursal getEntity() {
        return this.registro;
    }

    public LazyDataModel<Sucursal> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Sucursal> lazyModel) {
        this.lazyModel = lazyModel;
    }
    
    
    
}
