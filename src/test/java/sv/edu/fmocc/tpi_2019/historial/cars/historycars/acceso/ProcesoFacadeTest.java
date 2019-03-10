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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Proceso;

/**
 *
 * @author kevin
 */
public class ProcesoFacadeTest extends SessionBeanTest<Proceso> {

    private ProcesoFacade cut = new ProcesoFacade();
    private Proceso proceso = new Proceso(1);
    private List<Proceso> registrosEsperados = new ArrayList<>();

    public ProcesoFacadeTest() {
        super(Proceso.class);
        registrosEsperados.add(new Proceso(1, "cambio de aceite"));
        registrosEsperados.add(new Proceso(2, "cambio de frenos"));

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
    protected Proceso getEntity() {
        return proceso;
    }

}
