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
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.MarcaFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class MarcaFacadeIT extends SessionBeanIT<Marca> {

    @Inject
    MarcaFacade cut;
    List<Marca> datos = new ArrayList();

    public MarcaFacadeIT() {
        datos.add(new Marca(1, "toyota"));
        datos.add(new Marca(5, "nissan"));
        datos.add(new Marca(1, "mazda"));
        this.idNuevo = 5;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Marca> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdMarca();
    }

}
