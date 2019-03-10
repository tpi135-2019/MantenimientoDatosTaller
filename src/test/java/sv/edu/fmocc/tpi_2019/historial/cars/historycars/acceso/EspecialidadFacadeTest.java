/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Especialidad;

/**
 *
 * @author kevin
 */
public class EspecialidadFacadeTest extends SessionBeanTest<Especialidad> {

    private EspecialidadFacade cut = new EspecialidadFacade();
    private Especialidad especialidad = new Especialidad(1);
    private List<Especialidad> registrosEsperados = new ArrayList<>();

    public EspecialidadFacadeTest() {
        super(Especialidad.class);
        registrosEsperados.add(new Especialidad(1, "Mecanica general"));
        registrosEsperados.add(new Especialidad(2, "Electricista"));

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
    protected Especialidad getEntity() {
        return especialidad;
    }

}
