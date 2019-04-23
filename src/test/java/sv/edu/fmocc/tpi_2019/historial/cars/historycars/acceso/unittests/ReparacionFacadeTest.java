/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ReparacionFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Reparacion;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class ReparacionFacadeTest extends SessionBeanTest<Reparacion> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private ReparacionFacade cut;
    private Reparacion reparacion = new Reparacion(1);
    private List<Reparacion> registrosEsperados = new ArrayList<>();

    public ReparacionFacadeTest() {
        super(Reparacion.class);
        registrosEsperados.add(new Reparacion(1));
        registrosEsperados.add(new Reparacion(2));
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Reparacion> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Reparacion getEntity() {
        return reparacion;
    }

    @Test
    public void reparacionesPlacaTest() {
        System.out.println("ReparacionesPorPlaca");
        String placa = "P234-345";
        mockList("ReparacionPorPlaca");
        List mylist = cut.reparacionesPorPlaca(placa);
        Assert.assertEquals(registrosEsperados.size(), mylist.size());
        mylist = cut.reparacionesPorPlaca(null);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), mylist.size());
        setEmNull();
        cut.reparacionesPorPlaca(placa);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void reparacionDiagnosticoTest() {
        System.out.println("ReparacionPorDiagnostico");
        int diagnostico = 1;
        mockList("Reparacion.Diagnostico");
        List lista = cut.reparacionPorDiagnostico(diagnostico);
        Assert.assertEquals(registrosEsperados.size(), lista.size());
        lista = cut.reparacionPorDiagnostico(-1);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
        setEmNull();
        cut.reparacionPorDiagnostico(diagnostico);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

    @Test
    public void reparacionPorPersonalTest() {
        System.out.println("reparacionPorPersonal");
        int idPersonal = 1;
        mockList("Reaparacion.Personal");
        List lista = cut.reparacionPorPersonal(idPersonal);
        Assert.assertEquals(registrosEsperados.size(), lista.size());
        lista = cut.reparacionPorPersonal(-1);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
        setEmNull();
        cut.reparacionPorPersonal(idPersonal);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    // Revisar uso de try catch (a mi me parece bien xd)
    @Test
    public void reparacionFechasTest() {
        System.out.println("reparacion por fechas ");
        mockList("Reparacion.Fechas");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date desde = null, hasta = null;
        try {
            desde = dateFormat.parse("2000-02-03");
            hasta = dateFormat.parse("2019-11-03");
        } catch (ParseException ex) {
            Logger.getLogger(ReparacionFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        List lista = cut.reparacionEntreFechas(desde, hasta);
        Assert.assertEquals(registrosEsperados.size(), lista.size());
        lista = cut.reparacionEntreFechas(null, null);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
        setEmNull();
        cut.reparacionEntreFechas(desde, hasta);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    private void mockList(String nameQuery) {
        Mockito.when(getEntityManager().createNamedQuery(nameQuery))
                .thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);

    }
}
