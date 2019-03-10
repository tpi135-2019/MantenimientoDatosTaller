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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Parte;

/**
 *
 * @author kevin
 */
public class ParteFacadeTest extends SessionBeanTest<Parte> {

    private ParteFacade cut = new ParteFacade();
    private Parte parte = new Parte(1);
    private List<Parte> registrosEsperados = new ArrayList<>();

    public ParteFacadeTest() {
        super(Parte.class);
        registrosEsperados.add(new Parte(1, "bujia"));
        registrosEsperados.add(new Parte(2, "bobina"));
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
    protected Parte getEntity() {
        return parte;
    }

}
