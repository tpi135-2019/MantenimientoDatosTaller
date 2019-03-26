/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

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
}
