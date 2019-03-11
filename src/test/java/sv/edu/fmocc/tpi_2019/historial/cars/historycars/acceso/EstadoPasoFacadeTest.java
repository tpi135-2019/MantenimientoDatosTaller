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

    @Before
    public void first() {
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
    protected EstadoPaso getEntity() {
        return estadoPaso;
    }

}
