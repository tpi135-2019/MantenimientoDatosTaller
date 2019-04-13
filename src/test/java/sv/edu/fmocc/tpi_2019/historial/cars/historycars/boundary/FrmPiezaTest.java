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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PiezaFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SubParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Pieza;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.SubParte;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmPiezaTest extends BackingBeanTest<Pieza> {

    @Mock
    PiezaFacade piezaFacade;
    @Mock
    SubParteFacade subParteFacade;
    @Spy
    @InjectMocks
    FrmPieza cut = new FrmPieza();
    Pieza pieza = new Pieza(1);
    List<SubParte> registrosSubParte= new ArrayList<>();
    List<Pieza> piezaList= new ArrayList<>();

    @Before
    public void setup() {
        registrosSubParte.add(new SubParte(1, "pistones"));
        registrosSubParte.add(new SubParte(2, "radiador"));
        
        piezaList.add(new Pieza(1, "tuercas"));
        piezaList.add(new Pieza(2, "faja del tiempo"));
    }

    
    
    @Override
    protected AbstractBean<Pieza> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Pieza> getFacade() {
        return piezaFacade;
    }

    @Override
    protected Pieza getEntity() {
        return pieza;
    }

    @Test
    public void listarPiezas() {
        System.out.println("listarPiezas");
        Mockito.when(subParteFacade.findAll()).thenReturn(registrosSubParte);
        List lista = cut.listarSubpartes();
        Assert.assertEquals(registrosSubParte.size(),lista.size());
        Mockito.when(subParteFacade.findAll()).thenThrow(Exception.class);
        lista=cut.listarSubpartes();
        Assert.assertEquals(Collections.EMPTY_LIST.size(),lista.size());
    }

    @Override
    protected List<Pieza> getLista() {
        return piezaList;
    }

}
