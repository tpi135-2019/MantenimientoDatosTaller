/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;

/**
 *
 * @author kevin
 */
@RunWith(Arquillian.class)
public class DiagnosticoFacadeIT extends SessionBeanIT<Diagnostico> {

    @Inject
    DiagnosticoFacade cut;
    @Inject
    VehiculoFacade vehiculoFacade;
    List<Diagnostico> datos = new ArrayList<>();

    public DiagnosticoFacadeIT() {
        datos.add(new Diagnostico(1, "se murio don yon", new Date(2018-03-16)));
        datos.add(new Diagnostico(5, "no furula el carro", new Date(2019-02-05)));
        datos.add(new Diagnostico(1, "no sirve algo random", new Date(2018-01-16)));
        this.idNuevo = 5;
    }
    
    @Before
    public void before(){
        datos.forEach(diagnostico -> diagnostico.setIdVehiculo(vehiculoFacade.find("P365428")));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected List getResgistros() {
        return datos;
    }

    @Override
    protected Object getId() {
        return entity.getIdDiagnostico();
    }
    
    @Test
    public void testDiagnosticoPorPlaca(){
        List<Diagnostico> resultado = cut.diagnosticoPorPlaca("P365428");
        Assert.assertEquals(2, resultado.size());
    }

}
