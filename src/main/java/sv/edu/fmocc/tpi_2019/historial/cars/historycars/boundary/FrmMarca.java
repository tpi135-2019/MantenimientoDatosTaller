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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmMarca extends AbstractBean<Marca> implements Serializable{

    @Inject
    MarcaFacade marcaFacade;
    
    @PostConstruct
    @Override
    public void init(){
        super.init();
    }

    @Override
    public void crear() {
    estado =EstadosCRUD.AGREGAR;
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
    protected void crearNuevo() {
        this.registro= new Marca();
    }

    @Override
    protected Marca getrowD(String rowkey) {
        
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
          

                for (Marca item : (List<Marca>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (item.getIdMarca().compareTo(registry) == 0) {
                        return item;
                    }

                }

          
        }

        return null;
    }

    @Override
    protected Object getKey(Marca entity) {
        return entity.getIdMarca();
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return marcaFacade;
    }

    @Override
    protected Marca getEntity() {
        return this.registro;
    }
    
    
    // getters y setters

    public void setLazyModel(LazyDataModel<Marca> lazyModel) {
        this.lazyModel = lazyModel;
    }

    
}
