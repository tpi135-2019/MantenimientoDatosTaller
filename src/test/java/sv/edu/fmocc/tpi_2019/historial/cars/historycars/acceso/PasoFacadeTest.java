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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Paso;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PasoFacadeTest extends SessionBeanTest<Paso> {
    @Mock
    EntityManager ema;
    @InjectMocks
    private PasoFacade cut;
    private Paso paso = new Paso(1);
    private List<Paso> registrosEsperados = new ArrayList<>();

    public PasoFacadeTest() {
        super(Paso.class);
        registrosEsperados.add(new Paso(1, "levantar carro"));
        registrosEsperados.add(new Paso(2, "quitar llanta"));

    }

      @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Paso getEntity() {
        return paso;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Paso> getLista() {
        return registrosEsperados;
    }

}
