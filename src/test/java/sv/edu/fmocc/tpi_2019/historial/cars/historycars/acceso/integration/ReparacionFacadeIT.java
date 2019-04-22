/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class ReparacionFacadeIT extends SessionBeanIT<Reparacion> {

    @Inject
    ReparacionFacade cut;
    @Inject
    DiagnosticoFacade diagnosticoFacade;
    List<Reparacion> datos = new ArrayList();

    public ReparacionFacadeIT()  {
        datos.add(new Reparacion(1, new Date(2018-06-19), "se repararon los frenos"));
        datos.add(new Reparacion(5, new Date(2019-03-5), "se reparo el escape"));
        datos.add(new Reparacion(1, new Date(2018-05-8), "se cambio el aceite todo good"));
    }
    
    @Before
    public void before(){
        datos.forEach(item -> item.setIdDiagnostico(diagnosticoFacade.find(2)));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List<Reparacion> getResgistros() {
        return datos;

    }

    @Override
    protected Object getId() {
        return entity.getIdReparacion();
    }

}
