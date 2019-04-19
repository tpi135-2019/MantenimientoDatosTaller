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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ParteFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmSubParte extends AbstractBean<SubParte> implements Serializable {

    @Inject
    SubParteFacade subparteFacade;
    @Inject
    ParteFacade parteFacade;
    List<Parte> listaParte;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarPartes();
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

    @Override
    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    @Override
    public void btnNuevoHandler() {
        estado = EstadosCRUD.NUEVO;
    }

    public List listarPartes() {
        try {
            return listaParte = parteFacade.findAll();
        } catch (Exception e) {
            return listaParte = Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new SubParte();
    }

    @Override
    protected SubParte getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(sp -> sp.getIdSubParte().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    protected Object getKey(SubParte entity) {
        if (entity != null) {
            return entity.getIdSubParte();
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return subparteFacade;
    }

    @Override
    protected SubParte getEntity() {
        return this.registro;
    }

    public List<Parte> getListaParte() {
        return listaParte;
    }

    public void setListaParte(List<Parte> listaParte) {
        this.listaParte = listaParte;
    }

}
