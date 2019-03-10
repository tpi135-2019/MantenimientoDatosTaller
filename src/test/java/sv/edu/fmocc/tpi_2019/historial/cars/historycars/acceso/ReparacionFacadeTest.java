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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Reparacion;

/**
 *
 * @author kevin
 */
public class ReparacionFacadeTest extends SessionBeanTest<Reparacion> {

    public ReparacionFacadeTest() {
        super(Reparacion.class);
        registrosEsperados.add(new Reparacion(1));
        registrosEsperados.add(new Reparacion(2));
    }

    private ReparacionFacade cut = new ReparacionFacade();
    private Reparacion reparacion = new Reparacion(1);
    private List<Reparacion> registrosEsperados = new ArrayList<>();

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
    public void testFindIdException() {
        Object i = null;
        testFindIdExceptionGeneric(i);
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
    protected Reparacion getEntity() {
        return reparacion;
    }
}
