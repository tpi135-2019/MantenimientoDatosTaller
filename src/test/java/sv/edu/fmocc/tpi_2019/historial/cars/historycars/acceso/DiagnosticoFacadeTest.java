/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Diagnostico;

/**
 *
 * @author kevin
 */
public class DiagnosticoFacadeTest extends SessionBeanTest<Diagnostico> {

    private DiagnosticoFacade cut = new DiagnosticoFacade();
    private Diagnostico diagnostico = new Diagnostico(1);
    private List<Diagnostico> registrosEsperados = new ArrayList<>();

    public DiagnosticoFacadeTest() {
        super(Diagnostico.class);
        registrosEsperados.add(new Diagnostico(1, "ah esta jodido ese bolado", "fecha"));
        registrosEsperados.add(new Diagnostico(2, "ah esta jodido ese bolado", "fecha"));

    }

    @Before
    public void first() {
        cut.em = em;
    }

    @Test
    public void testFindAll() {
        testFindAllGeneric(registrosEsperados);
    }

    @Test
    public void testFindRange() {
        System.out.println("range");
        cut.em = em;
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
