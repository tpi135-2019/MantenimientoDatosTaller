/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;

/**
 *
 * @author kevin
 */
@RunWith(MockitoJUnitRunner.class)
public class DiagnosticoFacadeTest extends SessionBeanTest<Diagnostico> {
    
    @Mock
    EntityManager ema;
    @InjectMocks
    private DiagnosticoFacade cut;
    private Diagnostico diagnostico = new Diagnostico(1);
    private List<Diagnostico> registrosEsperados = new ArrayList<>();

    public DiagnosticoFacadeTest() {
        super(Diagnostico.class);
        registrosEsperados.add(new Diagnostico(1, "ah esta jodido ese bolado", new Date()));
        registrosEsperados.add(new Diagnostico(2, "ah esta jodido ese bolado", new Date()));
    }
    
    @Override
    protected FacadeGenerico getSessionBean() {
        return cut;
    }

    @Override
    protected Diagnostico getEntity() {
        return diagnostico;
    }

    @Override
    protected EntityManager getEntityManager() {
        return ema;
    }

    @Override
    protected List<Diagnostico> getLista() {
        return registrosEsperados;
    }
    
    
   

}
