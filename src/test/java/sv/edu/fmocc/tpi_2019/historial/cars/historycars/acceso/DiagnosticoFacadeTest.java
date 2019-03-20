/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class DiagnosticoFacadeTest extends SessionBeanTest<Diagnostico> {

    @InjectMocks
    private DiagnosticoFacade cut;
    private Diagnostico diagnostico = new Diagnostico(1);
    private List<Diagnostico> registrosEsperados = new ArrayList<>();

    public DiagnosticoFacadeTest() {
        super(Diagnostico.class);
        registrosEsperados.add(new Diagnostico(1, "ah esta jodido ese bolado", new Date()));
        registrosEsperados.add(new Diagnostico(2, "ah esta jodido ese bolado", new Date()));
    }

    @Test
    public void testFindAll() {

        testFindAllGeneric(registrosEsperados);
    }

    @Test
    public void testFindRange() {
        testFingRangeGeneric(registrosEsperados);
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
        Whitebox.setInternalState(cut, "em", null);
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
        Whitebox.setInternalState(cut, "em", null);
        testCreateEmNuloGeneric();
    }

    @Test(expected = Exception.class)
    public void testCreateException() {
        testCreateExceptionGeneric();
    }

// </editor-fold>}
    @Test(expected = NullPointerException.class)
    public void testCountEmNull() {
        Whitebox.setInternalState(cut, "em", null);
        testCountEmNullGeneric();
    }

    @Test(expected = NullPointerException.class)
    public void testFindEmNull() {
        int desde = 0, hasta = 6;
        Whitebox.setInternalState(cut, "em", null);
        testFindRangeEmNullGeneric(desde, hasta);
    }

    @Test(expected = NullPointerException.class)
    public void testEditEmNulo() {
        Whitebox.setInternalState(cut, "em", null);
        testEditEmNullGeneric();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditException() {
        testEditExceptionGeneric();
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveEmNulo() {
        Whitebox.setInternalState(cut, "em", null);
        testRemoveEmNullGeneric();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveException() {
        testRemoveExceptionGeneric();
    }

    @Test
    public void testFindAllEmpty() {
        Whitebox.setInternalState(cut, "em", null);
        testFinAllEmptyGeneric();
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
    protected Diagnostico getEntity() {
        return diagnostico;
    }

}
