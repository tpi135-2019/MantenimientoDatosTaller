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

    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    public void btnNuevoHandler() {
        estado = EstadosCRUD.NUEVO;
    }

    public void listarPartes() {
        try {
            listaParte = parteFacade.findAll();
        } catch (Exception e) {
            listaParte = Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new SubParte();
    }

    @Override
    protected SubParte getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            for (SubParte item : (List<SubParte>) this.getLazyModel().getWrappedData()) {
                Integer registry = new Integer(rowkey);
                if (item.getIdSubParte().compareTo(registry) == 0) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    protected Object getKey(SubParte entity) {
        try {
            return entity.getIdSubParte();
        } catch (Exception e) {
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

    public LazyDataModel<SubParte> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SubParte> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Integer getIdParteSeleccionada() {
        if (this.registro != null && this.registro.getIdParte()!= null) {
            return this.registro.getIdParte().getIdParte();
        }
        return null;
    }

    public void setIdParteSeleccionada(Integer idParte) {
        if (this.registro != null && this.listaParte != null) {
            try {
                this.registro.setIdParte(this.listaParte.stream().
                        filter(d -> d.getIdParte().compareTo(idParte) == 0)
                        .collect(Collectors.toList()).get(0));
            } catch (Exception e) {

            }
        }
    }
}
