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
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;

/**
 *
 * @author kevin
 */
//@RunWith(Arquillian.class)
//public class DiagnosticoFacadeIT extends SessionBeanIT<Diagnostico> {
//
//    @Inject
//    DiagnosticoFacade cut;
//    List<Diagnostico> datos = new ArrayList<>();
//
//    @Override
//    protected FacadeGenerico getSessionBean() {
//        return cut;
//    }
//
//    @Override
//    protected Diagnostico getEntity() {
//        return new Diagnostico(1, "se jodio", new Date(), "P123454");
//    }
//
//    @Override
//    protected List getResgistros() {
//        datos.add(new Diagnostico(2, "no sirve", new Date(), "P789654"));
//        datos.add(new Diagnostico(2, "no sirve x2", new Date(), "P028515"));
//        return datos;
//    }
//
//    @Override
//    protected Class getEntityClass() {
//        return Diagnostico.class;
//    }
//
//}
