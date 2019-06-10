/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PasoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmUtilidades implements Serializable {

    @Inject
    ReparacionFacade reparacionFacade;
    @Inject
    SucursalFacade sucursalFacade;
    @Inject
    DiagnosticoFacade diagnosticoFacade;
    @Inject
    PiezaFacade piezaFacade;
    @Inject
    PasoFacade pasoFacade;
    @Inject
    PropietarioFacade propietarioFacade;

    protected transient Logger logger = Logger.getGlobal();

    private List<Paso> listaPaso;
    private List<Pieza> listaPieza;
    private List<Reparacion> listaReparacion;
    private List<Diagnostico> listaDiagnostico;
    private List<Sucursal> listaSucursal;
    private List<Propietario> listaPropietario;

    String reparacionDiagnostico = "";
    String placaReparacion = "";
    String reparacionPersonal = "";
    String reparacionSucursal = "";
    String diagnosticoPlaca = "";
    String pieza = "";
    String pasoReparacion = "";
    String propietario = "";
    estadosTbl tbl;

    Date desde;
    Date hasta;

    protected enum estadosTbl {
        REPARACION, SUCURSAL, NONE, DIAGNOSTICO, PIEZA, PASO, PROPIETARIO;
    }

    //**** Manejo de imagenes que se desplazan en el inicio
    private List<String> images = new ArrayList<>();

    public void suffleImages() {
        for (int i = 1; i <= 3; i++) {
            images.add("image" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @PostConstruct
    public void init() {
        tbl = estadosTbl.NONE;
        suffleImages();
    }

    /**
     *
     * @return listado de reparaciones referentes a una placa
     */
    public List buscarPorPlaca() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();
        if (placaReparacion != null) {
            try {
                listaReparacion = reparacionFacade.reparacionesPorPlaca(placaReparacion.toLowerCase());
                return listaReparacion;
            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage());
            }
        }
        this.listaReparacion = Collections.emptyList();
        return this.listaReparacion;
    }

    /**
     *
     * @return listado de reparaciones referentes a un diagnostico
     */
    public List buscarReparacionesPorDiagnostico() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();
        if (reparacionDiagnostico != null) {

            try {
                listaReparacion = reparacionFacade.reparacionPorDiagnostico(new Integer(reparacionDiagnostico));
                return listaReparacion;
            } catch (NumberFormatException ex) {
                logger.log(Level.SEVERE, ex.getMessage());
            }
        }
        this.listaReparacion = Collections.emptyList();
        return this.listaReparacion;
    }

    /**
     *
     * @return lista de reparaciones en las que ha intervenido el Personal
     */
    public List buscarReparacionesPorPersonal() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();
        if (reparacionPersonal != null) {
            try {
                listaReparacion = reparacionFacade.reparacionPorPersonal(new Integer(reparacionPersonal));
                return listaReparacion;
            } catch (NumberFormatException ex) {
                logger.log(Level.SEVERE, ex.getMessage());

            }
        }
        this.listaReparacion = Collections.emptyList();
        return this.listaReparacion;
    }

    /**
     *
     * @return listado de reparaciones echas entre un intervalo de
     * tiempo(fechas)
     */
    public List buscarReparacionesEntreFechas() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();

        try {
            listaReparacion = reparacionFacade.reparacionEntreFechas(desde, hasta);
            return listaReparacion;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());

        }
        this.listaReparacion = Collections.emptyList();
        return this.listaReparacion;
    }

    /**
     *
     * @return listado de Sucursales referentes a una reparacion
     */
    public List buscarSucursalPorReparacion() {
        tbl = estadosTbl.SUCURSAL;
        listaSucursal = new ArrayList<>();
        if (reparacionSucursal != null) {
            try {
                listaSucursal = reparacionFacade.lugarReparacion(new Integer(reparacionSucursal));
                return listaSucursal;
            } catch (NumberFormatException ex) {
                logger.log(Level.SEVERE, ex.getMessage());

            }
        }
        listaSucursal = Collections.emptyList();
        return listaSucursal;
    }

    /**
     *
     * @return listado de diagnosticos referentes a una placaReparacion
     */
    public List buscarDiagnosticoPorPlaca() {

        tbl = estadosTbl.DIAGNOSTICO;
        listaDiagnostico = new ArrayList<>();
        if (diagnosticoPlaca != null) {
            try {

                listaDiagnostico = diagnosticoFacade.diagnosticoPorPlaca(diagnosticoPlaca);
                return listaDiagnostico;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

        }
        this.listaDiagnostico = Collections.emptyList();
        return this.listaDiagnostico;

    }

    /**
     *
     * @return listado de piezas ocupadas en una reparacion
     */
    public List buscarPiezasPorReparacion() {

        tbl = estadosTbl.PIEZA;
        listaPieza = new ArrayList<>();
        if (pieza != null) {
            try {
                listaPieza = piezaFacade.piezasReparacion(new Integer(pieza));
                return listaPieza;
            } catch (NumberFormatException ex) {
                logger.log(Level.SEVERE, ex.getMessage());

            }
        }
        this.listaPieza = Collections.emptyList();
        return this.listaPieza;
    }

    /**
     *
     * @return listado de pasos ocupadas realizados en una reparacion
     */
    public List buscarPasoPorReparacion() {

        tbl = estadosTbl.PASO;
        listaPaso = new ArrayList<>();
        if (pasoReparacion != null) {
            try {
                listaPaso = pasoFacade.pasoReparacion(new Integer(pasoReparacion));
                return listaPaso;
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        this.listaPaso = Collections.emptyList();
        return this.listaPaso;
    }

    /**
     *
     * @return lista con los propietarios que ha tenido un vehiculo
     */
    public List buscarHistorialDePropietarios() {
        tbl = estadosTbl.PROPIETARIO;
        listaPropietario = new ArrayList<>();
        if (propietario != null) {
            try {
                listaPropietario = propietarioFacade.historialPropietarios(propietario);
                return listaPropietario;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

        }
        this.listaPropietario = Collections.emptyList();
        return this.listaPropietario;
    }

    /////******getters y setters
    public String getReparacionSucursal() {
        return reparacionSucursal;
    }

    public void setReparacionSucursal(String rep) {
        this.reparacionSucursal = rep;
    }

    public String getReparacionDiagnostico() {
        return reparacionDiagnostico;
    }

    public void setReparacionDiagnostico(String salect) {
        this.reparacionDiagnostico = salect;
    }

    public List<Reparacion> getListaReparacion() {
        return listaReparacion;
    }

    public void setListaReparacion(List<Reparacion> listaReparacion) {
        this.listaReparacion = listaReparacion;
    }

    public String getPlacaReparacion() {
        return placaReparacion;
    }

    public estadosTbl getTbl() {
        return tbl;
    }

    public void setPlacaReparacion(String placaReparacion) {
        this.placaReparacion = placaReparacion;
    }

    public String getReparacionPersonal() {
        return reparacionPersonal;
    }

    public void setReparacionPersonal(String reparacionPersonal) {
        this.reparacionPersonal = reparacionPersonal;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public String getDiagnosticoPlaca() {
        return diagnosticoPlaca;
    }

    public void setDiagnosticoPlaca(String diagnos) {
        this.diagnosticoPlaca = diagnos;
    }

    public List<Diagnostico> getListaDiagnostico() {
        return listaDiagnostico;
    }

    public void setListaDiagnostico(List<Diagnostico> listaDiagnostico) {
        this.listaDiagnostico = listaDiagnostico;
    }

    public List<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(List<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }

    public List<Paso> getListaPaso() {
        return listaPaso;
    }

    public void setListaPaso(List<Paso> listaPaso) {
        this.listaPaso = listaPaso;
    }

    public List<Pieza> getListaPieza() {
        return listaPieza;
    }

    public void setListaPieza(List<Pieza> listaPieza) {
        this.listaPieza = listaPieza;
    }

    public String getPieza() {
        return pieza;
    }

    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    public String getPasoReparacion() {
        return pasoReparacion;
    }

    public void setPasoReparacion(String paso) {
        this.pasoReparacion = paso;
    }

    public List<Propietario> getListaPropietario() {
        return listaPropietario;
    }

    public void setListaPropietario(List<Propietario> listaPropietario) {
        this.listaPropietario = listaPropietario;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
