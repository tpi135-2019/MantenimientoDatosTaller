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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.TipoVehiculo;

/**
 *
 * @author kevin
 */
public class TipoVehiculoFacadeTest extends SessionBeanTest<TipoVehiculo> {

    public TipoVehiculoFacadeTest() {
        super(TipoVehiculo.class);
        registrosEsperados.add(new TipoVehiculo(1, "sedan"));
        registrosEsperados.add(new TipoVehiculo(2, "pickup"));
    }

    private TipoVehiculoFacade cut = new TipoVehiculoFacade();
    private TipoVehiculo entidad = new TipoVehiculo(1);
    private List<TipoVehiculo> registrosEsperados = new ArrayList<>();

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
    protected TipoVehiculo getEntity() {
        return entidad;
    }

}
