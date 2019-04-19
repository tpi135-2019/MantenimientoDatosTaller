/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;

/**
 *
 * @author kevin
 */
@Named
@ViewScoped
public class FrmMarca extends AbstractBean<Marca> implements Serializable {

    @Inject
    MarcaFacade marcaFacade;

    @PostConstruct
    @Override
    public void init() {
        super.init();
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
        this.registro = new Marca();
    }

    @Override
    protected Marca getrowD(String rowkey) {

        if (rowkey != null && !rowkey.isEmpty() && this.getLazyModel().getWrappedData() != null) {
            return this.getLazyModel().getWrappedData().stream().
                    filter(m -> m.getIdMarca().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);

        }

        return null;
    }

    @Override
    protected Object getKey(Marca entity) {
        if (entity != null) {
            return entity.getIdMarca();
        }
        return null;
    }

    @Override
    protected FacadeGenerico getFacadeLocal() {
        return marcaFacade;
    }

    @Override
    protected Marca getEntity() {
        return this.registro;
    }

    // getters y setters
}
