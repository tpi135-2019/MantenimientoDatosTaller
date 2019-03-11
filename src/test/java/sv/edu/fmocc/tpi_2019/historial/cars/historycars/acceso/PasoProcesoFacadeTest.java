/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.PasoProceso;

/**
 *
 * @author kevin
 */
public class PasoProcesoFacadeTest extends SessionBeanTest<PasoProceso> {

    public PasoProcesoFacadeTest() {
        super(PasoProceso.class);
        registrosEsperados.add(new PasoProceso(1, 1));
        registrosEsperados.add(new PasoProceso(2, 1));

    }

    private PasoProcesoFacade cut = new PasoProcesoFacade();
    private PasoProceso pasoProceso = new PasoProceso(1, 1);
    private List<PasoProceso> registrosEsperados = new ArrayList<>();

    @Before
    public void algo() {
        cut.em = em;
    }

    @Test
    public void testFindAll() {
        testFindAllGeneric(registrosEsperados);
    }

    @Test
    public void testFindId() {
        testFindIdGeneric();
    }

    @Test(expected = NullPointerException.class)
    public void testCountEmNull() {
        cut.em = null;
        testCountEmNullGeneric();
    }

    @Test(expected = NullPointerException.class)
    public void testFindEmNull() {
        int desde = 0, hasta = 6;
        cut.em = null;
        testFindRangeEmNullGeneric(desde, hasta);
    }

    @Test(expected = NullPointerException.class)
    public void testEditException() {
        cut.em = null;
        testEditEmNullGeneric();
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveException() {
        cut.em = null;
        testRemoveEmNullGeneric();
    }

    @Test
    public void testFindAllEmpty() {
        cut.em = null;
        testFinAllEmptyGeneric();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindIdException() {
        Object i = 1;
        testFindIdExceptionGeneric(i);
    }

    @Test(expected = NullPointerException.class)
    public void testFindIdEmNulo() {
        cut.em = null;
        testFindIdEmNuloGeneric();
    }

    @Test
    public void testCount() {
        testCountGeneric(10);
    }

// <editor-fold desc="/*****TESTS CREATE*******/">
    @Test
    public void testCreate() {
        testCreateGeneric();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateEmNulo() {
        cut.em = null;
        testCreateEmNuloGeneric();
    }

    @Test(expected = Exception.class)
    public void testCreateException() {
        testCreateExceptionGeneric();
    }

// </editor-fold>
    @Test
    public void testFindRange() {
        testFingRangeGeneric(registrosEsperados);
    }

    @Test
    public void testEdit() {
        testEditGeneric();
    }

    @Test
    public void testRemove() {
        testRemoveGeneric();
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected PasoProceso getEntity() {
        return pasoProceso;
    }

}
