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
    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    @Override
    public void btnNuevoHandler() {
        estado = EstadosCRUD.NUEVO;
    }

    @Override
    protected Propietario getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(p -> p.getIdPropietario().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }

        return null;

    }

    @Override
    protected Object getKey(Propietario entity) {
        if (entity != null) {
            return entity.getIdPropietario();
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return propietarioFacade;
    }

    @Override
    protected Propietario getEntity() {
        return this.registro;
    }
    
}
