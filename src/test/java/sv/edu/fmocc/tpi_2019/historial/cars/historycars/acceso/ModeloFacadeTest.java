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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Modelo;

/**
 *
 * @author kevin
 */
public class ModeloFacadeTest extends SessionBeanTest<Modelo> {

    private ModeloFacade cut = new ModeloFacade();
    private Modelo modelo = new Modelo(1);
    private List<Modelo> registrosEsperados = new ArrayList<>();

    public ModeloFacadeTest() {
        super(Modelo.class);
        registrosEsperados.add(new Modelo(1, "sentra"));
        registrosEsperados.add(new Modelo(2, "corolla"));
    }

    @Before
    public void init() {
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
    protected Modelo getEntity() {
        return modelo;
    }

}
