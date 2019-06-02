/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PiezaFacadeTest extends SessionBeanTest<Pieza> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private PiezaFacade cut;
    private Pieza pieza = new Pieza(1, "piston");
    private List<Pieza> registrosEsperados = new ArrayList<>();

    public PiezaFacadeTest() {
        super(Pieza.class);
        registrosEsperados.add(new Pieza(1, "biela"));
        registrosEsperados.add(new Pieza(2, "cadena de tiempo"));

    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Pieza getEntity() {
        return pieza;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Pieza> getLista() {
        return registrosEsperados;
    }

    @Test
    public void piezaReparacionTest() {
        System.out.println("piezaPorReparacion");
        //SETUP
        int reparacion = 1;
        Mockito.when(getEntityManager().createNamedQuery("Pieza.Reparacion")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);

        //TODO GOOD
        List ls = cut.piezasReparacion(reparacion);
        Assert.assertEquals(registrosEsperados.size(), ls.size());

        //ALGO MENOR
        ls = cut.piezasReparacion(-1);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());

        //EXEPTION
        setEmNull();
        cut.piezasReparacion(reparacion);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void piezasPorSubParteTest() {
        System.out.println("PIEZA POR SUBPARTE");
        //SETUP
        int idpieza = 1;
        String nombre = "tornillo";
        Mockito.when(getEntityManager().createNamedQuery("Pieza.findBySubarteNombreLike")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);

        //TODO GOOD
        List ls = cut.piezasPorSubParte(idpieza, nombre);
        Assert.assertEquals(registrosEsperados.size(), ls.size());

        //ALGO MENOR 
        ls = cut.piezasPorSubParte(-1, nombre);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());

        //EXCEPTION
        setEmNull();
        cut.piezasPorSubParte(idpieza, nombre);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

}
