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
public class FrmPieza extends AbstractBean<Pieza> implements Serializable {

    @Inject
    PiezaFacade piezaFacade;
    @Inject
    SubParteFacade subparteFacade;
    private List<SubParte> listaSubParte;

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

    @Override
    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    @Override
    public void btnNuevoHandler() {
        estado = EstadosCRUD.NUEVO;
    }

    public void listarSubpartes() {
        try {
            listaSubParte = subparteFacade.findAll();
        } catch (Exception e) {
            listaSubParte = Collections.emptyList();
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new Pieza();
    }

    @Override
    protected Pieza getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(p -> p.getIdPieza().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    protected Object getKey(Pieza entity) {
        if (entity != null) {
            return entity.getIdPieza();
        }
        return null;
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

}
