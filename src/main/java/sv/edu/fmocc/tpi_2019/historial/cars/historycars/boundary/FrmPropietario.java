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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmPropietario extends AbstractBean<Propietario> implements Serializable {

    @Inject
    PropietarioFacade propietarioFacade;

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    /*
    sobreCarga de metodos !!
     */
    @Override
    public void crearNuevo() {
        this.registro = new Propietario();

    }

    @Override
    public void crear() {
        estado=EstadosCRUD.AGREGAR;
        super.crear(); 
    }

    @Override
    public void modificar() {
        estado=EstadosCRUD.EDITAR;
        super.modificar(); 
    }

    
    @Override
    public void eliminar() {
        estado=EstadosCRUD.ELIMINAR;
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
    protected Propietario getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            try {

                for (Propietario item : (List<Propietario>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (item.getIdPropietario().compareTo(registry) == 0) {
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
    protected Object getKey(Propietario entity) {
        return entity.getIdPropietario();
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return propietarioFacade;
    }

    @Override
    protected Propietario getEntity() {
        return this.registro;
    }
    
    public PropietarioFacade getPropietarioFacade() {
        return propietarioFacade;
    }

    public void setPropietarioFacade(PropietarioFacade propietarioFacade) {
        this.propietarioFacade = propietarioFacade;
    }

    public List<Propietario> getLista() {
        return lista;
    }

    public void setLista(List<Propietario> lista) {
        this.lista = lista;
    }

    public LazyDataModel<Propietario> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Propietario> lazyModel) {
        this.lazyModel = lazyModel;
    }

    @Override
    public Propietario getRegistro() {
        return registro;
    }

    @Override
    public EstadosCRUD getEstado() {
        return estado;
    }


}
