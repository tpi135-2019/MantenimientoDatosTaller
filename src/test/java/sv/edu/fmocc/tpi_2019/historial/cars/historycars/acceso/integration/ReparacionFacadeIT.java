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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests.ReparacionFacadeTest;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

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
    @Inject
    PiezaFacade piezaFacade;
    List<Reparacion> datos = new ArrayList();
    List<Pieza> piezas;
    @PersistenceContext(unitName = "PU_talleres")
    EntityManager em;

    public ReparacionFacadeIT() {
        datos.add(new Reparacion(1, new Date(2018 - 06 - 19), "se repararon los frenos"));
        datos.add(new Reparacion(5, new Date(2019 - 03 - 5), "se reparo el escape"));
        datos.add(new Reparacion(1, new Date(2018 - 05 - 8), "se cambio el aceite todo good"));
    }

    @Before
    public void before() {
        piezas = piezaFacade.findAll();
        datos.forEach(item -> {
            item.setIdDiagnostico(diagnosticoFacade.find(2));
        });
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

    //@Test
    public void algo() {
        Pieza pieza = piezas.get(0);
        entidadNueva.setPiezaCollection(piezas);
        cut.create(entidadNueva);
        //pieza.setReparacionCollection(cut.findAll());
        //piezaFacade.create(pieza);
        List lista = em.createQuery("select p.idPieza, t.idReparacion from Reparacion t INNER JOIN t.piezaCollection p").getResultList();
        System.out.println("");
        System.out.println("");

    }

    @Test
    public void testReparacionesPorPlaca() {
        List<Reparacion> resultados = cut.reparacionesPorPlaca("P365428");
        Assert.assertEquals(2, resultados.size());
    }

    @Test
    public void testReparacionPorDiagnostico() {
        List<Reparacion> resultados = cut.reparacionPorDiagnostico(2);
        Assert.assertEquals(2, resultados.size());
    }

    @Test
    public void testReparacionPorPersonal() {
        List<Reparacion> resultados = cut.reparacionPorPersonal(1);
        Assert.assertEquals(2, resultados.size());
    }

    @Test
    public void testReparacionEntreFechas() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date desde = null, hasta = null;
        try {
            desde = dateFormat.parse("2017-02-03");
            hasta = dateFormat.parse("2019-02-03");
        } catch (ParseException ex) {
            Logger.getLogger(ReparacionFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Reparacion> resultados = cut.reparacionEntreFechas(desde, hasta);
        Assert.assertEquals(1, resultados.size());
    }
    
      @Test
    public void tallerReparacionTest() {
        List<Sucursal> resultados = cut.lugarReparacion(1);
        Assert.assertEquals(1, resultados.size());

    }

}
