/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmEspecialidad extends AbstractBean<Especialidad> implements Serializable {

    @Inject
    EspecialidadFacade especialidadFacade;


    @Override
    public void btncancelarHandler() {
        estado = EstadosCRUD.NONE;
        crearNuevo();
    }

    @Override
    protected void crearNuevo() {
        this.registro = new Especialidad();
    }

    @Override
    protected Especialidad getrowD(String rowkey) {
        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel() != null) {

          return this.getLazyModel().getWrappedData().stream().
                    filter(e -> e.getIdEspecialidad().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
        }

        return null;
    }

    @Override
    protected Object getKey(Especialidad entity) {
        if (entity != null) {
            return entity.getIdEspecialidad();

        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return especialidadFacade;
    }

    @Override
    protected Especialidad getEntity() {
        return this.registro;
    }

}
