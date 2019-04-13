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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmPieza extends AbstractBean<Pieza> implements Serializable{

    @Inject
    PiezaFacade piezaFacade;
    @Inject
    SubParteFacade subparteFacade;
    List<SubParte> listaSubParte;
    
    @PostConstruct
    @Override
    protected void init() {
        super.init();
       listarSubpartes();
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
    
    
    public List listarSubpartes(){
        try {
            return listaSubParte=subparteFacade.findAll();
        } catch (Exception e) {
        return listaSubParte=Collections.EMPTY_LIST;
        }
    }
    
    @Override
    protected void crearNuevo() {
        this.registro= new Pieza();
    }

    @Override
    protected Pieza getrowD(String rowkey) {
        if(rowkey!=null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData()!=null){
            for (Pieza item : (List<Pieza>)this.getLazyModel().getWrappedData()) {
                Integer registry = new Integer(rowkey);
                if(item.getIdPieza().compareTo(registry)==0){
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    protected Object getKey(Pieza entity) {
     
            return entity.getIdPieza();
    
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return piezaFacade;
    }

    @Override
    protected Pieza getEntity() {
        return this.registro;
    }

    public List<SubParte> getListaSubParte() {
        return listaSubParte;
    }

    public void setListaSubParte(List<SubParte> listaSubParte) {
        this.listaSubParte = listaSubParte;
    }

    public LazyDataModel<Pieza> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Pieza> lazyModel) {
        this.lazyModel = lazyModel;
    }
    
    
}
