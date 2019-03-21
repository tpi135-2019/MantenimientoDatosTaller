/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.PropietarioFacade;
import ues.fmocc.ingenieria.tpi1352019.accesodatos.libreriadatostaller.Propietario;

/**
 *
 * @author kevin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public class FrmPropietarioTest {

    public FrmPropietarioTest() {
    }

    @Mock
    protected FacesContext facesContext;
    PropietarioFacade pf;
    FrmPropietario cut;

    @Before
    public void setUp() {
     
        cut = new FrmPropietario();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void messageTest() {
        System.out.println("addMessage");
        PowerMockito.mockStatic(FacesContext.class);
        Mockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
        ArgumentCaptor<String> clientId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<FacesMessage> facesMessage = ArgumentCaptor.forClass(FacesMessage.class);
        String summary = "msj";
        cut.addMessage(summary);
        Mockito.verify(facesContext).addMessage(clientId.capture(), facesMessage.capture());
        // Assert.fail("fallara");
    }

    @Test
   public void getKeyTest() {
        System.out.println("getRowData");
        Propietario p = new Propietario(1);
        Assert.assertNotNull(p);
        Object b=cut.getKey(p);
        Assert.assertEquals(1,b);
      //  Assert.fail("muere");
   }  
   
   @Test
   public void getFacadeTest(){
       System.out.println("getFacade");
       PropietarioFacade pfc = (PropietarioFacade) cut.getFacadeLocal();
       Assert.assertEquals(pfc,pf);
   }

@Test
public void crearTest(){
    System.out.println("crear");
   cut.crear();
  
    
}

}
