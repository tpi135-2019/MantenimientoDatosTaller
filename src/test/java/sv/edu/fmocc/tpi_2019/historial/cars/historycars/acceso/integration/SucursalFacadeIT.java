/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class SucursalFacadeIT extends SessionBeanIT<Sucursal> {

    @Inject
    SucursalFacade cut;
    List<Sucursal> datos = new ArrayList();

    public SucursalFacadeIT() {
        datos.add(new Sucursal(1, "los chepes"));
        datos.add(new Sucursal(5, "pedro y pablo"));
        datos.add(new Sucursal(1, "mi chuchito"));

    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Sucursal> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdSucursal();
    }


}
