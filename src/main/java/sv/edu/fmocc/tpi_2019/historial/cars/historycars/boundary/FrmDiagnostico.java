/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import edu.emory.mathcs.backport.java.util.Collections;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    
    private List<Vehiculo> listaVehiculo= new ArrayList<>();

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarVehiculos();
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

    public void listarVehiculos() {
        try {
            listaVehiculo= vehiculoFacade.findAll();
        } catch (Exception e) {
            listaVehiculo=Collections.emptyList();
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new Diagnostico();
    }

    @Override
    protected Diagnostico getrowD(String rowkey) {

        if (rowkey != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(d -> d.getIdDiagnostico().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }

        return null;
    }

    @Override
    protected Object getKey(Diagnostico entity) {
        if (entity != null) {
            return entity.getIdDiagnostico();

        }
        return null;
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

}
