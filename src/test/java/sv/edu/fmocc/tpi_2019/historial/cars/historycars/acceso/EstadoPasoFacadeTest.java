/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.EstadoPaso;

/**
 *
 * @author kevin
 */
public class EstadoPasoFacadeTest extends SessionBeanTest<EstadoPaso> {

    private EstadoPasoFacade cut = new EstadoPasoFacade();
    private EstadoPaso estadoPaso = new EstadoPaso(1);
    private List<EstadoPaso> registrosEsperados = new ArrayList<>();

    public EstadoPasoFacadeTest() {
        super(EstadoPaso.class);
        registrosEsperados.add(new EstadoPaso(1, "Inicio"));
        registrosEsperados.add(new EstadoPaso(2, "En proceso"));

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
    protected EstadoPaso getEntity() {
        return estadoPaso;
    }

}
