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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.PasoReparacion;

/**
 *
 * @author kevin
 */
public class PasoReparacionFacadeTest extends SessionBeanTest<PasoReparacion> {

    private PasoReparacionFacade cut = new PasoReparacionFacade();
    private PasoReparacion pasoReparacion = new PasoReparacion(1, 1, 1);
    private List<PasoReparacion> registrosEsperados = new ArrayList<>();

    public PasoReparacionFacadeTest() {
        super(PasoReparacion.class);
        registrosEsperados.add(new PasoReparacion(1, 1,1));
        registrosEsperados.add(new PasoReparacion(2, 2,5));

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
    protected PasoReparacion getEntity() {
        return pasoReparacion;
    }

}
