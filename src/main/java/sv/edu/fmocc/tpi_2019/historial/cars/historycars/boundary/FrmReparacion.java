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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmReparacion extends AbstractBean<Reparacion> implements Serializable {

    @Inject
    ReparacionFacade reparacionFacade;
    @Inject
    DiagnosticoFacade diagnosticoFacade;
    List<Diagnostico> listaDiagnostico;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarDiagnosticos();
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

    @Override
    protected void crearNuevo() {
        this.registro = new Reparacion();
    }

    public void listarDiagnosticos() {
        try {
            listaDiagnostico = diagnosticoFacade.findAll();
        } catch (Exception e) {
            listaDiagnostico = Collections.EMPTY_LIST;
        }
    }

    @Override
    protected Reparacion getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData()!=null) {
            try {

                for (Reparacion item : (List<Reparacion>) this.getLazyModel().getWrappedData()) {
                    Integer registry = new Integer(rowkey);
                    if (item.getIdReparacion().compareTo(registry) == 0) {
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
    protected Object getKey(Reparacion entity) {
        try {
            return entity.getIdReparacion();

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return reparacionFacade;
    }

    @Override
    protected Reparacion getEntity() {
        return this.registro;
    }

    public Integer getIdDiagnosticoSeleccionado() {
        if (this.registro != null && this.registro.getIdDiagnostico() != null) {
            return this.registro.getIdDiagnostico().getIdDiagnostico();
        }
        return null;
    }

    public void setIdDiagnosticoSeleccionado(Integer idDiagnostico) {
        if (this.registro != null && this.listaDiagnostico != null) {
            try {
                this.registro.setIdDiagnostico(this.listaDiagnostico.stream().
                        filter(d -> d.getIdDiagnostico().compareTo(idDiagnostico) == 0)
                        .collect(Collectors.toList()).get(0));
            } catch (Exception e) {

            }
        }
    }

    public ReparacionFacade getReparacionFacade() {
        return reparacionFacade;
    }

    public void setReparacionFacade(ReparacionFacade reparacionFacade) {
        this.reparacionFacade = reparacionFacade;
    }

    public DiagnosticoFacade getDiagnosticoFacade() {
        return diagnosticoFacade;
    }

    public void setDiagnosticoFacade(DiagnosticoFacade diagnosticoFacade) {
        this.diagnosticoFacade = diagnosticoFacade;
    }

    public List<Diagnostico> getListaDiagnostico() {
        return listaDiagnostico;
    }

    public void setListaDiagnostico(List<Diagnostico> listaDiagnostico) {
        this.listaDiagnostico = listaDiagnostico;
    }

    public List<Reparacion> getLista() {
        return lista;
    }

    public void setLista(List<Reparacion> lista) {
        this.lista = lista;
    }

    public LazyDataModel<Reparacion> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Reparacion> lazyModel) {
        this.lazyModel = lazyModel;
    }



    
}
