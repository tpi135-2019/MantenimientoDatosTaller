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
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonalFacadeTest extends SessionBeanTest<Personal> {

    public PersonalFacadeTest() {
        super(Personal.class);
        registrosEsperados.add(new Personal(1, "milo", "reyes"));
        registrosEsperados.add(new Personal(2, "chele", "papaya"));
    }
    
    
    @Mock
    EntityManager ema;
    @InjectMocks
    private PersonalFacade cut;
    private Personal personal = new Personal(1);
    private List<Personal> registrosEsperados = new ArrayList<>();

      @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Personal getEntity() {
        return personal;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Personal> getLista() {
        return registrosEsperados;
    }

}
