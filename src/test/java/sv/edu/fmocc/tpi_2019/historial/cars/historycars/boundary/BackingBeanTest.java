/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.fmocc.tpi_2019.historial.cars.historycars.boundary;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sv.edu.fmocc.tpi_2019.historial.cars.historycars.acceso.FacadeGenerico;

/**
 *
 * @author kevin
 * @param <T>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FacesContext.class})
public abstract class BackingBeanTest<T> extends IniciarTest {

    protected abstract AbstractBean<T> getBean();

    protected abstract FacadeGenerico<T> getFacade();

    protected abstract T getEntity();
    
    protected abstract List<T> getLista();
    


    @Spy
    Logger logger = Logger.getGlobal();

    @Mock
    protected FacesContext facesContext;

    @Before
    public void setup() {
        getBean().setLogger(logger);
    }

//    @Test
//    public void LoadTest(){
//        System.out.println("Load");
//        getBean().modelo();
//        Mockito.verify(getFacade()).findRange(Matchers.anyInt(), Matchers.anyInt());
//        
//         //Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
//    }
    
    @Test
    public void messageTest() {
        System.out.println("addMessage");
        PowerMockito.mockStatic(FacesContext.class);
        Mockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
        ArgumentCaptor<String> clientId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<FacesMessage> facesMessage = ArgumentCaptor.forClass(FacesMessage.class);
        String summary = "msj";
        getBean().addMessage(summary);
        Mockito.verify(facesContext).addMessage(clientId.capture(), facesMessage.capture());
        Assert.assertEquals(FacesMessage.SEVERITY_INFO, facesMessage.getValue().getSeverity());
        Assert.assertEquals(summary, facesMessage.getValue().getSummary());
    }

    @Test
    public void getKeyTest() {
        System.out.println("getRowData");
        Assert.assertNotNull(getEntity());
        Object b = getBean().getKey(getEntity());
        Assert.assertNotNull(b);
        //  Assert.fail("muere");
    }

    @Test
    public void crearTest() {
        System.out.println("crear");
        Mockito.doNothing().when(getFacade()).create(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().crear();
        Mockito.verify(getFacade()).create(Matchers.anyObject());
        //catch
        Mockito.doThrow(new NullPointerException()).when(getFacade()).create(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().crear();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void modificarTest() {
        System.out.println("modificar");
        Mockito.doNothing().when(getFacade()).edit(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().modificar();
        Mockito.verify(getFacade()).edit(Matchers.anyObject());

        Mockito.doThrow(new NullPointerException()).when(getFacade()).edit(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().modificar();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());

    }

    @Test
    public void eliminarTest() {
        System.out.println("eliminar");
        Mockito.doNothing().when(getFacade()).remove(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().eliminar();
        Mockito.verify(getFacade()).remove(Matchers.anyObject());

        Mockito.doThrow(new NullPointerException()).when(getFacade()).remove(Matchers.anyObject());
        Mockito.doNothing().when(getBean()).addMessage(Matchers.any());
        getBean().eliminar();
        Mockito.verify(logger).log(Matchers.any(Level.class), Matchers.anyString());
    }

}
