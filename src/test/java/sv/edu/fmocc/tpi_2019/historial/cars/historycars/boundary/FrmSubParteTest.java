/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ParteFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmSubParteTest extends BackingBeanTest<SubParte> {

    @Mock
    SubParteFacade subParteFacade;
    @Mock
    ParteFacade parteFacade;
    @Spy
    @InjectMocks
    FrmSubParte cut = new FrmSubParte();
    SubParte subParte = new SubParte(1);

    List<Parte> registrosParte = new ArrayList<>();
    List<SubParte> registrosSubParte = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosParte.add(new Parte(1, "motor"));
        registrosParte.add(new Parte(2, "interiores"));

        registrosSubParte.add(new SubParte(1, "pistones"));
        registrosSubParte.add(new SubParte(2, "rediador"));

    }

    @Override
    protected AbstractBean<SubParte> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<SubParte> getFacade() {
        return subParteFacade;
    }

    @Override
    protected SubParte getEntity() {
        return subParte;
    }

    @Test
    public void listarPartes() {
        System.out.println("listarPartes");
        Mockito.when(parteFacade.findAll()).thenReturn(registrosParte);
        List lista = cut.listarPartes();
        Assert.assertEquals(registrosParte.size(), lista.size());
        Mockito.when(parteFacade.findAll()).thenThrow(Exception.class);
        lista = cut.listarPartes();
        Assert.assertEquals(Collections.EMPTY_LIST.size(), lista.size());
    }

    @Override
    protected List<SubParte> getLista() {
        return registrosSubParte;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }
}
