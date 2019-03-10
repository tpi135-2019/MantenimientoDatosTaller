/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void testFindAll() {
        cut.em = em;
        testFindAllGeneric(registrosEsperados);
    }

    @Test
    public void testFindId() {
        cut.em = em;
        testFindIdGeneric();
    }

    @Test
    public void testCreate() {
        cut.em = em;
        testCreateGeneric();
    }

    @Test
    public void testEdit() {
        cut.em = em;
        testEditGeneric();
    }

    @Test
    public void testRemove() {
        cut.em = em;
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
