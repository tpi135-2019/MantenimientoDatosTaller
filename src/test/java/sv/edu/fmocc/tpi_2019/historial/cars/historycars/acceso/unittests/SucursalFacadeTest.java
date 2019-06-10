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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class SucursalFacadeTest extends SessionBeanTest<Sucursal> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private SucursalFacade cut;
    private Sucursal sucursal = new Sucursal(1);
    private List<Sucursal> registrosEsperados = new ArrayList<>();
    private List<Personal> registrosPersonal = new ArrayList<>();

    public SucursalFacadeTest() {
        super(Sucursal.class);
        registrosEsperados.add(new Sucursal(1, "taller chuchito"));
        registrosEsperados.add(new Sucursal(2, "taller chepito"));
        registrosPersonal.add(new Personal(1, "milo", "reyes"));
        registrosPersonal.add(new Personal(2, "chele", "papaya"));
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Sucursal> getLista() {
        return registrosEsperados;
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Sucursal getEntity() {
        return sucursal;
    }


    @Test
    public void personalPorSucursalTest() {
        System.out.println("personal por sucursal test");
        //SET UP
        Mockito.when(ema.createNamedQuery("Personal.SucursalNombreLike")).thenReturn(queryMock);
        Mockito.when(queryMock.getResultList()).thenReturn(registrosPersonal);
        int id = 1;
        String nombre = "algo";
        
        //WHEN TODO GOOD
        List personal = cut.personalPorSucursal(id, nombre);
        Assert.assertEquals(registrosPersonal.size(), personal.size());
        
        //WHEN MENOR A 0
        personal = cut.personalPorSucursal(-1, nombre);
        Assert.assertEquals(Collections.EMPTY_LIST.size(), personal.size());
        
        //WHEN EXCEPCION
        setEmNull();
        cut.personalPorSucursal(id, nombre);
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }
}
