/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmParte extends AbstractBean<Parte> implements Serializable {

    @Inject
    ParteFacade parteFacade;

    @Override
    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    @Override
    protected void crearNuevo() {
        this.registro = new Parte();
    }

    @Override
    protected Parte getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(p -> p.getIdParte().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    protected Object getKey(Parte entity) {
        if (entity != null) {
            return entity.getIdParte();
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return parteFacade;
    }

    @Override
    protected Parte getEntity() {
        return this.registro;
    }

}
