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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.HistorialPropietario;

/**
 *
 * @author kevin
 */
public class HistorialPropietarioFacadeTest extends SessionBeanTest<HistorialPropietario> {

    private HistorialPropietarioFacade cut = new HistorialPropietarioFacade();
    private HistorialPropietario historialPropietario = new HistorialPropietario(1, "P123-456");
    private List<HistorialPropietario> registrosEsperados = new ArrayList<>();

    public HistorialPropietarioFacadeTest() {
        super(HistorialPropietario.class);
        registrosEsperados.add(new HistorialPropietario(1, "P123-789"));
        registrosEsperados.add(new HistorialPropietario(2, "P654-789"));

    }

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
    protected HistorialPropietario getEntity() {
        return historialPropietario;
    }

}
