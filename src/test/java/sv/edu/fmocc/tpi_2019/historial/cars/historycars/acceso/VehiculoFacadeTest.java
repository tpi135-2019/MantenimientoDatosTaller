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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Vehiculo;

/**
 *
 * @author kevin
 */
public class VehiculoFacadeTest extends SessionBeanTest<Vehiculo> {

    private VehiculoFacade cut = new VehiculoFacade();
    private Vehiculo entidad = new Vehiculo("P35166845", "123456", "AD12384598D", "AD12384598D");
    private List<Vehiculo> registrosEsperados = new ArrayList<>();

    public VehiculoFacadeTest() {
        super(Vehiculo.class);
        registrosEsperados.add(new Vehiculo("P35166845", "123456", "AD12384598D", "AD12384598D"));
        registrosEsperados.add(new Vehiculo("P963258", "2156852", "ADFD15368D", "AD12384598D"));
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
    protected Vehiculo getEntity() {
        return entidad;
    }

}
