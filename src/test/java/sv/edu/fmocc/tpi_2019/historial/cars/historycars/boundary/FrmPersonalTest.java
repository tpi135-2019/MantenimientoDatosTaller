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
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PersonalFacade;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.SucursalFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Especialidad;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Personal;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Sucursal;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
public class FrmPersonalTest extends BackingBeanTest<Personal> {

    @Mock
    PersonalFacade personalFacade;
    @Mock
    SucursalFacade sucursalFacade;
    @Mock
    EspecialidadFacade especialidadFacade;
    @Spy
    @InjectMocks
    FrmPersonal cut = new FrmPersonal();
    Personal personal = new Personal(1);
    List<Sucursal> registrosSucursal = new ArrayList<>();
    List<Especialidad> registrosEspecialidad= new ArrayList<>();
    List<Personal> registrosPersonal = new ArrayList<>();

    @Before
    @Override
    public void setup() {
        registrosSucursal.add(new Sucursal(1, "Taller los 3 chepes"));
        registrosSucursal.add(new Sucursal(1, "Taller los 3 chepes"));

        registrosPersonal.add(new Personal(1, "Jose", "Ramirez"));
        registrosPersonal.add(new Personal(2, "Cindy", "Nero"));
        
        registrosEspecialidad.add(new Especialidad(1));
    }

    @Override
    protected AbstractBean<Personal> getBean() {
        return cut;
    }

    @Override
    protected FacadeGenerico<Personal> getFacade() {
        return personalFacade;
    }

    @Override
    protected Personal getEntity() {
        return personal;
    }

    @Test
    public void listarSucursalesTest() {
        System.out.println("listarSucursales");
       cut.listarSucursales();
         Mockito.verify(sucursalFacade).findAll();
        cut.sucursalFacade=null;
        cut.listarSucursales();
        Mockito.doThrow(Exception.class).when(sucursalFacade).findAll();
    }
    
    
    @Test
    public void listarEspecialidaTest() {
        System.out.println("listarEspecialidad");
       cut.listarEspecialidades();
         Mockito.verify(especialidadFacade).findAll();
        cut.especialidadFacade=null;
        cut.listarEspecialidades();
        Mockito.doThrow(Exception.class).when(especialidadFacade).findAll();
    }


    @Override
    protected List<Personal> getLista() {
        return registrosPersonal;
    }

    @Test
    public void getRowDTest() {
        String key = "";
        getRowDataTest(key);
    }

    @Test
    public void getListaSucursalTest(){
        cut.setListaSucursal(registrosSucursal);
        List ls = cut.getListaSucursal();
        Assert.assertEquals(registrosSucursal,ls);
    }
    
     @Test
    public void getListaEspecialidadTest(){
        cut.setLsEspecialidad(registrosEspecialidad);
        List ls = cut.getLsEspecialidad();
        Assert.assertEquals(registrosEspecialidad,ls);
    }
    
}
