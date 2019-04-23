/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.PasoProceso;
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
    @Inject
    PiezaFacade piezaFacade;
    @Inject
    PasoProcesoFacade pasoProcesoFacade;
    @Inject
    PersonalFacade personalFacade;
    List<Diagnostico> listaDiagnostico;
    List<Pieza> lspieza;
    List<PasoProceso> lspasoProceso;
    List<Personal> lsPersonal;
  

    public List listarPiezas() {
        try {
            return lspieza =piezaFacade.findAll();
        } catch (Exception e) {
            return lspieza=Collections.EMPTY_LIST;
        }
    }
     public List listarPersonal() {
        try {
            return lsPersonal =personalFacade.findAll();
        } catch (Exception e) {
            return lsPersonal=Collections.EMPTY_LIST;
        }
    }
      public List listarPasoProceso() {
        try {
            return lspasoProceso =pasoProcesoFacade.findAll();
        } catch (Exception e) {
            return lspasoProceso=Collections.EMPTY_LIST;
        }
    }

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarDiagnosticos();
        listarPiezas();
        listarPasoProceso();
        listarPersonal();
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

    @Override
    protected void crearNuevo() {
        this.registro = new Reparacion();
    }

    public List listarDiagnosticos() {
        try {
            return listaDiagnostico = diagnosticoFacade.findAll();
        } catch (Exception e) {
            return listaDiagnostico = Collections.EMPTY_LIST;
        }
    }

    @Override
    protected Reparacion getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(r -> r.getIdReparacion().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }

        return null;
    }

    @Override
    protected Object getKey(Reparacion entity) {
        if (entity != null) {
            return entity.getIdReparacion();
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

    public List<Diagnostico> getListaDiagnostico() {
        return listaDiagnostico;
    }

    public void setListaDiagnostico(List<Diagnostico> listaDiagnostico) {
        this.listaDiagnostico = listaDiagnostico;
    }

    public List<Pieza> getLspieza() {
        return lspieza;
    }

    public void setLspieza(List<Pieza> lspieza) {
        this.lspieza = lspieza;
    }

    public List<PasoProceso> getLspasoProceso() {
        return lspasoProceso;
    }

    public void setLspasoProceso(List<PasoProceso> lspasoProceso) {
        this.lspasoProceso = lspasoProceso;
    }

    public List<Personal> getLsPersonal() {
        return lsPersonal;
    }

    public void setLsPersonal(List<Personal> lsPersonal) {
        this.lsPersonal = lsPersonal;
    }
    

}
