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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmDiagnostico extends AbstractBean<Diagnostico> implements Serializable {

    @Inject
    DiagnosticoFacade diagnosticoFacade;
    @Inject
    VehiculoFacade vehiculoFacade;
    List<Vehiculo> listaVehiculo;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarVehiculos();
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

    public List listarVehiculos() {
        try {
            return listaVehiculo = vehiculoFacade.findAll();
        } catch (Exception e) {
            return listaVehiculo = Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new Diagnostico();
    }

    @Override
    protected Diagnostico getrowD(String rowkey) {
        
        if (rowkey != null) {
            try {

                for (Diagnostico item : (List<Diagnostico>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (item.getIdDiagnostico().compareTo(registry) == 0) {
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
    protected Object getKey(Diagnostico entity) {
            return entity.getIdDiagnostico();
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return diagnosticoFacade;
    }

    @Override
    protected Diagnostico getEntity() {
        return this.registro;
    }

    public List<Vehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    public void setListaVehiculo(List<Vehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    public LazyDataModel<Diagnostico> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Diagnostico> lazyModel) {
        this.lazyModel = lazyModel;
    }
    
    

}
