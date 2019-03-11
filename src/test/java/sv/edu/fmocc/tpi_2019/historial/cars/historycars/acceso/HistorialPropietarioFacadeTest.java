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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.HistorialPropietario;

/**
 *
 * @author kevin
 */
public class HistorialPropietarioFacadeTest extends SessionBeanTest<HistorialPropietario> {

    private HistorialPropietarioFacade cut = new HistorialPropietarioFacade();
    private HistorialPropietario historialPropietario = new HistorialPropietario(1, "P123-456");
    private List<HistorialPropietario> registrosEsperados = new ArrayList<>();

    public HistorialPropietarioFacadeTest() {
        super(HistorialPropietario.class);
        registrosEsperados.add(new HistorialPropietario(1, "P123-789"));
        registrosEsperados.add(new HistorialPropietario(2, "P654-789"));

    }

    @Before
    public void algo() {
        cut.em = em;
    }

    @Test
    public void testFindAll() {
        testFindAllGeneric(registrosEsperados);
    }

        @Test(expected = NullPointerException.class)
    public void testCountEmNull(){
        cut.em=null;
       testCountEmNullGeneric();
    }
    
    @Test(expected = NullPointerException.class)
    public void testFindEmNull(){
        int desde=0,hasta=6;
        cut.em=null;
        testFindRangeEmNullGeneric(desde, hasta);
    }
    
    @Test(expected = NullPointerException.class)
    public void testEditException(){
        cut.em=null;
        testEditEmNullGeneric();
    }
    
    @Test(expected = NullPointerException.class)
    public void testRemoveException(){
        cut.em=null;
        testRemoveEmNullGeneric();
    }
    
    @Test
    public void testFindAllEmpty(){
        cut.em=null;
        testFinAllEmptyGeneric();
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
    protected HistorialPropietario getEntity() {
        return historialPropietario;
    }

}
