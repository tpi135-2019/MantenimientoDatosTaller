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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class EspecialidadFacadeTest extends SessionBeanTest<Especialidad> {

    @Mock
    EntityManager ema;
    @InjectMocks
    private EspecialidadFacade cut;
    private Especialidad especialidad = new Especialidad(1);
    private List<Especialidad> registrosEsperados = new ArrayList<>();

    public EspecialidadFacadeTest() {
        super(Especialidad.class);
        registrosEsperados.add(new Especialidad(1, "Mecanica general"));
        registrosEsperados.add(new Especialidad(2, "Electricista"));

    }

    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Especialidad getEntity() {
        return especialidad;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Especialidad> getLista() {
        return registrosEsperados;
    }

}
