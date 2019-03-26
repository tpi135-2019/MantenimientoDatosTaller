/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Marca;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class MarcaFacadeTest extends SessionBeanTest<Marca> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private MarcaFacade cut;
    private Marca marca = new Marca(1);
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

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Marca> getLista() {
        return registrosEsperados;
    }
}
