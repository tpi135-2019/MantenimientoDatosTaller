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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Propietario;

/**
 *
 * @author kevin
 */
public class PropietarioFacadeTest extends SessionBeanTest<Propietario> {

    Propietario propietario = new Propietario(1);
    PropietarioFacade cut = new PropietarioFacade();
    List<Propietario> registrosEsperados;

    @Override
    protected FacadeGenerico<Propietario> getSessionBean() {
        return cut;
    }

    @Override
    protected Propietario getEntity() {
        return propietario;
    }

    public PropietarioFacadeTest() {
        super(Propietario.class);
    }
    
    @Before
    public void first() {
        cut.em = em;
    }

    @Test
    public void testFindAll() {
        registrosEsperados = listarRegistros();
        testFindAllGeneric(registrosEsperados);
    }

    @Test
    public void testFindRange() {
        registrosEsperados = listarRegistros();
        testFingRangeGeneric(registrosEsperados);
    }


    @Test
    public void testFindId() {
        testFindIdGeneric();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindIdException() {
        Object i = 1;
        testFindIdExceptionGeneric(i);
    }

    @Test(expected = NullPointerException.class)
    public void testFindIdEmNulo() {
        cut.em = null;
        testFindIdEmNuloGeneric();
    }

    @Test
    public void testCount() {
        testCountGeneric(10);
    }

// <editor-fold desc="/*****TESTS CREATE*******/">
    @Test
    public void testCreate() {
        testCreateGeneric();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateEmNulo() {
        cut.em = null;
        testCreateEmNuloGeneric();
    }

    @Test(expected = Exception.class)
    public void testCreateException() {
        testCreateExceptionGeneric();
    }

// </editor-fold>

    @Test
    public void testEdit() {
        testEditGeneric();
    }

    @Test
    public void testRemove() {
      testRemoveGeneric();
    }

    public Propietario crearRegistro(int id, String nombre, String apellido, String direccion, String cel) {
        Propietario prop = new Propietario();
        prop.setIdPropietario(id);
        prop.setNombre(nombre);
        prop.setApellido(apellido);
        prop.setDireccion(direccion);
        prop.setTelefono(cel);
        return prop;
    }

    public List<Propietario> listarRegistros() {
        List<Propietario> ls = new ArrayList<>();
        ls.add(crearRegistro(1, "juan", "penya", "calle ayuwoky", "7423-2312"));
        ls.add(crearRegistro(2, "juan", "penya", "calle ayuwoky", "7423-2312"));

        return ls;
    }

}
