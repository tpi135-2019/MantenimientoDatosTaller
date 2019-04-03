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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmPersonal extends AbstractBean<Personal> implements Serializable{
    
    
    @Inject
    PersonalFacade personalFacade;
    @Inject
    SucursalFacade sucursalFacade;
    List<Sucursal> listaSucursal;
    
    
    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarSucursales();
    }

    public void listarSucursales(){
        try {
            listaSucursal=sucursalFacade.findAll();
        } catch (Exception e) {
            listaSucursal=Collections.EMPTY_LIST;
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
    protected void crearNuevo() {
        this.registro= new Personal();
    }

    @Override
    protected Personal getrowD(String rowkey) {
        if(rowkey!=null && !rowkey.isEmpty() &&  this.getLazyModel().getWrappedData()!=null){
            try {
                for (Personal item : (List<Personal>)this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if(item.getIdMecanico().compareTo(registry)==0){
                        return item;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    protected Object getKey(Personal entity) {
        try {
            return entity.getIdMecanico();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return personalFacade;
    }

    @Override
    protected Personal getEntity() {
        return this.registro;
    }

    public LazyDataModel<Personal> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Personal> lazyModel) {
        this.lazyModel = lazyModel;
    }
    
    public Integer getIdSucursalSeleccionada(){
        if(this.registro!=null && this.registro.getIdSucursal()!=null){
            return this.registro.getIdSucursal().getIdSucursal();
        }
        return null;
    }
  
    public void setIdSucursalSeleccionada(Integer idSucursal){
        if(this.registro!=null && listaSucursal!=null){
            this.registro.setIdSucursal(this.listaSucursal.stream()
                    .filter(s->s.getIdSucursal().compareTo(idSucursal)==0).collect(Collectors.toList()).get(0));
        }
    }

    public List<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(List<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }
    
    
    
    
}