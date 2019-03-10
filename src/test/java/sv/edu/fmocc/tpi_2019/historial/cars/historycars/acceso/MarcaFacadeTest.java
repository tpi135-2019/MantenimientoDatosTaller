/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.entities.Marca;

/**
 *
 * @author kevin
 */
public class MarcaFacadeTest extends SessionBeanTest<Marca> {

    private MarcaFacade cut = new MarcaFacade();
    private Marca marca = new Marca();
    private List<Marca> registrosEsperados = new ArrayList<>();

    public MarcaFacadeTest() {
        super(Marca.class);
        registrosEsperados.add(new Marca(1, "nissan"));
        registrosEsperados.add(new Marca(2, "toyota"));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Marca getEntity() {
        return marca;
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

}
