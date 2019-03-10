/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Propietario;

/**
 *
 * @author kevin
 */
public class PropietarioFacadeTest extends SessionBeanTest<Propietario>{

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

    
    @Test
    public void testFindAll() {
        registrosEsperados = listarRegistros();
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

    public Propietario crearRegistro(int id, String nombre, String apellido, String direccion, String cel) {
        Propietario prop = new Propietario();
        prop.setIdPropietario(id);
        prop.setNombre(nombre);
        prop.setApellido(apellido);
        prop.setDireccion(direccion);
        prop.setTelefono(cel);
        return prop;
    }
//
    public List<Propietario> listarRegistros() {
        List<Propietario> ls = new ArrayList<>();
        ls.add(crearRegistro(1, "juan", "penya", "calle ayuwoky", "7423-2312"));
        ls.add(crearRegistro(2, "juan", "penya", "calle ayuwoky", "7423-2312"));

        return ls;
    }

   

    

}
