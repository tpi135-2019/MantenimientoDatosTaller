/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import edu.emory.mathcs.backport.java.util.Collections;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;
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
    ProcesoFacade procesoFacade;
    @Inject
    PersonalFacade personalFacade;
    private List<Diagnostico> listaDiagnostico;
    private List<Pieza> lspieza;
    private List<Paso> lspasoProceso;
    private List<Personal> lsPersonal;
    private List<Proceso> listaProceso;
    private Proceso proceso;

    public void listarPiezas() {
        try {
            lspieza = piezaFacade.findAll();
        } catch (Exception e) {
            lspieza = Collections.emptyList();
        }
    }

    public void listarPersonal() {
        try {
            lsPersonal = personalFacade.personalPorProceso(proceso.getIdProceso());
            System.out.println(proceso.toString());
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public void listarProcesos() {
        listaProceso = procesoFacade.findAll();
    }

    public void listarPasoProceso() {
        try {
            lspasoProceso = pasoProcesoFacade.pasosPorProceso(proceso.getIdProceso(), "");
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public void listar() {
        listarDiagnosticos();
        listarPiezas();
        listarPasoProceso();
        listarPersonal();
    }

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarProcesos();
        listar();
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

    public void listarDiagnosticos() {
        listaDiagnostico = diagnosticoFacade.findAll();
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

    public List<Paso> getLspasoProceso() {
        return lspasoProceso;
    }

    public void setLspasoProceso(List<Paso> lspasoProceso) {
        this.lspasoProceso = lspasoProceso;
    }

    public List<Personal> getLsPersonal() {
        return lsPersonal;
    }

    public void setLsPersonal(List<Personal> lsPersonal) {
        this.lsPersonal = lsPersonal;
    }

    public List<Proceso> getListaProceso() {
        return listaProceso;
    }

    public void setListaProceso(List<Proceso> listaProceso) {
        this.listaProceso = listaProceso;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

}
