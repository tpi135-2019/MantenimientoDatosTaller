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

    List<Paso> listaPaso;
    List<Pieza> listaPieza;
    List<Reparacion> listaReparacion;
    List<Diagnostico> listaDiagnostico;
    List<Sucursal> listaSucursal;
    List<Propietario> listaPropietario;

    String select = "", placa = "", person = "", rep = "", diagnos = "", pieza = "", paso = "", propietario = "";
    estadosTbl tbl;
    Date desde, hasta;

    private enum estadosTbl {
        REPARACION, SUCURSAL, NONE, DIAGNOSTICO, PIEZA, PASO,PROPIETARIO;
    }

    @PostConstruct
    public void init() {
        tbl = estadosTbl.NONE;

    }

    /**
     *
     * @return listado de reparaciones referentes a una placa
     */
    public List buscarPorPlaca() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();
        if (select != null) {
            try {
                List<Reparacion> ls = reparacionFacade.reparacionesPorPlaca(placa.toLowerCase());
                ls.forEach((item) -> {
                    listaReparacion.add(item);
                });

                if (listaReparacion != null && !listaReparacion.isEmpty()) {
                    return listaReparacion;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return this.listaReparacion = Collections.EMPTY_LIST;
    }

    /**
     *
     * @return listado de reparaciones referentes a un diagnostico
     */
    public List buscarReparacionesPorDiagnostico() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();
        if (select != null) {
            try {
                List<Reparacion> ls = reparacionFacade.reparacionPorDiagnostico(new Integer(select));
                ls.forEach((item) -> {
                    listaReparacion.add(item);
                });

                if (listaReparacion != null && !listaReparacion.isEmpty()) {
                    return listaReparacion;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return this.listaReparacion = Collections.EMPTY_LIST;
    }

    /**
     *
     * @return lista de reparaciones en las que ha intervenido el personal
     */
    public List buscarReparacionesPorPersonal() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();
        if (select != null) {
            try {
                List<Reparacion> ls = reparacionFacade.reparacionPorPersonal(new Integer(person));
                ls.forEach((item) -> {
                    listaReparacion.add(item);
                });

                if (listaReparacion != null && !listaReparacion.isEmpty()) {
                    return listaReparacion;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return this.listaReparacion = Collections.EMPTY_LIST;
    }

    /**
     *
     * @return listado de reparaciones echas entre un intervalo de
     * tiempo(fechas)
     */
    public List buscarReparacionesEntreFechas() {
        tbl = estadosTbl.REPARACION;
        listaReparacion = new ArrayList<>();
        if (select != null) {
            try {
                List<Reparacion> ls = reparacionFacade.reparacionEntreFechas(desde, hasta);
                ls.forEach((item) -> {
                    listaReparacion.add(item);
                });

                if (listaReparacion != null && !listaReparacion.isEmpty()) {
                    return listaReparacion;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return this.listaReparacion = Collections.EMPTY_LIST;
    }

    /**
     *
     * @return listado de Sucursales referentes a una reparacion
     */
    public List buscarSucursalPorReparacion() {
        tbl = estadosTbl.SUCURSAL;
        listaSucursal = new ArrayList<>();
        if (rep != null) {
            try {
                List<Sucursal> ls = sucursalFacade.lugarReparacion(new Integer(rep));
                ls.forEach((item) -> {
                    listaSucursal.add(item);
                });

                if (listaSucursal != null && !listaSucursal.isEmpty()) {
                    return listaSucursal;
                }

            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return listaSucursal = Collections.EMPTY_LIST;
    }

    /**
     *
     * @return listado de diagnosticos referentes a una placa
     */
    public List buscarDiagnosticoPorPlaca() {

        tbl = estadosTbl.DIAGNOSTICO;
        listaDiagnostico = new ArrayList<>();
        if (diagnos != null) {
            try {
                List<Diagnostico> ls = diagnosticoFacade.diagnosticoPorPlaca(diagnos);
                ls.forEach((item) -> {
                    listaDiagnostico.add(item);
                });

                if (listaDiagnostico != null && !listaDiagnostico.isEmpty()) {
                    return listaDiagnostico;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return this.listaDiagnostico = Collections.EMPTY_LIST;
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
                List<Pieza> ls = piezaFacade.piezasReparacion(new Integer(pieza));
                ls.forEach((item) -> {
                    listaPieza.add(item);
                });

                if (listaPieza != null && !listaPieza.isEmpty()) {
                    return listaPieza;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return this.listaPieza = Collections.EMPTY_LIST;
    }

    /**
     *
     * @return listado de pasos ocupadas realizados en una reparacion
     */
    public List buscarPasoPorReparacion() {

        tbl = estadosTbl.PASO;
        listaPaso = new ArrayList<>();
        if (paso != null) {
            try {
                List<Paso> ls = pasoFacade.pasoReparacion(new Integer(paso));
                ls.forEach((item) -> {
                    listaPaso.add(item);
                });

                if (listaPaso != null && !listaPaso.isEmpty()) {
                    return listaPaso;
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return this.listaPaso = Collections.EMPTY_LIST;
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
                return listaPropietario = propietarioFacade.historialPropietarios(propietario);
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);

            }

        }

        return this.listaPropietario = Collections.EMPTY_LIST;
    }

    /////******getters y setters
    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String salect) {
        this.select = salect;
    }

    public List<Reparacion> getListaReparacion() {
        return listaReparacion;
    }

    public void setListaReparacion(List<Reparacion> listaReparacion) {
        this.listaReparacion = listaReparacion;
    }

    public estadosTbl getTbl() {
        return tbl;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
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

    public String getPaso() {
        return paso;
    }

    public void setPaso(String paso) {
        this.paso = paso;
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

}
