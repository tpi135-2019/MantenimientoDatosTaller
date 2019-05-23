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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmPersonal extends AbstractBean<Personal> implements Serializable {

    @Inject
    PersonalFacade personalFacade;
    @Inject
    SucursalFacade sucursalFacade;
    List<Sucursal> listaSucursal;
    @Inject
    EspecialidadFacade especialidadFacade;
    List<Especialidad> lsEspecialidad;
    
    public List listarESpecialidades(){
        try {
            return lsEspecialidad=especialidadFacade.findAll();
        } catch (Exception e) {
        return lsEspecialidad=Collections.EMPTY_LIST;
        }
    }

    @PostConstruct
    @Override
    protected void init() {
        super.init();
        listarSucursales();
        listarESpecialidades();
    }

    public List listarSucursales() {
        try {
            return listaSucursal = sucursalFacade.findAll();
        } catch (Exception e) {
            return listaSucursal = Collections.EMPTY_LIST;
        }
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
        this.registro = new Personal();
    }

    @Override
    protected Personal getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(p -> p.getIdMecanico().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);

        }
        return null;
    }

    @Override
    protected Object getKey(Personal entity) {
        if (entity != null) {
            return entity.getIdMecanico();

        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return personalFacade;
    }

    @Override
    protected Personal getEntity() {
        return this.registro;
    }

    public List<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(List<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }

    public List<Especialidad> getLsEspecialidad() {
        return lsEspecialidad;
    }

    public void setLsEspecialidad(List<Especialidad> lsEspecialidad) {
        this.lsEspecialidad = lsEspecialidad;
    }
    
    

}
