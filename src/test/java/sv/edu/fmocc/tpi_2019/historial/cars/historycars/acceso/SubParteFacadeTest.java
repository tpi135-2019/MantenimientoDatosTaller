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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.SubParte;

/**
 *
 * @author kevin
 */
public class SubParteFacadeTest extends SessionBeanTest<SubParte> {

    private SubParteFacade cut = new SubParteFacade();
    private SubParte subparte = new SubParte(1, "motor");
    private List<SubParte> registrosEsperados = new ArrayList<>();

    public SubParteFacadeTest() {
        super(SubParte.class);
        registrosEsperados.add(new SubParte(1, "motor"));
        registrosEsperados.add(new SubParte(2, "radiador"));

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

    @Test
    public void testFindRange() {
        testFingRangeGeneric(registrosEsperados);
    }

    @Test
    public void testCount() {
        testCountGeneric(10);
    }

    @Test
    public void testCreate() {
        testCreateGeneric();
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
    protected SubParte getEntity() {
        return subparte;
    }
}
