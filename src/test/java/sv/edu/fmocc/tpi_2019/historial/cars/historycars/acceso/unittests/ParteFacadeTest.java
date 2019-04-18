/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.unittests;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ParteFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Parte;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class ParteFacadeTest extends SessionBeanTest<Parte> {
    
    @Mock
    EntityManager ema;
    @InjectMocks
    private ParteFacade cut;
    private Parte parte = new Parte(1);
    private List<Parte> registrosEsperados = new ArrayList<>();

    public ParteFacadeTest() {
        super(Parte.class);
        registrosEsperados.add(new Parte(1, "bujia"));
        registrosEsperados.add(new Parte(2, "bobina"));
    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Parte getEntity() {
        return parte;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Parte> getLista() {
        return registrosEsperados;
    }

}
