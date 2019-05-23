/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmPaso extends AbstractBean<Paso> implements Serializable {

    @Inject
    PasoFacade pasoFacade;

    @PostConstruct
    @Override
    protected void init() {
        super.init();

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
        this.registro = new Paso();
    }

    @Override
    protected Paso getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {

          return this.getLazyModel().getWrappedData().stream().
                    filter(p -> p.getIdPaso().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);

        }

        return null;
    }

    @Override
    protected Object getKey(Paso entity) {
        if (entity != null) {
            return entity.getIdPaso();
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return pasoFacade;
    }

    @Override
    protected Paso getEntity() {
        return this.registro;
    }

}
