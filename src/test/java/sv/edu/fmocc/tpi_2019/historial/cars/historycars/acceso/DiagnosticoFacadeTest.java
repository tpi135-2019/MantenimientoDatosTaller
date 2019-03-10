/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Diagnostico;

/**
 *
 * @author kevin
 */
public class DiagnosticoFacadeTest extends SessionBeanTest<Diagnostico> {

    private DiagnosticoFacade cut = new DiagnosticoFacade();
    private Diagnostico diagnostico = new Diagnostico(1);
    private List<Diagnostico> registrosEsperados = new ArrayList<>();

    public DiagnosticoFacadeTest() {
        super(Diagnostico.class);
        registrosEsperados.add(new Diagnostico(1, "ah esta jodido ese bolado", "fecha"));
        registrosEsperados.add(new Diagnostico(2, "ah esta jodido ese bolado", "fecha"));

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
    protected Diagnostico getEntity() {
        return diagnostico;
    }

}
