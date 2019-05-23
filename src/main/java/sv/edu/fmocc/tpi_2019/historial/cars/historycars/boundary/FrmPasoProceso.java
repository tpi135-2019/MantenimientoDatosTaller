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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmPasoProceso extends AbstractBean<PasoProceso> implements Serializable {

    @Inject
    PasoProcesoFacade pasoProcesoFacade;
    @Inject
    ProcesoFacade procesoFacade;
    private List<Proceso> listaProceso;
    @Inject
    PasoFacade pasofacade;
    private List<Paso> listaPaso;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarPasos();
        listarProcesos();

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

    public void listarPasos() {
        try {
             listaPaso = pasofacade.findAll();
        } catch (Exception e) {
             listaPaso = Collections.emptyList();
        }
    }

    public void listarProcesos() {
        try {
             listaProceso = procesoFacade.findAll();
        } catch (Exception e) {
             listaProceso = Collections.emptyList();
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new PasoProceso();
    }

    @Override
    protected PasoProceso getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {

            return this.getLazyModel().getWrappedData().stream().
                    filter(pP -> pP.getIdPasoProceso().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);

        }
        return null;
    }

    @Override
    protected Object getKey(PasoProceso entity) {
        if (entity != null) {
            return entity.getIdPasoProceso();

        }
        return null;

    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return pasoProcesoFacade;
    }

    @Override
    protected PasoProceso getEntity() {
        return this.registro;
    }

    public List<Proceso> getListaProceso() {
        return listaProceso;
    }

    public void setListaProceso(List<Proceso> listaProceso) {
        this.listaProceso = listaProceso;
    }

    public List<Paso> getListaPaso() {
        return listaPaso;
    }

    public void setListaPaso(List<Paso> listaPaso) {
        this.listaPaso = listaPaso;
    }

}
