/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class SubParteFacadeTest extends SessionBeanTest<SubParte> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private SubParteFacade cut;
    private SubParte subparte = new SubParte(1, "motor");
    private List<SubParte> registrosEsperados = new ArrayList<>();

    public SubParteFacadeTest() {
        super(SubParte.class);
        registrosEsperados.add(new SubParte(1, "motor"));
        registrosEsperados.add(new SubParte(2, "radiador"));

    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<SubParte> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected SubParte getEntity() {
        return subparte;
    }
    
    @Test
    public void subPartePorParteTest(){
     System.out.println("SUBPARTE POR PARTE");
        //SETUP
        int idpieza = 1;
        String nombre = "tornillo";
        Mockito.when(ema.createNamedQuery("SubParte.findByidParteNombreLike")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosEsperados);

        //TODO GOOD
        List ls = cut.subPartePorParte(idpieza, nombre);
        Assert.assertEquals(registrosEsperados.size(), ls.size());

        //ALGO MENOR 
        ls = cut.subPartePorParte(-1, nombre);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), ls.size());

        //EXCEPTION
        setEmNull();
        cut.subPartePorParte(idpieza, nombre);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }
}
