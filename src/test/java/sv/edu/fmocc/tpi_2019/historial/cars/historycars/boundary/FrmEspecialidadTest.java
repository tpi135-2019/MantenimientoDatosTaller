/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmEspecialidadTest extends BackingBeanTest<Especialidad>{
    
    @Mock
    EspecialidadFacade especialidadFacade;
    @Spy
    @InjectMocks
    FrmEspecialidad cut = new FrmEspecialidad();
    Especialidad especialidad = new Especialidad(1);
    List<Especialidad> registrosEspecialidad= new ArrayList<>();
    
@Before
public void setup(){
    registrosEspecialidad.add(new Especialidad(1, "mecanica General"));
    registrosEspecialidad.add(new Especialidad(2, "Electrico"));
}
    
    @Override
    protected AbstractBean<Especialidad> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Especialidad> getFacade() {
        return especialidadFacade;
    }

    @Override
    protected Especialidad getEntity() {
        return especialidad;
    }

    @Override
    protected List<Especialidad> getLista() {
        return registrosEspecialidad;
    }
    
}
