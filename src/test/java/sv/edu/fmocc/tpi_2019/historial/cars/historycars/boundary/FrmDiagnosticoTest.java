/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.DiagnosticoFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.VehiculoFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Diagnostico;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Vehiculo;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmDiagnosticoTest extends BackingBeanTest<Diagnostico> {

    @Mock
    DiagnosticoFacade diagnosticoFacade;
    @Mock
    VehiculoFacade vehiculoFacade;
    @Spy
    @InjectMocks
    FrmDiagnostico cut = new FrmDiagnostico();
    Diagnostico diagnostico = new Diagnostico(1);

    List<Vehiculo> registrosVehiculos = new ArrayList<>();
    List<Diagnostico> registrosDiagnostico = new ArrayList<>();

    @Override
    protected AbstractBean<Diagnostico> getBean() {
        return cut;
    }

    @Before
    @Override
    public void setup() {
        registrosVehiculos.add(new Vehiculo("P323-12", "HSH3345", "GTTG324", "FST6632"));
        registrosVehiculos.add(new Vehiculo("P322-12", "E31145", "D2342F24", "VCBQ632"));
        registrosDiagnostico.add(new Diagnostico(1, "se le pincho la llanta", new Date()));
        registrosDiagnostico.add(new Diagnostico(2, "necesita cambio de aceite", new Date()));

    }

    @Override
    protected FacadeGenerico<Diagnostico> getFacade() {
        return diagnosticoFacade;
    }

    @Override
    protected Diagnostico getEntity() {
        return diagnostico;
    }

    @Test
    public void listarVehiculosTest() {
        System.out.println("listarVehiculos");
        Mockito.when(vehiculoFacade.findAll()).thenReturn(registrosVehiculos);
        List lsta = cut.listarVehiculos();
        Assert.assertEquals(registrosVehiculos.size(), lsta.size());
    }

    @Override
    protected List<Diagnostico> getLista() {
        return registrosDiagnostico;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }

}
