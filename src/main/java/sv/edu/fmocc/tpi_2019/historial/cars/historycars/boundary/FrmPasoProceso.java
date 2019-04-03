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
    List<Proceso> listaProceso;
    @Inject
    PasoFacade pasofacade;
    List<Paso> listaPaso;

    
      @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarPasos();
        listarProcesos();

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
    
    public void listarPasos(){
        try {
            listaPaso=pasofacade.findAll();
        } catch (Exception e) {
            listaPaso=Collections.EMPTY_LIST;
        }
    }
    public void listarProcesos(){
        try {
            listaProceso=procesoFacade.findAll();
        } catch (Exception e) {
            listaProceso=Collections.EMPTY_LIST;
        }
    }
    
    @Override
    protected void crearNuevo() {
        this.registro = new PasoProceso();
    }

    @Override
    protected PasoProceso getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            try {
                for (PasoProceso pp : (List<PasoProceso>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (pp.getIdPasoProceso().compareTo(registry) == 0) {
                        return pp;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    protected Object getKey(PasoProceso entity) {
        try {
            return entity.getIdPasoProceso();
        } catch (Exception e) {
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

    public LazyDataModel<PasoProceso> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<PasoProceso> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Integer getIdProcesoSeleccionado() {
        if (this.registro != null && this.registro.getIdProceso() != null) {
            return this.registro.getIdProceso().getIdProceso();
        }
        return null;
    }

    public void setIdProcesoSeleccionado(Integer idProc) {
        if (this.registro != null && this.listaProceso != null) {
            this.registro.setIdProceso(this.listaProceso.stream()
                    .filter(p -> p.getIdProceso().compareTo(idProc) == 0).collect(Collectors.toList()).get(0));
        }
    }

    public Integer getIdPasoSeleccionado() {
        if (this.registro != null && this.registro.getIdPaso() != null) {
            return this.registro.getIdPaso().getIdPaso();
        }
        return null;
    }

    public void setIdPasoSeleccionado(Integer idPaso) {
        if (this.registro != null && this.listaPaso != null) {
            this.registro.setIdPaso(this.listaPaso.stream()
                    .filter(p -> p.getIdPaso().compareTo(idPaso) == 0).collect(Collectors.toList()).get(0));
        }
    }
}
