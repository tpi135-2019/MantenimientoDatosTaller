/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.EspecialidadFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.ProcesoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Proceso;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmProcesoTest extends BackingBeanTest<Proceso> {

    @Mock
    ProcesoFacade procesoFacade;
    @Mock
    EspecialidadFacade especialidadFacade;
    @Spy
    @InjectMocks
    FrmProceso cut = new FrmProceso();
    Proceso proceso = new Proceso(1);
    List<Especialidad> registrosEspecialidad = new ArrayList<>();
    List<Proceso> registrosProceso = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosEspecialidad.add(new Especialidad(1, "Mecanica General"));
        registrosEspecialidad.add(new Especialidad(2, "Electronica"));

        registrosProceso.add(new Proceso(1, "Cambio de aceite"));
        registrosProceso.add(new Proceso(2, "cambio de llantas"));
    }

    @Override
    protected AbstractBean<Proceso> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Proceso> getFacade() {
        return procesoFacade;
    }

    @Override
    protected Proceso getEntity() {
        return proceso;
    }

    @Test
    public void listarEspecialidadesTest() {
        System.out.println("listarEspecialidad");
        cut.listarEspecialidades();
        Mockito.verify(especialidadFacade).findAll();
        cut.especialidadFacade = null;
        cut.listarEspecialidades();
        Mockito.doThrow(Exception.class).when(especialidadFacade).findAll();

    }

    @Override
    protected List<Proceso> getLista() {
        return registrosProceso;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }

    @Test
    public void getListaEspecialidadTest() {
        cut.setListaEspecialidad(registrosEspecialidad);
        List ls = cut.getListaEspecialidad();
        Assert.assertEquals(registrosEspecialidad, ls);
    }
}
