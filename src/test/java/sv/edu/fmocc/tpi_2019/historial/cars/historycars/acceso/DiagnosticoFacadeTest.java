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
    
    @Before
    public void first() {
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
    protected Diagnostico getEntity() {
        return diagnostico;
    }

}
