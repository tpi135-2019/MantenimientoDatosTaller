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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmProceso extends AbstractBean<Proceso> implements Serializable {

    @Inject
    ProcesoFacade procesoFacade;
    @Inject
    EspecialidadFacade especialidadFacade;
    private List<Especialidad> listaEspecialidad;

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarEspecialidades();
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

    public void listarEspecialidades() {
        try {
            listaEspecialidad = especialidadFacade.findAll();
        } catch (Exception e) {
            listaEspecialidad = Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void crearNuevo() {
        this.registro = new Proceso();
    }

    @Override
    protected Proceso getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {

            return this.getLazyModel().getWrappedData().stream().
                    filter(p -> p.getIdProceso().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);

        }

        return null;
    }

    @Override
    protected Object getKey(Proceso entity) {
        if (entity != null) {
            return entity.getIdProceso();
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return procesoFacade;
    }

    @Override
    protected Proceso getEntity() {
        return this.registro;
    }

    public List<Especialidad> getListaEspecialidad() {
        return listaEspecialidad;
    }

    public void setListaEspecialidad(List<Especialidad> listaEspecialidad) {
        this.listaEspecialidad = listaEspecialidad;
    }

}
